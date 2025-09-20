-- Script de criação das tabelas para o sistema de folha de pagamento

-- Criar banco de dados
CREATE DATABASE folha_pagamento;

-- Usar o banco de dados
\c folha_pagamento;

-- Tabela de funcionários
CREATE TABLE funcionario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    salario_bruto DECIMAL(10,2) NOT NULL,
    desconto_inss DECIMAL(10,2) DEFAULT 0.00,
    desconto_ir DECIMAL(10,2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de dependentes
CREATE TABLE dependente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    parentesco VARCHAR(20) NOT NULL CHECK (parentesco IN ('FILHO', 'SOBRINHO', 'OUTROS')),
    cpf_funcionario VARCHAR(11) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cpf_funcionario) REFERENCES funcionario(cpf)
);

-- Tabela de folha de pagamento
CREATE TABLE folha_pagamento (
    codigo SERIAL PRIMARY KEY,
    cpf_funcionario VARCHAR(11) NOT NULL,
    data_pagamento DATE NOT NULL,
    desconto_inss DECIMAL(10,2) NOT NULL,
    desconto_ir DECIMAL(10,2) NOT NULL,
    salario_liquido DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cpf_funcionario) REFERENCES funcionario(cpf)
);

-- Índices para melhorar performance
CREATE INDEX idx_funcionario_cpf ON funcionario(cpf);
CREATE INDEX idx_dependente_cpf ON dependente(cpf);
CREATE INDEX idx_dependente_funcionario ON dependente(cpf_funcionario);
CREATE INDEX idx_folha_pagamento_funcionario ON folha_pagamento(cpf_funcionario);
CREATE INDEX idx_folha_pagamento_data ON folha_pagamento(data_pagamento);

-- Comentários nas tabelas
COMMENT ON TABLE funcionario IS 'Tabela que armazena informações dos funcionários';
COMMENT ON TABLE dependente IS 'Tabela que armazena informações dos dependentes dos funcionários';
COMMENT ON TABLE folha_pagamento IS 'Tabela que armazena os registros de folha de pagamento processados';

-- Comentários nas colunas
COMMENT ON COLUMN funcionario.cpf IS 'CPF do funcionário (chave única)';
COMMENT ON COLUMN dependente.cpf IS 'CPF do dependente (chave única)';
COMMENT ON COLUMN dependente.parentesco IS 'Tipo de parentesco: FILHO, SOBRINHO ou OUTROS';
COMMENT ON COLUMN folha_pagamento.codigo IS 'Código único da folha de pagamento';