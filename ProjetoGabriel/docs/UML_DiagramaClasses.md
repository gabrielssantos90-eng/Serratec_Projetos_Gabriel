# UML - Diagrama de Classes do Sistema de Folha de Pagamento

## Visão Geral
Este documento apresenta o diagrama de classes do sistema ProjetoGabriel, desenvolvido para calcular a folha de pagamento de funcionários.

## Classes Principais

### 1. Classe Abstrata Pessoa
```
<<abstract>> Pessoa
-------------------
- nome: String
- cpf: String
- dataNascimento: LocalDate
-------------------
+ Pessoa()
+ Pessoa(nome, cpf, dataNascimento)
+ getNome(): String
+ setNome(String): void
+ getCpf(): String
+ setCpf(String): void
+ getDataNascimento(): LocalDate
+ setDataNascimento(LocalDate): void
+ equals(Object): boolean
+ hashCode(): int
+ toString(): String
```

### 2. Classe Funcionario (herda de Pessoa)
```
Funcionario
-------------------
- salarioBruto: BigDecimal
- descontoInss: BigDecimal
- descontoIR: BigDecimal
- dependentes: List<Dependente>
-------------------
+ Funcionario()
+ Funcionario(nome, cpf, dataNascimento, salarioBruto)
+ adicionarDependente(Dependente): void
+ calcularSalarioLiquido(): BigDecimal
+ getQuantidadeDependentes(): int
+ getValorTotalDeducoesDependentes(): BigDecimal
+ getSalarioBruto(): BigDecimal
+ setSalarioBruto(BigDecimal): void
+ getDescontoInss(): BigDecimal
+ setDescontoInss(BigDecimal): void
+ getDescontoIR(): BigDecimal
+ setDescontoIR(BigDecimal): void
+ getDependentes(): List<Dependente>
+ setDependentes(List<Dependente>): void
+ toString(): String
```

### 3. Classe Dependente (herda de Pessoa)
```
Dependente
-------------------
- parentesco: Parentesco
-------------------
+ Dependente()
+ Dependente(nome, cpf, dataNascimento, parentesco) throws DependenteException
- validarIdade(LocalDate): void throws DependenteException
+ getValorDeducaoIR(): double
+ getParentesco(): Parentesco
+ setParentesco(Parentesco): void
+ toString(): String
```

### 4. Enum Parentesco
```
<<enumeration>> Parentesco
-------------------
FILHO
SOBRINHO
OUTROS
```

### 5. Classe FolhaPagamento
```
FolhaPagamento
-------------------
- codigo: Long
- funcionario: Funcionario
- dataPagamento: LocalDate
- descontoINSS: BigDecimal
- descontoIR: BigDecimal
- salarioLiquido: BigDecimal
-------------------
+ FolhaPagamento()
+ FolhaPagamento(funcionario, dataPagamento, descontoINSS, descontoIR, salarioLiquido)
+ getCodigo(): Long
+ setCodigo(Long): void
+ getFuncionario(): Funcionario
+ setFuncionario(Funcionario): void
+ getDataPagamento(): LocalDate
+ setDataPagamento(LocalDate): void
+ getDescontoINSS(): BigDecimal
+ setDescontoINSS(BigDecimal): void
+ getDescontoIR(): BigDecimal
+ setDescontoIR(BigDecimal): void
+ getSalarioLiquido(): BigDecimal
+ setSalarioLiquido(BigDecimal): void
+ toString(): String
```

### 6. Exceção DependenteException
```
DependenteException
-------------------
+ DependenteException(String)
+ DependenteException(String, Throwable)
```

## Interfaces

### 1. Interface INSSCalculavel
```
<<interface>> INSSCalculavel
-------------------
+ calcularINSS(BigDecimal): BigDecimal
```

### 2. Interface IRCalculavel
```
<<interface>> IRCalculavel
-------------------
+ calcularIR(BigDecimal, BigDecimal, BigDecimal): BigDecimal
```

### 3. Interface ArquivoProcessavel
```
<<interface>> ArquivoProcessavel
-------------------
+ lerArquivo(String): List<Funcionario> throws Exception
+ escreverArquivo(List<Funcionario>, String): void throws Exception
```

## Classes de Serviço

