alter table tb_attendances
drop constraint if exists fk_attendances_dog,
add constraint fk_attendances_dog
    foreign key (dog_id)
    references tb_dogs(id)
    on delete cascade;

alter table tb_attendances
drop constraint if exists fk_attendances_client,
add constraint fk_attendances_client
    foreign key (client_id)
    references tb_clients(id)
    on delete cascade;

alter table tb_attendances
drop constraint if exists fk_attendances_vet,
add constraint fk_attendances_vet
    foreign key (vet_id)
    references tb_vets(id)
    on delete cascade;
