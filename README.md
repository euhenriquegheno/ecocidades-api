# Projeto -Eco Cidades

Este projeto é uma API desenvolvida em Java com Spring Boot, projetada para monitorar e gerenciar dados relacionados a Eventos Urbanos, Feedback de Cidadãos, Indicadores Ambientais, Iniciativas Sociais e Transparência Governamental, com foco em cidades inteligentes e sustentáveis (ESG).

## Como executar localmente com Docker

Para executar a aplicação localmente utilizando Docker, siga os passos abaixo:

1.  **Construir a imagem Docker:**
    Navegue até o diretório raiz do projeto (`ecocidades-api`) onde o `Dockerfile` está localizado e execute o seguinte comando para construir a imagem Docker:
    ```bash
    docker build -t ecocidades-api .
    ```

2.  **Executar o contêiner Docker:**
    Após a imagem ser construída, você pode executar o contêiner. A aplicação será exposta na porta `8080`.
    ```bash
    docker run -p 8080:8080 ecocidades-api
    ```
    A API estará disponível em `http://localhost:8080`.

## Pipeline CI/CD

O projeto utiliza **GitHub Actions** para automatizar os processos de Integração Contínua (CI) e Entrega Contínua (CD). Dois workflows principais são configurados:

### continuous_integration.yml

Este workflow é acionado em cada `pull_request` para a branch `develop`. Suas etapas incluem:

*   **Git Checkout**: Clona o repositório.
*   **Setup Java SDK**: Configura o ambiente Java 21 usando Temurin.
*   **Unit tests**: Executa os testes unitários do projeto usando Maven (`mvn test`).

### continuous-delivery.yml

Este workflow é acionado em cada `push` para a branch `develop`. Suas etapas incluem:

*   **Checkout**: Clona o repositório.
*   **Set up Docker Buildx**: Configura o Buildx para construção de imagens Docker.
*   **Log in to registry**: Realiza o login no Docker Hub utilizando credenciais armazenadas como segredos do GitHub (`DOCKERHUB_USERNAME`, `DOCKERHUB_TOKEN`).
*   **Build and push container image to registry**: Constrói a imagem Docker da aplicação e a envia para o Docker Hub, utilizando a `SHA` do commit como tag.
*   **Deploy to Azure Web App**: Realiza o deploy da imagem Docker construída para um Azure Web App chamado `ecocidades-api` no slot de `production`, utilizando um perfil de publicação (`AZURE_PROFILE`) como segredo.

## Containerização

A aplicação é containerizada utilizando Docker. O `Dockerfile` define as etapas para construir uma imagem otimizada da aplicação Spring Boot.

```dockerfile
FROM maven:3.9.8-eclipse-temurin-21 AS build

RUN mkdir /opt/app

COPY . /opt/app

WORKDIR /opt/app

RUN mvn clean package

FROM eclipse-temurin:21-jdk-alpine

RUN mkdir /opt/app

COPY --from=build  /opt/app/target/app.jar /opt/app/app.jar

WORKDIR /opt/app

ENV PROFILE=prd

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "app.jar"]
```

**Estratégias adotadas:**

*   **Build multi-stage**: O `Dockerfile` utiliza um build multi-stage. A primeira etapa (`build`) compila o projeto Java e gera o arquivo `.jar`. A segunda etapa (`eclipse-temurin:21-jdk-alpine`) cria uma imagem final menor, copiando apenas o `.jar` gerado, resultando em uma imagem de produção mais leve e segura.
*   **Variável de ambiente para perfil Spring**: A variável de ambiente `PROFILE` é definida como `prd` (produção), garantindo que a aplicação inicie com o perfil de produção do Spring Boot. Isso permite a utilização de arquivos de configuração específicos para o ambiente de produção (e.g., `application.prd.properties`).
*   **Exposição de porta**: A porta `8080` é exposta, que é a porta padrão onde a aplicação Spring Boot será executada.
*   **Ponto de entrada**: O `ENTRYPOINT` define como a aplicação será iniciada dentro do contêiner, ativando o perfil Spring especificado.

## Prints do funcionamento

Azure
![Azure](https://github.com/euhenriquegheno/ecocidades-api/blob/develop/prints/azure.png)

Docker
![Docker](https://github.com/euhenriquegheno/ecocidades-api/blob/develop/prints/docker.png)

Swagger
![Swagger](https://github.com/euhenriquegheno/ecocidades-api/blob/develop/prints/swagger.png)

https://ecocidades-api-aqh2hkhgb6cjbwa8.canadacentral-01.azurewebsites.net/swagger-ui/index.html#/

## Tecnologias utilizadas

*   **Linguagem**: Java 21
*   **Framework**: Spring Boot 3.4.0
*   **Banco de Dados**: H2 Database (para desenvolvimento/testes)
*   **ORM**: Spring Data JPA
*   **Build Tool**: Apache Maven
*   **Containerização**: Docker
*   **CI/CD**: GitHub Actions
*   **Mapeamento de Objetos**: ModelMapper 3.2.0
*   **Documentação API**: Springdoc OpenAPI (Swagger UI) 2.5.0
*   **Testes**: JUnit 5.10.0
*   **Utilitários**: Project Lombok
*   **Cloud Provider**: Microsoft Azure (para deploy)

