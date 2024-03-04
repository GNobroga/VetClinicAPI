## VetClinicAPI (Em construção)

A "VetClinicAPI" é uma API desenvolvida para atender às necessidades de uma clínica veterinária. Ela fornece um conjunto de endpoints que permite a gestão de informações relacionadas a clientes, animais de estimação, consultas veterinárias e profissionais de veterinária. A API é projetada para facilitar o gerenciamento eficiente de dados, agendamento de consultas, e fornecer informações importantes para a equipe da clínica veterinária.

### Endpoints

#### /api/v1/pessoas (POST)

- Todos os campos são obrigatórios

- Document pode ser CPF ou CNPJ

- É obrigatório ter pelo menos 1 endereço na hora do cadastro

- O tipo do endereço pode ser WORK ou HOME

- Não é possível cadastrar um document ou email ou username que já exista.

```json
    {
    "name": "Gabriel",
    "birthDate": "2000-04-10",
    "document": "17364509720",
    "phone": "28999505410",
    "email": "gabrielcardoso123@gmail.com",
    "username": "gabiroba",
    "password": "gabiroba123",
    "addresses": [
        {
        "place": "Elizeu meia nove",
        "number": "100",
        "complement": "atrás do seu josé",
        "cep": "29360000",
        "type": "WORK"
        }
    ]
    }

```

#### /api/v1/pessoas/{id} (PUT)

- Todos os campos são obrigatórios

- Document pode ser CPF ou CNPJ

- Se for enviado 1 endereço todos os que já estavam cadastrados serão excluídos, caso não seja enviado nada os endereços continuarão.

- O tipo do endereço pode ser WORK ou HOME

- Não é possível atualizar para um document ou email ou username que já exista.

```json
{
  "name": "Gabriel",
  "birthDate": "2000-04-10",
  "document": "17364509720",
  "phone": "28999505410",
  "email": "gabrielcardoso123@gmail.com",
  "username": "gabiroba",
  "password": "gabiroba123",
  "addresses": [
    {
      "place": "Elizeu meia nove",
      "number": "100",
      "complement": "atrás do seu josé",
      "cep": "29360000",
      "type": "WORK"
    }
  ]
}
```

### Tecnologias

- Java 17

- Spring Docs (Swagger)

- Hibernate

- Postgres

- Redis

- Lombok

- Mapstruct

- Spring Security

- Flyway


#### Feita por amor por mim <3