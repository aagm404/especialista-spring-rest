-- delete dos dados existes
set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;

set foreign_key_checks = 1;

-- recontagem do auto_increment
alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;

-- insercao de dados
insert into cozinha (nome) values ('Tailandesa'), ('Indiana'), ('Francesa'), ('Alemã');

insert into forma_pagamento (descricao) values ('Dinheiro'), ('Transferência'), ('Cartão Crédito'), ('Cartão Débito'), ('Vale Refeição'), ('Vale Alimentação'), ('Pagamento pelo App');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into estado (nome, sigla) values ('São Paulo', 'SP');
insert into estado (nome, sigla) values ('Mato Grosso', 'MT');
insert into estado (nome, sigla) values ('Ceará', 'CE');
insert into estado (nome, sigla) values ('Maranhão', 'MA');
insert into estado (nome, sigla) values ('Rio de Janeiro', 'RJ');
insert into estado (nome, sigla) values ('Santa Catarina', 'SC');

insert into cidade (nome, estado_id) values ('São Paulo', (select id from estado where sigla = 'SP'));
insert into cidade (nome, estado_id) values ('São José do Rio Preto', (select id from estado where sigla = 'SP'));
insert into cidade (nome, estado_id) values ('Mauá', (select id from estado where sigla = 'SP'));
insert into cidade (nome, estado_id) values ('São Luís', (select id from estado where sigla = 'MA'));
insert into cidade (nome, estado_id) values ('Fortaleza', (select id from estado where sigla = 'CE'));
insert into cidade (nome, estado_id) values ('Cuiabá', (select id from estado where sigla = 'MT'));
insert into cidade (nome, estado_id) values ('Rio de Janeiro', (select id from estado where sigla = 'RJ'));
insert into cidade (nome, estado_id) values ('Resende', (select id from estado where sigla = 'RJ'));
insert into cidade (nome, estado_id) values ('Florianópolis', (select id from estado where sigla = 'SC'));
insert into cidade (nome, estado_id) values ('Balneário Camboriú', (select id from estado where sigla = 'SC'));
insert into cidade (nome, estado_id) values ('Penha', (select id from estado where sigla = 'SC'));

insert into restaurante (data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id) values (utc_timestamp, utc_timestamp, 'Mooca', '03000-000', '', 'Rua da Mooca', '500', 'Disque comida Tailandesa', 2.50, (select id from cozinha where nome = 'Tailandesa'), (select id from cidade where nome = 'São Paulo'));
insert into restaurante (data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id) values (utc_timestamp, utc_timestamp, 'Jardim Bordon', '15000-000', 'Lj 3', 'Rua Emília Tarraf', '306', 'Thai Gourmet', 10.00, (select id from cozinha where nome = 'Tailandesa'), (select id from cidade where nome = 'São José do Rio Preto'));
insert into restaurante (data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id) values (utc_timestamp, utc_timestamp, 'Lapa', '00000-000', '', 'Rua Alguma Rua da Lapa', '984', 'Thai Delivery', 9.50, (select id from cozinha where nome = 'Tailandesa'), (select id from cidade where nome = 'Rio de Janeiro'));
insert into restaurante (data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id) values (utc_timestamp, utc_timestamp, 'Centro', '10000-000', '', 'Rua Alguma Rua do Centro', '56', 'Nova Delhi comida indiana', 5.00, (select id from cozinha where nome = 'Indiana'), (select id from cidade where nome = 'Balneário Camboriú'));
insert into restaurante (data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id) values (utc_timestamp, utc_timestamp, 'Jaguaré', '15000-000', 'Fundos', 'Avenida Jaguaré', '250', 'Soufflé e Cia', 3.75, (select id from cozinha where nome = 'Francesa'), (select id from cidade where nome = 'São Paulo'));
insert into restaurante (data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id) values (utc_timestamp, utc_timestamp, 'Centro', '03000-000', '', 'Rua Marechal Deodoro', '1090', 'O melhor Bratwurst do Brasil', 6.50, (select id from cozinha where nome = 'Alemã'), (select id from cidade where nome = 'São José do Rio Preto'));

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,3), (1,4), (1,7), (2,1), (2,5), (2,6), (3,3), (3,4), (3,7), (4,1), (4,5), (4,6), (5,3), (5,4), (5,7), (6,1), (6,5), (6,6);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Pad Thai', 'Noodles misturados num wok bem quente com rebentos de soja, camarão, ovo e cebola e temperados com molho de soja, açúcar e malaguetas', 22.50, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Som Tum', 'Salada Picante de Papaia', 27.80, true, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Gaeng Daeng', 'Mistura de leite de coco, carne e pasta de caril vermelho, com um toque de folhas de lima por cima', 35.00, true, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Samosa', 'A samosa é um aperitivo feita com uma massa semelhante à do pastel e recheio de vegetais', 24.90, true, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Escargot', 'Caracóis terrestres cozidos e servidos com molho especial, feito à base de manteiga e alho', 42.30, true, 5);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Weisswurst', 'Salsicha branca da Baviera', 20.00, true, 6);