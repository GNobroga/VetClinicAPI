-- Adicionando a foreign key para a tabela tb_dogs
alter table tb_attendances
drop constraint if exists fk_attendances_dog;

alter table tb_attendances
add constraint fk_attendances_dog
    foreign key (dog_id)
    references tb_dogs(id)
    on delete cascade;

-- Adicionando a foreign key para a tabela tb_clients
alter table tb_attendances
drop constraint if exists fk_attendances_client;

alter table tb_attendances
add constraint fk_attendances_client
    foreign key (client_id)
    references tb_clients(id)
    on delete cascade;

-- Adicionando a foreign key para a tabela tb_vets
alter table tb_attendances
drop constraint if exists fk_attendances_vet;

alter table tb_attendances
add constraint fk_attendances_vet
    foreign key (vet_id)
    references tb_vets(id)
    on delete cascade;
