-- -- CREATE TABLE conta
-- -- (
-- --     id_conta IDENTITY NOT NULL PRIMARY KEY,
-- --     nome_responsavel VARCHAR(50) NOT NULL
-- -- );


-- -- CREATE TABLE transferencia
-- -- (
-- --     id IDENTITY NOT NULL PRIMARY KEY,
-- --     data_transferencia TIMESTAMP WITH TIME ZONE NOT NULL,
-- --     valor NUMERIC (20,2) NOT NULL,
-- --     tipo VARCHAR(15) NOT NULL,
-- --     nome_operador_transacao VARCHAR (50),
-- --     conta_id INT NOT NULL,

-- --         CONSTRAINT FK_CONTA
-- --         FOREIGN KEY (conta_id)
-- --         REFERENCES conta(id_conta)
-- -- );

INSERT INTO conta (id, nome) VALUES (1,'Fulano');
INSERT INTO conta (id, nome) VALUES (2,'Sicrano');

INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (1,'2019-01-01',30895.46,'DEPOSITO', null, 1);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (2,'2019-02-03',12.24,'DEPOSITO', null,2);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (3,'2019-05-04',-500.50,'SAQUE', null,1);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (4,'2019-08-07',-530.50,'SAQUE', null,2);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (5,'2019-10-09',3241.23,'TRANSFERENCIA', 'Beltrano',1);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (6,'2019-04-01',25173.09,'TRANSFERENCIA', 'Ronnyscley',2);
