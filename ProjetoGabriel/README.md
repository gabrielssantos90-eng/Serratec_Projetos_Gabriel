# ProjetoGabriel - Sistema de Cálculo de Folha de Pagamento

## Descrição
Sistema desenvolvido em Java para calcular o salário líquido de funcionários de uma empresa, considerando descontos de INSS e Imposto de Renda, além de deduções por dependentes.

## Funcionalidades
- Cálculo automático de INSS conforme tabela progressiva
- Cálculo de Imposto de Renda com deduções por dependentes
- Processamento de arquivos CSV para entrada e saída de dados
- Validação de regras de negócio (idade de dependentes, CPFs únicos)
- Integração com banco de dados PostgreSQL
- Interface console para interação com o usuário

## Conceitos Implementados
- ✅ **Interfaces**: INSSCalculavel, IRCalculavel, ArquivoProcessavel
- ✅ **Classes abstratas**: Pessoa
- ✅ **Classes concretas**: Funcionario, Dependente, FolhaPagamento
- ✅ **Encapsulamento**: Todos os atributos privados com getters/setters
- ✅ **Modificadores de Acesso**: private, public, protected
- ✅ **Exceções**: DependenteException personalizada
- ✅ **Enum**: Parentesco (FILHO, SOBRINHO, OUTROS)
- ✅ **Arquivos**: Processamento de CSV
- ✅ **LocalDate**: Para datas de nascimento e pagamento
- ✅ **Coleções**: ArrayList, HashSet
- ✅ **Herança**: Funcionario e Dependente herdam de Pessoa
- ✅ **Separação por pacotes**: model, service, dao, exception, enums

## Estrutura do Projeto
```
ProjetoGabriel/
├── src/main/java/br/com/serratec/projetogabriel/
│   ├── model/           # Classes de modelo
│   │   ├── Pessoa.java
│   │   ├── Funcionario.java
│   │   ├── Dependente.java
│   │   └── FolhaPagamento.java
│   ├── enums/           # Enumerações
│   │   └── Parentesco.java
│   ├── exception/       # Exceções customizadas
│   │   └── DependenteException.java
│   ├── service/         # Interfaces e serviços
│   │   ├── INSSCalculavel.java
│   │   ├── IRCalculavel.java
│   │   ├── ArquivoProcessavel.java
│   │   ├── INSSCalculadoraService.java
│   │   ├── IRCalculadoraService.java
│   │   ├── ArquivoProcessadorService.java
│   │   └── FolhaPagamentoService.java
│   ├── dao/             # Acesso a dados
│   │   ├── DatabaseConnection.java
│   │   ├── FuncionarioDAO.java
│   │   ├── DependenteDAO.java
│   │   └── FolhaPagamentoDAO.java
│   └── ProjetoGabrielApplication.java
├── docs/                # Documentação
│   └── UML_DiagramaClasses.md
├── database_schema.sql  # Script de criação do banco
├── funcionarios.csv     # Arquivo de exemplo
└── README.md
```

## Pré-requisitos
- Java 8 ou superior
- PostgreSQL (opcional - para funcionalidades de banco de dados)

## Como Compilar
```bash
cd ProjetoGabriel
javac -d bin -cp src/main/java src/main/java/br/com/serratec/projetogabriel/*.java src/main/java/br/com/serratec/projetogabriel/*/*.java
```

## Como Executar
```bash
cd ProjetoGabriel
java -cp bin br.com.serratec.projetogabriel.ProjetoGabrielApplication
```

## Formato do Arquivo de Entrada (CSV)
O arquivo deve seguir o padrão CSV com separador ponto-e-vírgula (;):

### Para Funcionários:
```
<Nome_do_funcionario>;<CPF_do_Funcionario>;<DataNascimento_YYYYMMDD>;<salarioBruto>
```

### Para Dependentes:
```
<Nome_do_dependente>;<CPF_do_dependente>;<DataNascimento_YYYYMMDD>;<Parentesco>
```

