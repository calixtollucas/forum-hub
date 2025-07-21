create table if not exists perfil(
    id int primary key auto_increment,
    nome varchar(100) not null unique
);