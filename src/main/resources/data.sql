create table tb_cidade(
    id bigint not null primary key,
    nome varchar (50) not null,
    qtd_habitantes bigint
);

insert into tb_cidade
    (id, nome, qtd_habitantes)
values
    (1, 'São Paulo', 11451245),
    (2, 'Fortaleza', 2428678),
    (3, 'Rio de Janeiro', 16054524),
    (4, 'Salvador', 2418005),
    (5, 'tocantins', 1511459),
    (6, 'Brasilia', 2817068),
    (7, 'Belo Horizonte', 2315560)
    ;

--select * from tb_cidade where nome like '%a'