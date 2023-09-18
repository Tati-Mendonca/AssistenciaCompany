CREATE TABLE TBL_SERVICO
(
    ID_SERVICO   SERIAL PRIMARY KEY,
    TX_DESCRICAO VARCHAR(80) NOT NULL,
    NR_VALOR     NUMERIC(19,4) NOT NULL
);

INSERT INTO TBL_SERVICO(TX_DESCRICAO,NR_VALOR) VALUES ('Formatação de Sistemas Operacionais',50.00);
INSERT INTO TBL_SERVICO(TX_DESCRICAO,NR_VALOR) VALUES ('Instalação do Microsoft Windows',100.00);
INSERT INTO TBL_SERVICO(TX_DESCRICAO,NR_VALOR) VALUES ('Backup de arquivos e e-mails',50.00);
INSERT INTO TBL_SERVICO(TX_DESCRICAO,NR_VALOR) VALUES ('Instalação de programas diversos',30.00);
INSERT INTO TBL_SERVICO(TX_DESCRICAO,NR_VALOR) VALUES ('Remoção de vírus e ameaças',80.00);
INSERT INTO TBL_SERVICO(TX_DESCRICAO,NR_VALOR) VALUES ('Upgrade de peças e componentes',120.00);
INSERT INTO TBL_SERVICO(TX_DESCRICAO,NR_VALOR) VALUES ('Reparos em Placa-mãe',200.00);
INSERT INTO TBL_SERVICO(TX_DESCRICAO,NR_VALOR) VALUES ('Limpeza e lubrificação interna',100.00);
INSERT INTO TBL_SERVICO(TX_DESCRICAO,NR_VALOR) VALUES ('Configuração de periféricos',40.00);
INSERT INTO TBL_SERVICO(TX_DESCRICAO,NR_VALOR) VALUES ('Correção de lentidão e travamentos',80.00);
INSERT INTO TBL_SERVICO(TX_DESCRICAO,NR_VALOR) VALUES ('Indicação para upgrade e substituição de peças',30.00);
INSERT INTO TBL_SERVICO(TX_DESCRICAO,NR_VALOR) VALUES ('Configuração de roteador wireless',15.00);

CREATE TABLE TBL_TECNICO
(
    ID_TECNICO   SERIAL PRIMARY KEY,
    TX_NOME      VARCHAR(45) NOT NULL,
    TX_AREA_FONE VARCHAR(2),
    TX_FONE      VARCHAR(9),
    TX_TIPO_FONE VARCHAR(11),
    TX_EMAIL     VARCHAR(50)
);

INSERT INTO TBL_TECNICO(TX_NOME,TX_EMAIL) VALUES ('Rafaela Buainain Luiz','rafa.luiz@assistenciacompany.com');
INSERT INTO TBL_TECNICO(TX_NOME,TX_EMAIL) VALUES ('Diogo Castro Lopes','diogo.lopes@assistenciacompany.com');
INSERT INTO TBL_TECNICO(TX_NOME,TX_EMAIL) VALUES ('Felipe Kenji Yoshida','felipe.yoshida@assistenciacompany.com');
INSERT INTO TBL_TECNICO(TX_NOME,TX_EMAIL) VALUES ('Maria Luiza De Sa Barros','maria.barros@assistenciacompany.com');
INSERT INTO TBL_TECNICO(TX_NOME,TX_EMAIL) VALUES ('Thiago Costa Torres','thiago.torres@assistenciacompany.com');