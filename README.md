# Comissao API

Projeto Spring Boot com cadastro/login de usuarios, autenticacao JWT e endpoint protegido para lancar pedidos consumindo uma API externa que calcula comissao.

## Requisitos

- Java 21 ou superior
- Maven 3.9 ou superior

## Configuracao

Edite `src/main/resources/application.properties`:

```properties
app.jwt.secret=troque-esta-chave-secreta-jwt-com-pelo-menos-32-caracteres
app.commission-api.base-url=http://localhost:8081
app.commission-api.pedido-path=/pedidos
```

`app.commission-api.base-url` deve apontar para a API ja pronta de pedidos/comissao.

## Como executar

```bash
mvn spring-boot:run
```

## Endpoints

### Cadastrar usuario

```http
POST /auth/cadastro
Content-Type: application/json

{
  "nome": "Lucas",
  "email": "lucas@email.com",
  "senha": "123456"
}
```

### Login

```http
POST /auth/login
Content-Type: application/json

{
  "email": "lucas@email.com",
  "senha": "123456"
}
```

### Lancar pedido

```http
POST /pedidos
Authorization: Bearer SEU_TOKEN
Content-Type: application/json

{
  "codigoPedido": "PED-001",
  "valorPedido": 1500.00,
  "vendedorEmail": "lucas@email.com"
}
```

O corpo enviado em `/pedidos` e repassado para a API externa configurada em `app.commission-api.base-url`.
