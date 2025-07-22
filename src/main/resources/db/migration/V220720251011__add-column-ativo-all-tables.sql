alter table usuario add column ativo tinyint default 1 not null;
alter table topico add column ativo tinyint default 1 not null;
alter table curso add column ativo tinyint default 1 not null;