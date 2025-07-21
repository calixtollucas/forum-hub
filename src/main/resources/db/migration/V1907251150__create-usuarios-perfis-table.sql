create table if not exists usuarios_perfis(
    id bigint primary key auto_increment,
    id_usuario bigint not null,
    id_perfil int not null
);