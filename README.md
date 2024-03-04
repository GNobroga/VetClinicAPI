## VetClinicAPI (Em construção)

A "VetClinicAPI" é uma API desenvolvida para atender às necessidades de uma clínica veterinária. Ela fornece um conjunto de endpoints que permite a gestão de informações relacionadas a clientes, animais de estimação, consultas veterinárias e profissionais de veterinária. A API é projetada para facilitar o gerenciamento eficiente de dados, agendamento de consultas, e fornecer informações importantes para a equipe da clínica veterinária.

### Endpoints

Todos os Endpoints reconhecem **POST**, **DELETE**, **GET**, **PUT**.

#### /api/v1/[name] (GET)

- Paginação através dos queries params **pageNumber**, **pageSize**, **order**

- O **pageSize** é limitado a ter no máximo 50 de valor

```bash
    http://localhost:8080/api/v1/pessoas?pageNumber=1&pageSize=20&order=ASC
```


#### /api/v1/pessoas (POST)

- Todos os campos são obrigatórios e com limitação de caracteres

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

- Todos os campos são obrigatórios e com limitação de caracteres

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

#### /api/v1/clientes (POST)

- O personId é obrigatório e deve existir

```json
{
  "communicationPreferences": "email",
  "alternatePhone": "28999505410",
  "personId": 1
}
```

#### /api/v1/clientes/{id} (PUT)

- O personId é obrigatório e deve existir

- Se o personId for diferente do id do objeto Person associado a entidade Client será lançado uma execeção

- communicationPreferences só permite 100 caracteres e o alternatePhone 45.

```json
{
  "communicationPreferences": "email",
  "alternatePhone": "28999505410",
  "personId": 1
}
```

### /api/v1/enderecos (POST)

- Quase todos os campos são obrigatórios, exceto complemento.

- A identificação da pessoa é obrigatório

- O CEP será validado através de uma API externa

- O tipo deve ser WORK ou HOME

```json
{
    "place": "São Joaquim",
    "number": "100",
    "complement": "Atrás da academia",
    "cep": "29360-000",
    "type": "HOME",
    "personId": 1
}

```

#### /api/v1/enderecos/{id}

- Serão feitas as mesmas validações do method POST.

- Caso o personId seja diferente do objeto Person já associado ao Address será lançado uma exceção.

```json
{
    "place": "São Joaquim",
    "number": "100",
    "complement": "Atrás da academia",
    "cep": "29360-000",
    "type": "HOME",
    "personId": 1
}

```

### Relatórios

Em breve

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