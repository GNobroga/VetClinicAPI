create table tb_roles (
    id bigserial primary key,
    name varchar(100) not null
);

create table tb_users (
    id bigserial primary key,
    username varchar(100) not null,
    password varchar(70) not null
);

create table tb_user_role (
    user_id bigint not null,
    role_id bigint not null,
    constraint fk_tb_user_role_user foreign key (user_id) references tb_users(id),
    constraint fk_tb_user_role_role foreign key (role_id) references tb_roles(id)
);

create table tb_people (
    id bigserial primary key,
    name varchar(255) not null,
    person_type varchar(255) not null,
    cpf_cnpj varchar(14) not null,
    birth_date date not null,
    phone varchar(45) not null,
    email varchar(255) not null,
    user_id bigint not null,
    constraint fk_tb_people_user foreign key (user_id) references tb_users(id)
);

create table tb_addresses (
    id bigserial primary key,
    public_place varchar(255) not null,
    number varchar(15) not null,
    complement varchar(255) null,
    zip_code varchar(8) not null,
    address_type varchar(20) not null,
    person_id bigint not null,
    constraint fk_tb_addresses_person foreign key (person_id) references tb_people(id)
);

create table tb_clients (
    id bigserial primary key,
    registration_date date not null,
    person_id bigint not null,
    constraint fk_tb_clients_person foreign key (person_id) references tb_people(id)
);

create table tb_vets (
    id bigserial primary key,
    registration_date date not null,
    specialty varchar(255) not null,
    crmv varchar(45) not null,
    crmv_uf varchar(2) not null,
    person_id bigint not null,
    constraint fk_tb_vets_person foreign key (person_id) references tb_people(id)
);

create table tb_dogs (
    id bigserial primary key,
    name varchar(255) not null,
    breed varchar(255) null,
    birth_date date null,
    registration_date date not null,
    client_id bigint not null,
    constraint fk_tb_dogs_client foreign key (client_id) references tb_clients(id)
);

create table tb_attendances (
    id bigserial primary key,
    dog_id bigint not null,
    client_id bigint not null,
    vet_id bigint not null,
    attendance_date date not null,
    diagnosis text null,
    comments text null,
    dog_weight decimal(6, 2) null,
    dog_height decimal(6, 2) null,
    dog_temperament varchar(255) null,
    user_id bigint not null,
    constraint fk_attendances_dog foreign key (dog_id) references tb_dogs(id),
    constraint fk_attendances_client foreign key (client_id) references tb_clients(id),
    constraint fk_attendances_vet foreign key (vet_id) references tb_vets(id)
);