### Exemplo:
```
João Silva;12345678901;19850315;5000.00
Maria Silva;12345678902;20100520;FILHO
Pedro Silva;12345678903;20120810;FILHO

Ana Santos;23456789012;19900222;8000.00
Carlos Santos;23456789013;20150330;SOBRINHO
```

**Observações:**
- Linhas em branco separam grupos funcionário/dependentes
- CPF deve ter 11 dígitos
- Data no formato YYYYMMDD
- Salário com ponto decimal
- Parentesco: FILHO, SOBRINHO ou OUTROS

## Formato do Arquivo de Saída (CSV)
```
<Nome_do_funcionario>;<CPF_do_Funcionario>;<Desconto_INSS>;<Desconto_IR>;<SalarioLiquido>
```

### Exemplo:
```
João Silva;12345678901;518.82;260.18;4221.00
Ana Santos;23456789012;908.86;1001.93;6089.21
```

## Cálculos Implementados

### INSS
Tabela progressiva aplicada sobre o salário bruto:
- Até R$ 1.412,00: 7,5%
- De R$ 1.412,01 até R$ 2.666,68: 9%
- De R$ 2.666,69 até R$ 4.000,03: 12%
- De R$ 4.000,04 até R$ 7.786,02: 14%
- Teto de contribuição: R$ 8.157,41

### Imposto de Renda
Base de cálculo: Salário Bruto - INSS - (Dependentes × R$ 189,59)

Tabela progressiva:
- Até R$ 2.259,00: Isento
- De R$ 2.259,21 até R$ 2.826,65: 7,5% (dedução R$ 169,44)
- De R$ 2.826,66 até R$ 3.751,05: 15% (dedução R$ 381,44)
- De R$ 3.751,06 até R$ 4.664,68: 22,5% (dedução R$ 662,77)
- Acima de R$ 4.664,68: 27,5% (dedução R$ 896,00)

## Regras de Validação
1. **Funcionários**: CPF único (não pode haver duplicatas)
2. **Dependentes**: 
   - CPF único (não pode haver duplicatas)
   - Deve ser menor de 18 anos
   - Parentesco deve ser FILHO, SOBRINHO ou OUTROS
3. **Arquivos**: Formato CSV válido conforme especificação

## Banco de Dados (Opcional)
O sistema inclui integração com PostgreSQL. Para utilizar:

1. Execute o script `database_schema.sql` para criar as tabelas
2. Configure a conexão em `DatabaseConnection.java`
3. As classes DAO estão prontas para persistir os dados

### Tabelas criadas:
- `funcionario`: Dados dos funcionários
- `dependente`: Dados dos dependentes
- `folha_pagamento`: Registros de folha processados

## Exemplos de Uso

### Execução Básica
1. Prepare um arquivo CSV com funcionários e dependentes
2. Execute a aplicação
3. Informe o nome do arquivo de entrada
4. Informe o nome do arquivo de saída
5. O sistema processará e gerará o relatório

### Resultado da Execução
```
=== SISTEMA DE CÁLCULO DE FOLHA DE PAGAMENTO ===

Digite o nome do arquivo de entrada: funcionarios.csv
Digite o nome do arquivo de saída: folha_pagamento.csv

Processando arquivo de entrada...
Funcionários carregados: 3
Calculando folha de pagamento...

=== RESUMO DOS CÁLCULOS ===
Funcionário: João Silva (CPF: 12345678901)
  Salário Bruto: R$ 5000.00
  Dependentes: 2
  Desconto INSS: R$ 518.82
  Desconto IR: R$ 260.18
  Salário Líquido: R$ 4221.00

Processo concluído com sucesso!
```

## Tratamento de Erros
- CPFs duplicados geram exceções
- Dependentes maiores de 18 anos geram DependenteException
- Arquivos malformados geram exceções de parsing
- Erros de banco de dados são tratados adequadamente

## Autor
Desenvolvido para o curso Serratec como projeto de demonstração de conceitos Java aplicados a um sistema real de folha de pagamento.

## Licença
Este projeto é para fins educacionais.