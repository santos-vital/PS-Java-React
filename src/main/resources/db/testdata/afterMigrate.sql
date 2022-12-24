SET SQL_SAFE_UPDATES = 0;

set foreign_key_checks = 0;

delete from conta;
delete from transferencia;

set foreign_key_checks = 1;

alter table conta auto_increment = 1;
alter table transferencia auto_increment = 1;

INSERT INTO conta (id, nome) VALUES (1,'Fulano');
INSERT INTO conta (id, nome) VALUES (2,'Sicrano');

INSERT INTO transferencia (id,dataTransferencia, valor, tipo, nomeOperadorTransacao, conta_id) VALUES (1,'2019-01-01',30895.46,'DEPOSITO', null, 1);
INSERT INTO transferencia (id,dataTransferencia, valor, tipo, nomeOperadorTransacao, conta_id) VALUES (2,'2019-02-03',12.24,'DEPOSITO', null,2);
INSERT INTO transferencia (id,dataTransferencia, valor, tipo, nomeOperadorTransacao, conta_id) VALUES (3,'2019-05-04',-500.50,'SAQUE', null,1);
INSERT INTO transferencia (id,dataTransferencia, valor, tipo, nomeOperadorTransacao, conta_id) VALUES (4,'2019-08-07',-530.50,'SAQUE', null,2);
INSERT INTO transferencia (id,dataTransferencia, valor, tipo, nomeOperadorTransacao, conta_id) VALUES (5,'2019-10-09',3241.23,'TRANSFERENCIA', 'Beltrano',1);
INSERT INTO transferencia (id,dataTransferencia, valor, tipo, nomeOperadorTransacao, conta_id) VALUES (6,'2019-04-01',25173.09,'TRANSFERENCIA', 'Ronnyscley',2);
