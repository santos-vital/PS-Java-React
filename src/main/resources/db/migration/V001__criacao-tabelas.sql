CREATE TABLE conta
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    
    PRIMARY KEY(id)
)engine=InnoDB default charset=utf8mb4;


CREATE TABLE transferencia
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    dataTransferencia DATETIME NOT NULL,
    valor NUMERIC (20,2) NOT NULL,
    tipo VARCHAR(15) NOT NULL,
    nomeOperadorTransacao VARCHAR (50),
    conta_id BIGINT NOT NULL,
    
    PRIMARY KEY(id),

	CONSTRAINT FK_CONTA FOREIGN KEY (conta_id) REFERENCES conta (id)
)engine=InnoDB default charset=utf8mb4;