create table comentario (
    id bigint not null AUTO_INCREMENT,
    ordem_servico_id bigint not null,
    descricao text not null,
    data_envio datetime not null,
    
    primary key (id)
);

alter table comentario add CONSTRAINT fk_comentario_ordem_servico
FOREIGN KEY (ordem_servico_id) REFERENCES ordem_servico (id);