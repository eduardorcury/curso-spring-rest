create table ordem_servico (
    id bigint not null AUTO_INCREMENT,
    cliente_id bigint not null,
    descricao text not null,
    preco decimal(10,2) not null,
    status varchar(20) not null,
    data_abertura datetime not null,
    data_finalizacao datetime,
    
    primary key (id)
);

alter table ordem_servico add CONSTRAINT fk_ordem_servico_cliente
FOREIGN KEY (cliente_id) REFERENCES cliente (id);