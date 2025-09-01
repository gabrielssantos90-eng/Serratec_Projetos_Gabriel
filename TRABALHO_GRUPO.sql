-- Criação da data base
CREATE DATABASE Clinica_Odontologica;

-- Criação do schema
CREATE TABLE clinicabd;

-- Criação da tabela paciente
CREATE TABLE clinicabd.paciente (

idpaciente SERIAL PRIMARY KEY NOT NULL,
nome_completo VARCHAR (100),
cpf VARCHAR(11),
data_nasc DATE,
telefone VARCHAR(11),
email VARCHAR (50),
endereco VARCHAR (100),
hist_consult VARCHAR(100)

);

-- Criação da tabela dentista
CREATE TABLE clinicabd.dentista (

iddentista SERIAL PRIMARY KEY,
nome_completo VARCHAR (100),
cpf VARCHAR(11) UNIQUE,
CRO VARCHAR(20) UNIQUE,
especialidade VARCHAR (50),
hr_atend TIME

);

-- Criação da tabela procedimento
CREATE TABLE clinicabd.procedimentos (
idprocedimento SERIAL PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
descricao TEXT,
duracao_media_minutos INT NOT NULL

);

-- Criação da tabela consulta
CREATE TABLE clinicabd.consultas (
idconsulta SERIAL PRIMARY KEY,
paciente_id INT NOT NULL,
dentista_id INT NOT NULL,
data_hora TIMESTAMP NOT NULL,
descricao TEXT,
prescricao TEXT,
FOREIGN KEY (paciente_id) REFERENCES clinicabd.paciente(idpaciente),
FOREIGN KEY (dentista_id) REFERENCES clinicabd.dentista(iddentista)
);

--Criação da tabela procedimento
CREATE TABLE consulta_procedimentos (
consulta_id INT NOT NULL,
procedimento_id INT NOT NULL,
PRIMARY KEY (consulta_id, procedimento_id),
FOREIGN KEY (consulta_id) REFERENCES clinicabd.consultas(idconsulta),
FOREIGN KEY (procedimento_id) REFERENCES clinicabd.procedimentos(idprocedimento)
);

--Criação da tabela horário_dentista
CREATE TABLE clinicabd.horarios_dentista (
id SERIAL PRIMARY KEY,
dentista_id INT NOT NULL,
dia_da_semana VARCHAR(20) NOT NULL,
hora_inicio TIME NOT NULL,
hora_fim TIME NOT NULL,
FOREIGN KEY (dentista_id) REFERENCES clinicabd.dentista(iddentista)
);

-- Criação da tabela atendente
CREATE TABLE clinicabd.atendente(

idusuario serial NOT NULL,
nome_atendentente varchar (100) NOT NULL,
email varchar (100) NOT NULL,
login varchar (100) UNIQUE NOT NULL,
senha varchar NOT NULL
);


---Deve ser inserido, um mínimo de, 10 registros em cada tabela (INSERT);

INSERT INTO clinicabd.usuario (nome_atendentente, email, login, senha)
VALUES
('usuario 1', 'usuario1@email.com', 'usu@rio1', '123456'),
('usuario 2', 'usuario2@email.com', 'usu@rio2', '987456'),
('usuario 3', 'usuario3@email.com', 'usu@rio3', '456789'),
('usuario 4', 'usuario4@email.com', 'usu@rio4', '741258'),
('usuario 5', 'usuario5@email.com', 'usu@rio5', '936852'),
('usuario 6', 'usuario6@email.com', 'usu@rio6', '159753'),
('usuario 7', 'usuario7@email.com', 'usu@rio7', '920365'),
('usuario 8', 'usuario8@email.com', 'usu@rio8', '741369'),
('usuario 9', 'usuario9@email.com', 'usu@rio9', '987521'),
('usuario 10', 'usuario10@email.com', 'usu@rio10', '145263');



