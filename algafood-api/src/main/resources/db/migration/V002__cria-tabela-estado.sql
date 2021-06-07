create table estado (
	id bigint not null auto_increment,
    nome varchar(60) not null,
    sigla varchar(2),
    primary key (id)
) engine = InnoDB default charset=utf8;