create table if not exists resposta(
    id bigint primary key auto_increment,
    mensagem varchar(255) not null,
    topico_id bigint not null,
    data_criacao date not null default (CURRENT_DATE()),
    autor_id bigint not null,
    solucao varchar(255) not null,
    constraint fk_resposta_topico foreign key (topico_id) references topico(id),
    constraint fk_resposta_usuario foreign key (autor_id) references usuario(id)
);