INSERT INTO clinicabd.paciente (nome_completo, cpf, data_nasc, telefone, email, endereco, hist_consult) VALUES
('Paciente 1', '11111111111', '1990-01-01', '11911111111', 'email1@.com', 'Rua A, 1', 'Consulta de rotina'),
('Paciente 2', '22222222222', '1991-02-02', '11922222222', 'email2@.com', 'Rua B, 2', 'Limpeza'),
('Paciente 3', '33333333333', '1992-03-03', '11933333333', 'email13@.com', 'Rua C, 3', 'Restauração'),
('Paciente 4', '44444444444', '1993-04-04', '11944444444', 'email4@.com', 'Rua D, 4', 'Extração'),
('Paciente 5', '55555555555', '1994-05-05', '11955555555', 'email5@.com', 'Rua E, 5', 'Canal'),
('Paciente 6', '66666666666', '1995-06-06', '11966666666', 'email6@.com', 'Rua F, 6', 'Implante'),
('Paciente 7', '77777777777', '1996-07-07', '11977777777', 'email7@.com', 'Rua G, 7', 'Clareamento'),
('Paciente 8', '88888888888', '1997-08-08', '11988888888', 'email8@.com', 'Rua H, 8', 'Ortodontia'),
('Paciente 9', '99999999999', '1998-09-09', '11999999999', 'email9@.com', 'Rua I, 9', 'Prótese'),
('Paciente 10', '10101010101', '1999-10-10', '11910101010', 'email7@.com', 'Rua J, 10', 'Check-up');

INSERT INTO clinicabd.dentista (nome_completo, cpf, CRO, especialidade, hr_atend) VALUES
('Dentista 1', '11111111111', 'CRO/SP 12345', 'Odontologia Geral', '08:00:00'),
('Dentista 2', '22222222222', 'CRO/RJ 23456', 'Ortodontia', '09:00:00'),
('Dentista 3', '33333333333', 'CRO/MG 34567', 'Endodontia', '10:00:00'),
('Dentista 4', '44444444444', 'CRO/RS 45678', 'Periodontia', '11:00:00'),
('Dentista 5', '55555555555', 'CRO/PR 56789', 'Cirurgia Oral', '12:00:00'),
('Dentista 6', '66666666666', 'CRO/SC 67890', 'Implantodontia', '13:00:00'),
('Dentista 7', '77777777777', 'CRO/BA 78901', 'Odontopediatria', '14:00:00'),
('Dentista 8', '88888888888', 'CRO/PE 89012', 'Estética Dental', '15:00:00'),
('Dentista 9', '99999999999', 'CRO/DF 90123', 'Reabilitação Oral', '16:00:00'),
('Dentista 10', '10101010101', 'CRO/GO 01234', 'Patologia Oral', '17:00:00');

INSERT INTO clinicabd.procedimentos (nome, descricao, duracao_media_minutos) VALUES
('Consulta de Rotina', 'Avaliação geral da saúde bucal e verificação de cáries.', 30),
('Limpeza Dental', 'Remoção de placa bacteriana, tártaro e manchas.', 60),
('Restauração', 'Preenchimento de cavidade causada por cárie.', 45),
('Extração', 'Remoção de dente danificado ou siso.', 90),
('Tratamento de Canal', 'Remoção de polpa infectada do dente.', 120),
('Implante Dentário', 'Inserção de pino de titânio para substituir raiz do dente.', 180),
('Clareamento Dental', 'Procedimento para branquear os dentes.', 90),
('Aplicação de Flúor', 'Prevenção de cáries, fortalecendo o esmalte dos dentes.', 20),
('Ponte Fixa', 'Substituição de dentes ausentes com prótese fixa.', 120),
('Aparelho Ortodôntico', 'Instalação de aparelho para correção de má oclusão.', 60);

INSERT INTO clinicabd.consultas (paciente_id, dentista_id, data_hora, descricao, prescricao) VALUES
(1, 1, '2025-09-01 09:00:00', 'Limpeza e avaliação de rotina.', 'Recomendado uso de enxaguante bucal.'),
(2, 2, '2025-09-01 10:30:00', 'Reavaliação ortodôntica, troca de elásticos.', 'Manter uso de elásticos contínuo.'),
(3, 3, '2025-09-02 14:00:00', 'Tratamento de canal do molar superior.', 'Tomar antibiótico por 7 dias.'),
(4, 4, '2025-09-02 16:00:00', 'Avaliação de gengivite.', 'Escovar com mais frequência.'),
(5,5, '2025-09-03 08:30:00', 'Extração de siso inferior.', 'Fazer compressa de gelo no local.'),
(6, 6, '2025-09-03 11:00:00', 'Início de tratamento de implante dentário.', 'Não ingerir alimentos duros.'),
(7, 7, '2025-09-04 15:00:00', 'Consulta de odontopediatria, aplicação de flúor.', 'Incentivar escovação após refeições.'),
(8, 8, '2025-09-04 17:00:00', 'Procedimento de clareamento dental.', 'Evitar alimentos com corante.'),
(9,9, '2025-09-05 09:30:00', 'Avaliação para prótese dentária.', 'Agendar nova consulta para moldagem.'),
(10, 10, '2025-09-05 13:00:00', 'Check-up e profilaxia.', 'Manter visitas regulares a cada 6 meses.');

