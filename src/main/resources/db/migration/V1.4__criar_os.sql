CREATE TABLE TBL_ORDEM_SERVICO
(
    ID_ORDEM_SERVICO SERIAL PRIMARY KEY,
    ID_EQUIPAMENTO   INTEGER       NOT NULL,
    ID_CLIENTE       INTEGER       NOT NULL,
    DT_ENTRADA       TIMESTAMP(6) NOT NULL,
    DT_PREVISAO      TIMESTAMP(6),
    TX_DEFEITO       VARCHAR(100) NOT NULL,
    NR_PRIORIDADE    TINYINT NOT NULL,
    DT_SAIDA         TIMESTAMP(6),
    TX_STATUS        VARCHAR(12) NOT NULL,
    FOREIGN KEY (ID_EQUIPAMENTO) REFERENCES TBL_EQUIPAMENTO (ID_EQUIPAMENTO),
    FOREIGN KEY (ID_CLIENTE) REFERENCES TBL_CLIENTE (ID_CLIENTE)
);

INSERT INTO TBL_ORDEM_SERVICO(ID_EQUIPAMENTO, ID_CLIENTE, DT_ENTRADA, DT_PREVISAO, TX_DEFEITO, NR_PRIORIDADE, DT_SAIDA, TX_STATUS) VALUES (1, 1, '2023-11-23 09:01:43', '2023-11-25 14:13:09', 'Computador não liga', 0, '2023-11-25 14:13:09', 'CONCLUIDO');
INSERT INTO TBL_ORDEM_SERVICO(ID_EQUIPAMENTO, ID_CLIENTE, DT_ENTRADA, DT_PREVISAO, TX_DEFEITO, NR_PRIORIDADE, DT_SAIDA, TX_STATUS) VALUES (2, 2, '2023-11-15 11:32:23', '2023-11-15 11:32:23', 'Troca de memória', 2, NULL, 'BLOQUEADO');
INSERT INTO TBL_ORDEM_SERVICO(ID_EQUIPAMENTO, ID_CLIENTE, DT_ENTRADA, DT_PREVISAO, TX_DEFEITO, NR_PRIORIDADE, DT_SAIDA, TX_STATUS) VALUES (3, 3, '2023-11-25 14:12:37', '2023-11-30 18:00:00', 'Instalação de programa anti-vírus', 1, '2023-11-30 16:17:21', 'ABERTO');

CREATE TABLE TBL_REL_OS_TECNICO
(
    ID_ORDEM_SERVICO INTEGER NOT NULL,
    ID_TECNICO       INTEGER NOT NULL,
    FOREIGN KEY (ID_ORDEM_SERVICO) REFERENCES TBL_ORDEM_SERVICO (ID_ORDEM_SERVICO),
    FOREIGN KEY (ID_TECNICO) REFERENCES TBL_TECNICO (ID_TECNICO)
);

INSERT INTO TBL_REL_OS_TECNICO(ID_ORDEM_SERVICO,ID_TECNICO) VALUES (1,1);
INSERT INTO TBL_REL_OS_TECNICO(ID_ORDEM_SERVICO,ID_TECNICO) VALUES (1,2);
INSERT INTO TBL_REL_OS_TECNICO(ID_ORDEM_SERVICO,ID_TECNICO) VALUES (2,1);

CREATE TABLE TBL_ITEM_SERVICO
(
    ID_ITEM_SERVICO  SERIAL PRIMARY KEY,
    ID_ORDEM_SERVICO INTEGER NOT NULL,
    ID_SERVICO       INTEGER NOT NULL,
    NR_VALOR         NUMERIC(19,4) NOT NULL,
    FOREIGN KEY (ID_ORDEM_SERVICO) REFERENCES TBL_ORDEM_SERVICO (ID_ORDEM_SERVICO),
    FOREIGN KEY (ID_SERVICO) REFERENCES TBL_SERVICO (ID_SERVICO)
);

INSERT INTO TBL_ITEM_SERVICO(ID_ORDEM_SERVICO,ID_SERVICO,NR_VALOR) VALUES (1,3,50.00);
INSERT INTO TBL_ITEM_SERVICO(ID_ORDEM_SERVICO,ID_SERVICO,NR_VALOR) VALUES (1,1,50.00);
INSERT INTO TBL_ITEM_SERVICO(ID_ORDEM_SERVICO,ID_SERVICO,NR_VALOR) VALUES (1,2,100.00);
INSERT INTO TBL_ITEM_SERVICO(ID_ORDEM_SERVICO,ID_SERVICO,NR_VALOR) VALUES (1,4,30.00);
INSERT INTO TBL_ITEM_SERVICO(ID_ORDEM_SERVICO,ID_SERVICO,NR_VALOR) VALUES (2,11,30.00);

CREATE TABLE TBL_OBSERVACAO
(
    ID_OBSERVACAO    SERIAL PRIMARY KEY,
    DT_OBSERVACAO    TIMESTAMP(6) NOT NULL,
    TX_OBSERVACAO    VARCHAR(100) NOT NULL,
    ID_ORDEM_SERVICO INTEGER       NOT NULL,
    FOREIGN KEY (ID_ORDEM_SERVICO) REFERENCES TBL_ORDEM_SERVICO (ID_ORDEM_SERVICO)
);

INSERT INTO TBL_OBSERVACAO(DT_OBSERVACAO, TX_OBSERVACAO, ID_ORDEM_SERVICO) VALUES ('2023-07-03 13:11:12', 'Foi feita uma tentativa de iniciar em Modo de Segurança', 1);
INSERT INTO TBL_OBSERVACAO(DT_OBSERVACAO, TX_OBSERVACAO, ID_ORDEM_SERVICO) VALUES ('2023-07-15 16:39:30', 'Orçamento de peças pendente de aprovação pelo cliente', 2);