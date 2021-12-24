## Sobre

Esse projeto foi desenvolvido para a etapa de teste do processo seletivo do Grupo Boticário. Trata-se de uma API para os revendedores(as) cadastrarem suas compras e acompanhar o retorno de seu cashback.

## Ferramentas/Linguagens

Para o desenvolvimento dessa API, foi utilizado:

- Kotlin
- Spring Boot
- Spring Security
- Spring Validation
- Swagger
- MongoDB
- JWT

## Arquitetura

O projeto foi dividido nas seguintes camadas:

- **client**: Classes que se comunicam com serviços externos
- **config**: Classes de configuração
- **controller**: Classes que expõe as endpoints 
- **dto**: DTOs de entrada(request) e saída(response)
- **enums**: Classes de constantes
- **exception**: Classes de exceção
- **filter**: Classes de filtro e interceptor
- **handler**: Classe que trata os erros da API
- **helper**: Classes de ajuda
- **model**: Classes de entidades que serão persistidas na base
- **repository**: Classes que se comunicam com a base e manipulam as entidades
- **service**: Classes de serviços que implementam as regras de negócios

## Rotas

As rotas disponíveis para essa API são:

### Login

- **POST /auth/login**: Rota de login. É necessário informar o CPF e a Senha do revendedor para autenticar na API e obter o token.

### Revendedor

- **POST /dealer**: Rota para inserir um novo revendedor(a)
- **PUT /dealer**: Rota para atualizar os dados de um revendedor(a)
- **POST /dealer/accumulated-cashback**: Rota para consultar o acumulado de cashback do revendedor
- **PUT /dealer/update-password**: Rota para atualizar a senha do revendedor

### Compra

- **POST /purchase**: Rota para inserir uma nova compra de um revendedor
- **GET /purchase/dealer/{cpf}**: Rota para consultar todas as compras de um revendedor

Observações: As únicas rotas públicas são **POST /auth/login** e **POST /dealer**. As demais precisam estar autenticada, ou seja, precisa passar no header o Authorization com o token.

![img.png](images/swagger.png)