INSERT INTO clinicabd.horarios_dentista (dentista_id, dia_da_semana, hora_inicio, hora_fim) VALUES
(1, 'Segunda-feira', '08:00:00', '12:00:00'),
(2, 'Terça-feira', '09:00:00', '13:00:00'),
(3, 'Quarta-feira', '10:00:00', '14:00:00'),
(4, 'Quinta-feira', '11:00:00', '15:00:00'),
(5, 'Sexta-feira', '12:00:00', '16:00:00'),
(6, 'Segunda-feira', '13:00:00', '17:00:00'),
(7, 'Terça-feira', '14:00:00', '18:00:00'),
(8, 'Quarta-feira', '15:00:00', '19:00:00'),
(9, 'Quinta-feira', '16:00:00', '20:00:00'),
(10, 'Sexta-feira', '17:00:00', '21:00:00');



---SQL de dois índices coerentes; 

CREATE UNIQUE INDEX idx_paciente_cpf ON clinicabd.paciente (cpf);
SELECT * FROM clinicabd.paciente WHERE cpf = '22222222222';

CREATE INDEX idx_consultas_data_hora ON clinicabd.consultas (data_hora);
SELECT * FROM clinicabd.consultas WHERE data_hora = '2025-09-01 09:00:00';
---SQL de 3 atualizações de registros com condições em alguma tabela.

UPDATE clinicabd.paciente
SET telefone = '222223333', email = 'novo.email5@email.com', endereco ='Rua Z, 55'
WHERE idpaciente = 35;


---SQL de 3 exclusão de registros com condições em alguma tabela.

DELETE FROM clinicabd.paciente WHERE idpaciente = 4;
DELETE FROM clinicabd.consultas WHERE paciente_id = 3;
DELETE FROM clinicabd.consultas WHERE paciente_id = 2;

---Cinco consultas contextualizadas:
---1a - Quantidade de consultas por especialidade:

SELECT
    d.especialidade,
    contagem_consultas.total_consultas
FROM
    clinicabd.dentista AS d
JOIN (
    SELECT
        dentista_id,
        COUNT(idconsulta) AS total_consultas
    FROM
        clinicabd.consultas
    GROUP BY
        dentista_id
) AS contagem_consultas ON d.iddentista = contagem_consultas.dentista_id
ORDER BY
    contagem_consultas.total_consultas DESC;


--- 2a - Quantidade de consultas realizadas por cada dentista:

SELECT
    d.nome_completo,
    COUNT(c.idconsulta) AS total_consultas
FROM
    clinicabd.dentista AS d
JOIN
    clinicabd.consultas AS c ON d.iddentista = c.dentista_id
GROUP BY
    d.nome_completo
ORDER BY
    total_consultas DESC;

--- 3a - Pacientes com maior número de consultas: 
SELECT
    p.nome_completo,
    COUNT(c.idconsulta) AS total_consultas
FROM
    clinicabd.paciente AS p
JOIN
  clinicabd.consultas AS c ON p.idpaciente = c.paciente_id
GROUP BY
    p.nome_completo
ORDER BY
    total_consultas DESC;
	
---4a - View com lista de consultas ordenadas por data:

CREATE VIEW clinicabd.consultas_ordenadas_por_data AS
SELECT
    c.idconsulta,
    p.nome_completo AS nome_paciente,
    d.nome_completo AS nome_dentista,
    c.data_hora AS data_consulta,
    c.descricao AS procedimentos_realizados
FROM
   clinicabd.consultas AS c
JOIN
    clinicabd.paciente AS p ON c.paciente_id = p.idpaciente
JOIN
    clinicabd.dentista AS d ON c.dentista_id = d.iddentista
ORDER BY
    c.data_hora DESC;

-- Consulta View
SELECT * FROM clinicabd.consultas_ordenadas_por_data;

--- 4a - View com lista de consultas ordenadas por data:

SELECT
    AVG(total_consultas) AS media_consultas_por_dentista
FROM (
    SELECT
        COUNT(c.idconsulta) AS total_consultas
    FROM
       clinicabd.consultas AS c
    GROUP BY
        c.dentista_id
) AS contagem_por_dentista;
	

select * from clinicabd.paciente