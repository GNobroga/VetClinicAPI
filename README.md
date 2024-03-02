## VetClinicAPI (Em construção)

## Tabelas

### Roles

| Column   | Data Type | Constraints                    |
|----------|-----------|--------------------------------|
| id       | bigserial  | Primary Key                     |
| name     | varchar(100) | Not Null                       |

### Usuários

| Column   | Data Type  | Constraints                  |
|----------|------------|------------------------------|
| id       | bigserial  | Primary Key                   |
| username | varchar(100)| Not Null                     |
| password | varchar(70) | Not Null                     |

### Usuário Role

| Column    | Data Type | Constraints                           |
|-----------|-----------|---------------------------------------|
| user_id   | bigint    | Not Null, FK (tb_users.id)             |
| role_id   | bigint    | Not Null, FK (tb_roles.id)             |

### Pessoas

| Column       | Data Type    | Constraints                       |
|--------------|--------------|-----------------------------------|
| id           | bigserial    | Primary Key                        |
| name         | varchar(255) | Not Null                           |
| person_type  | varchar(255) | Not Null                           |
| cpf_cnpj     | varchar(14)  | Not Null                           |
| birth_date   | date         | Not Null                           |
| phone        | varchar(45)  | Not Null                           |
| email        | varchar(255) | Not Null                           |
| user_id      | bigint       | Not Null, FK (tb_users.id)         |

### Endereços

| Column        | Data Type    | Constraints                                    |
|---------------|--------------|------------------------------------------------|
| id            | bigserial    | Primary Key                                     |
| public_place  | varchar(255) | Not Null                                       |
| number        | varchar(15)  | Not Null                                       |
| complement    | varchar(255) | Nullable                                       |
| zip_code      | varchar(8)   | Not Null                                       |
| address_type  | varchar(20)  | Not Null                                       |
| person_id     | bigint       | Not Null, FK (tb_people.id) ON DELETE CASCADE  |

### Clientes

| Column             | Data Type    | Constraints                                       |
|--------------------|--------------|---------------------------------------------------|
| id                 | bigserial    | Primary Key                                       |
| registration_date  | date         | Not Null                                          |
| person_id          | bigint       | Not Null, FK (tb_people.id) ON DELETE CASCADE    |

### Veterinários

| Column             | Data Type    | Constraints                                       |
|--------------------|--------------|---------------------------------------------------|
| id                 | bigserial    | Primary Key                                       |
| registration_date  | date         | Not Null                                          |
| specialty          | varchar(255) | Not Null                                          |
| crmv               | varchar(45)  | Not Null                                          |
| crmv_uf            | varchar(2)   | Not Null                                          |
| person_id          | bigint       | Not Null, FK (tb_people.id) ON DELETE CASCADE    |

### Cachorros

| Column             | Data Type    | Constraints                                       |
|--------------------|--------------|---------------------------------------------------|
| id                 | bigserial    | Primary Key                                       |
| name               | varchar(255) | Not Null                                          |
| breed              | varchar(255) | Nullable                                          |
| birth_date         | date         | Nullable                                          |
| registration_date  | date         | Not Null                                          |
| client_id          | bigint       | Not Null, FK (tb_clients.id) ON DELETE CASCADE   |

### Atendimentos

| Column             | Data Type    | Constraints                                       |
|--------------------|--------------|---------------------------------------------------|
| id                 | bigserial    | Primary Key                                       |
| dog_id             | bigint       | Not Null, FK (tb_dogs.id)                         |
| client_id          | bigint       | Not Null, FK (tb_clients.id)                      |
| vet_id             | bigint       | Not Null, FK (tb_vets.id)                         |
| attendance_date    | date         | Not Null                                          |
| diagnosis          | text         | Nullable                                          |
| comments           | text         | Nullable                                          |
| dog_weight         | decimal(6, 2)| Nullable                                          |
| dog_height         | decimal(6, 2)| Nullable                                          |
| dog_temperament    | varchar(255) | Nullable                                          |
| user_id            | bigint       | Not Null, FK (tb_users.id)                        |


## Tecnologias

- Java 17

- Hibernate

- H2

- Lombok

- Mapstruct

- Spring Security

- Spring Docs
