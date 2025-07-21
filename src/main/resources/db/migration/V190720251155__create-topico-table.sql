create table if not exists topico(
    id bigint primary key auto_increment,
    titulo varchar(255) not null,
    mensagem varchar(255) not null,
    data_criacao date default (CURRENT_DATE()),
    status varchar(255) not null,
    autor_id bigint not null,
    curso_id bigint not null,

    constraint fk_topico_autor foreign key (autor_id) references usuario(id),
    constraint fk_topico_curso foreign key (curso_id) references curso(id)
);