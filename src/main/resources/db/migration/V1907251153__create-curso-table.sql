create table if not exists curso(
    id bigint primary key auto_increment,
    nome varchar(255) not null unique,
    categoria varchar(255) not null
);