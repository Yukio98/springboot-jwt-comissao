# Comissao API

Projeto Spring Boot com cadastro/login de usuarios, autenticacao JWT e endpoint protegido para lancar pedidos consumindo uma API externa que calcula comissao.

## Requisitos

- Java 21 ou superior
- Maven 3.9 ou superior, ou use o wrapper incluido (`mvnw.cmd`)

## Configuracao

Edite `src/main/resources/application.properties`:

```properties
app.jwt.secret=troque-esta-chave-secreta-jwt-com-pelo-menos-32-caracteres
server.port=8082
app.commission-api.base-url=http://localhost:8081
app.commission-api.pedido-path=/pedidos
```

`app.commission-api.base-url` deve apontar para a API ja pronta de pedidos/comissao.

## Como executar

No Windows, execute pela raiz do projeto:

```powershell
.\mvnw.cmd spring-boot:run
```

Ou dê dois cliques em `iniciar-api.cmd`.

Se o Maven ja estiver instalado globalmente, tambem funciona:

```powershell
mvn spring-boot:run
```

Nao compile a classe principal diretamente com `javac`, porque assim as dependencias do Spring Boot nao entram no classpath. O Maven le o `pom.xml`, baixa as bibliotecas e inicia a aplicacao com o classpath correto.

Para gerar o pacote:

```powershell
.\mvnw.cmd clean package
```

Por padrao, a API sobe em `http://localhost:8082`.

Se quiser usar outra porta temporariamente:

```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.arguments=--server.port=8083
```

## Endpoints

## Postman

Importe a collection:

```text
postman/comissao-api.postman_collection.json
```

Ordem recomendada para testar:

1. `Status da API`
2. `Cadastrar usuario`
3. `Login`
4. `Lancar pedido`

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

Tambem existe o atalho:

```http
POST /login
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
