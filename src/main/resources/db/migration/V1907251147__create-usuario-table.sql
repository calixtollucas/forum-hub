create table if not exists usuario(
    id bigint primary key auto_increment,
    nome varchar(255) not null,
    email varchar(255) not null unique,
    senha varchar(255) not null
);