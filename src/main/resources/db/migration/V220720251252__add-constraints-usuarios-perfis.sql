alter table usuarios_perfis
add constraint fk_usuarios_perfis_usuario
    foreign key (id_usuario) references usuario(id);

alter table usuarios_perfis
add constraint fk_usuarios_perfis_perfil
    foreign key (id_perfil) references perfil(id);