### 1. INSSCalculadoraService (implementa INSSCalculavel)
```
INSSCalculadoraService
-------------------
- FAIXA1_LIMITE: BigDecimal
- FAIXA2_LIMITE: BigDecimal
- FAIXA3_LIMITE: BigDecimal
- FAIXA4_LIMITE: BigDecimal
- TETO_INSS: BigDecimal
- ALIQUOTA1: BigDecimal
- ALIQUOTA2: BigDecimal
- ALIQUOTA3: BigDecimal
- ALIQUOTA4: BigDecimal
-------------------
+ calcularINSS(BigDecimal): BigDecimal
```

### 2. IRCalculadoraService (implementa IRCalculavel)
```
IRCalculadoraService
-------------------
- FAIXA_ISENCAO: BigDecimal
- FAIXA2_LIMITE: BigDecimal
- FAIXA3_LIMITE: BigDecimal
- FAIXA4_LIMITE: BigDecimal
- ALIQUOTA2: BigDecimal
- ALIQUOTA3: BigDecimal
- ALIQUOTA4: BigDecimal
- ALIQUOTA5: BigDecimal
- DEDUCAO2: BigDecimal
- DEDUCAO3: BigDecimal
- DEDUCAO4: BigDecimal
- DEDUCAO5: BigDecimal
-------------------
+ calcularIR(BigDecimal, BigDecimal, BigDecimal): BigDecimal
```

### 3. ArquivoProcessadorService (implementa ArquivoProcessavel)
```
ArquivoProcessadorService
-------------------
- DATE_FORMATTER: DateTimeFormatter
- cpfsFuncionarios: Set<String>
- cpfsDependentes: Set<String>
-------------------
+ lerArquivo(String): List<Funcionario> throws Exception
+ escreverArquivo(List<Funcionario>, String): void throws Exception
- processarFuncionario(String[]): Funcionario throws Exception
- processarDependente(String[]): Dependente throws Exception
```

### 4. FolhaPagamentoService
```
FolhaPagamentoService
-------------------
- inssCalculadora: INSSCalculavel
- irCalculadora: IRCalculavel
-------------------
+ FolhaPagamentoService()
+ processarFolhaPagamento(List<Funcionario>, LocalDate): List<FolhaPagamento>
```

## Classes DAO

### 1. DatabaseConnection
```
DatabaseConnection
-------------------
- URL: String
- USER: String
- PASSWORD: String
-------------------
+ getConnection(): Connection throws SQLException
+ testConnection(): void
```

### 2. FuncionarioDAO
```
FuncionarioDAO
-------------------
+ inserir(Funcionario): void throws SQLException
+ existeCpf(String): boolean throws SQLException
+ listarTodos(): List<Funcionario> throws SQLException
```

### 3. DependenteDAO
```
DependenteDAO
-------------------
+ inserir(Dependente, String): void throws SQLException
+ existeCpf(String): boolean throws SQLException
+ listarPorFuncionario(String): List<Dependente> throws SQLException
```

### 4. FolhaPagamentoDAO
```
FolhaPagamentoDAO
-------------------
+ inserir(FolhaPagamento): void throws SQLException
+ listarTodos(): List<FolhaPagamento> throws SQLException
```

## Classe Principal

### ProjetoGabrielApplication
```
ProjetoGabrielApplication
-------------------
+ main(String[]): void
```

## Relacionamentos

1. **Herança:**
   - Funcionario herda de Pessoa
   - Dependente herda de Pessoa

2. **Composição:**
   - Funcionario possui lista de Dependentes
   - FolhaPagamento possui um Funcionario

3. **Implementação:**
   - INSSCalculadoraService implementa INSSCalculavel
   - IRCalculadoraService implementa IRCalculavel
   - ArquivoProcessadorService implementa ArquivoProcessavel

4. **Dependência:**
   - FolhaPagamentoService depende de INSSCalculavel e IRCalculavel
   - Todas as classes DAO dependem de DatabaseConnection

5. **Uso:**
   - Dependente usa enum Parentesco
   - Dependente pode lançar DependenteException

## Pacotes

- **br.com.serratec.projetogabriel.model**: Classes de modelo (Pessoa, Funcionario, Dependente, FolhaPagamento)
- **br.com.serratec.projetogabriel.enums**: Enumerações (Parentesco)
- **br.com.serratec.projetogabriel.exception**: Exceções customizadas (DependenteException)
- **br.com.serratec.projetogabriel.service**: Interfaces e classes de serviço
- **br.com.serratec.projetogabriel.dao**: Classes de acesso a dados
- **br.com.serratec.projetogabriel**: Classe principal da aplicação