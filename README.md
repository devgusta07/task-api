
# API de Gerenciamento de Tarefas

API REST desenvolvida com Java e Spring Boot para gerenciamento de tarefas com autenticação JWT. A aplicação permite que usuários gerenciem suas próprias tarefas, com controle de acesso baseado em perfis e proteção de recursos com Spring Security.

## Tecnologias

- Java
- Spring Boot
- Spring Security
- Spring Data JPA / Hibernate
- PostgreSQL
- JWT e BCrypt
- Maven
- Docker Compose
- Swagger / OpenAPI
- JUnit 5 e Mockito

## Funcionalidades

- Cadastro e autenticação de usuários.
- Autenticação com JWT e senhas criptografadas com BCrypt.
- Controle de acesso por roles (`BASIC` e `ADMIN`).
- CRUD de tarefas.
- Isolamento das tarefas por usuário autenticado.
- Gerenciamento administrativo de usuários.
- Exclusão de usuários e suas tarefas associadas.
- Documentação interativa com Swagger.
- Testes unitários com JUnit 5 e Mockito.
- Tratamento de Exceções

## Como executar

### Pré-requisitos

- JDK compatível com a versão configurada no projeto.
- Docker e Docker Compose.

### 1. Clone o repositório

```bash
git clone https://github.com/devgusta07/task.git
cd task
```

### 3. Inicie o banco de dados

```bash
docker compose up -d
```

### Configurar a chave privada JWT

A aplicação utiliza um par de chaves RSA para trabalhar com JWT:

* **Chave privada:** utilizada para assinar os tokens.
* **Chave pública:** utilizada para validar os tokens e já está incluída no projeto.

Entre na pasta `src/main/resources/certs`:

```bash
cd src/main/resources/certs
```

Execute o comando:

```bash
openssl genpkey -algorithm RSA -out private.key -pkeyopt rsa_keygen_bits:2048
```


### 4. Execute a aplicação

Execute a class principal,
```bash
ApiTarefasApplication
``` 
## Endpoints principais

| Método | Rota | Descrição |
|---|---|---|
| POST | `/creat` | Cadastro de usuário |
| POST | `/loguin` | Login e geração do JWT |
| POST | `/task/creat` | Criação de tarefa |
| GET | `/task/listar` | Listagem das tarefas do usuário autenticado |
| PUT | `/task/update/{id}` | Atualização de tarefa |
| DELETE | `/task/delete/{id}` | Exclusão de tarefa |

As rotas administrativas permitem consultar e excluir usuários, conforme as permissões configuradas.

## Documentação

Com a aplicação em execução, acesse:

[Swagger UI](http://localhost:8080/swagger-ui/index.html)

## Testes

Execute os testes automatizados com:

```powershell
.\mvnw.cmd test
```

## Autor

**Gustavo Oliveira**

- GitHub: [@devgusta07](https://github.com/devgusta07)
- LinkedIn: [Gustavo Oliveira](https://linkedin.com/in/gustavosoliveiradev)

