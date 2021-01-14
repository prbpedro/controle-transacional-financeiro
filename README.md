# controle-transacional-financeiro

Este reposítório tem como objetivo demonstrar a utilização das tecnologias Java versão 1.8, o framework Spring Boot, o banco de dados Mysql, o framework de documentação Swagger, o sistema de monitoramento Prometheus, a plataforma de visualização e análise de métricas Grafana e a plataforma de conteinerização Docker, para a construção de web-api's RESTful publicáveis em nuvens baseadas no Docker e de fácil monitoração.

## WEB-API RESTful
A pasta [src/ctf](https://github.com/prbpedro/controle-transacional-financeiro/tree/main/src/ctf) deste reposítório contém uma web-api RESTful implementada utilizando a linguagem de programação Java versão 1.8, o framework Spring Boot versão 2.4.0 e a ferramenta Apache Maven.

### Base de dados
Esta aplicação mantém um banco de dados que contém três tabelas descritas a seguir:
  - Tabela: accounts
    - Coluna: account_id (Chave primária auto gerada)
    - Coluna: document_number (Tipo numérico com restirição de unicidade)
    - Coluna: available_credit_limit (Tipo Decimal que deve ser atualizado somando o valor do campo amount dos registros do tipo transaction com account_id igual ao campo account_id desta tabela. Não pode ser menor do que zero impedindo o cadastro de transactions que gerem este valor no campo available_credit_limit)
  - Tabela: operations_types 
    - Coluna: operation_type_id (Chave primária)
    - Coluna: description (Tipo alfanumérico)
  - Tabela: transactions
    - Coluna: transaction_id (Chave primária auto gerada)
    - Coluna: account_id (Chave estrangeira que referencia o campo account_id da tabela accounts)
    - Coluna: amount (Tipo Decimal que deve ter valor negativo quando operation_type_id igual a 1, 2 ou 3 e valor positivo quando operation_type_id igual a 4)
    - Coluna: event_date (Tipo DateTime)
    - Coluna: operation_type_id (Chave estrangeira que referencia o campo operation_type_id da tabela operations_types)

As tabelas são representadas pelas classes [Account](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/entidades/Account.java), [OperationType](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/entidades/OperationType.java) e [Transaction](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/entidades/Transaction.java).

O acesso a base de dados é feita através do framework spring-boot-starter-data-jpa através das interfaces [AccountRepository](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/repositorios/AccountRepository.java), [OperationTypeRepository](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/repositorios/OperationTypeRepository.java) e [TransactionRepository](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/repositorios/TransactionRepository.java) e das classes de serviço 
[AccountService](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/services/impl/AccountService.java), [OperationTypeService](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/services/impl/OperationTypeService.java) e [TransactionService](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/services/impl/TransactionService.java).

As validações quanto a integridade referencial da base de dados são realizadas pelas classes [TransactionAccountIdValidator](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/validation/validator/TransactionAccountIdValidator.java) e [TransactionOperationTypeIdValidator](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/validation/validator/TransactionOperationTypeIdValidator.java).

A validação do campo amount é realizada pela classe [TransactionAmountValidator](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/validation/validator/TransactionAmountValidator.java).

A validação de unicidade do campo document_number é feita pela classe [AccountService](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/services/impl/AccountService.java).

A validação do valor do campo available_credit_limit é feita pela classe [TransactionService](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/services/impl/TransactionService.java).

O banco de dados é configurado através do arquivo  [application.properties](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/resources/application.properties) e aponta para uma instância do banco de dados que é controlada pelo Docker.

### Endpoints
A api expõe os seguintes endpoints através das classes [AccountController](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/controllers/AccountController.java) e [TransactionController](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/controllers/TransactionController.java): 
  - /accounts - Cria novo registro do tipo Account (Método HTTP Post)
  - /accounts/{id} - Retorna registro do tipo Account através do id (Método HTTP Get)
  - /transactions - Cria novo registro do tipo Transaction (Método HTTP Post)

Os enpoints /accounts e /transactions recebem dados no formato json que são representados pelas classes [EntradaAccountSave](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/dtos/EntradaAccountSave.java) e [EntradaTransactionSave](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/dtos/EntradaTransactionSave.java).

As validações das integridades referenciais e a do campo amount são executadas durante a fase de validação dos dados de entrada no formato json, que ocorre antes da execução dos endpoints /accounts e /transactions. Estas são relacionadas aos campos das classes que representam os dados de entrada através das anotações [TransactionAccountIdConstraint](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/validation/TransactionAccountIdConstraint.java), [TransactionAmountConstraint](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/validation/TransactionAmountConstraint.java) e [TransactionOperationTypeIdConstraint](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/validation/TransactionOperationTypeIdConstraint.java).

O retorno dos endpoints /accounts, /transactions e /accounts/{id} (no caso de erro) é disposto no formato json, representado pela classe [GenericOperationResponse](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/dtos/GenericOperationResponse.java). 
Esta classe define as seguintes propriedades:
  - error - Valor booleano que indica o sucesso (false) ou não (true) do processamento da requisição
  - messages - Mapa com mensagens globais (chave: globalMessage) e/ou com mensagens por propriedade (chave: nome da propriedade) das classes de representação dos dados de entrada
  - entity - Entidade relacionada a requisição feita

O retorno do endpoint /accounts/{id} é disposto no formato json, representado pela classe [Account](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/java/com/github/prbpedro/ctf/entidades/Account.java). 

O retorno dos endpoints /accounts, /transactions e /accounts/{id} podem conter um dos seguintes HTTP Status Codes:
  - 200 (Processamento efetuado com sucesso)
  - 400 (Erros na validação dos dados de entrada)
  - 500 (Erros inesperados)
  - 409 (Para o endpoint /accounts quando já existe registro de account com o documentNumber informado)

Para a documentação dos endpoints foram utilizados os frameworks springfox-swagger2 e springfox-swagger-ui na versão 2.9.2. 

A interface gráfica da documentação pode ser acessada pelo endpoint [/swagger-ui.html](http://localhost:8080/swagger-ui.html) também exposto pela api.

### Testes unitários e de integração
Foram construídos 49 testes unitários e de integração que fazem 100% de cobertura de código pelos testes.

As classes de testes estão nas subpastas da pasta [src/test](https://github.com/prbpedro/controle-transacional-financeiro/tree/main/src/ctf/src/test).

Para a construção dos testes foram utilizados os frameworks spring-boot-starter-test e junit-jupiter versão 5.7.

Para a execução dos testes de integração é utilizado o banco de dados H2 com instância em memória, configurado através do arquivo [application.properties](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/test/resources/application.properties).

Foi configurado o plugin jacoco-maven-plugin do Apache Maven para a coleta de informações sobre os testes do projeto no arquivo [pom.xml](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/pom.xml).

Os testes devem ser executados através do comando mvn abaixo, na pasta aonde esta localizado o arquivo [pom.xml](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/pom.xml), para a geração do relatório de testes na pasta target/site/jacoco:
```sh
mvn install jacoco:report
```
Foi copiado para a pasta [src/ctf/tests-reports/jacoco](https://github.com/prbpedro/controle-transacional-financeiro/tree/main/src/ctf/tests-reports/jacoco), deste repositório, o relatório com os resultados dos testes da última alteração feita no repositório. Este pode ser acessado através do arquivo [index.html](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/tests-reports/jacoco/index.html).

### Monitoração
Para realizar a monitoração da api foram utilizados os frameworks spring-boot-starter-actuator e micrometer-registry-prometheus. Através destes a api expõe o endpoint [/actuator](http://localhost:8080/actuator) que retorna dados sobre a saúde da aplicação e suas métricas no formato do Prometheus através do endpoint [/actuator/prometheus](http://localhost:8080/actuator/prometheus).

## Execução
Para a execução desta aplicação foi utilizado o Docker Compose que deve iniciar 4 containers:
   - mysql-service (Contém a instância da base de dados MySQL Server 5.7)
   - web-service (Contém a instância da web-api)
   - prometheus (Contém a instância do Prometheus)
   - grafana (Contém a instância do Grafana)

Para iniciar os containers o comando abaixo deve ser executado executado na pasta que contém o arquivo [docker-compose.yml](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/docker/docker-compose.yml):
```sh
docker-compose up --build
```

### Container mysql-service
Este container contém a instância da base de dados MySQL Server versão 5.7 referenciada pela web-api através da configuração do arquivo [application.properties](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/src/main/resources/application.properties).

A definição deste container é feita através do arquivo [src/ctf/docker/docker-compose.yml](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/docker/docker-compose.yml).

Este container expõe a porta 3306 para acesso ao MySQL Server.

### Container web-service
Para a execução deste container é necessário gerar um arquivo JAR com a web-api através do comando mvn do Apache Maven executado na pasta que contém o arquivo [pom.xml](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/pom.xml) do projeto:
```sh
mvn install
```
Este comando irá gerar a pasta target que deverá conter o arquivo JAR  controle-transacional-financeiro-0.0.1-SNAPSHOT.jar que deverá ser copiado para a pasta [src/ctf/docker/app](https://github.com/prbpedro/controle-transacional-financeiro/tree/main/src/ctf/docker/app).

A definição deste container é feita através do arquivo [src/ctf/docker/Dockerfile](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/docker/Dockerfile) referenciado pelo arquivo [src/ctf/docker/docker-compose.yml](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/docker/docker-compose.yml).

Este container expõe a porta [8080](http://localhost:8080/swagger-ui.html) com a web-api já mencionada.

### Container prometheus
Este container contém a instância do Prometeus versão 2.6.1 que deverá obter as métricas da web-api através do endpoint [/actuator/prometheus](http://localhost:8080/actuator/prometheus) desta.

A configuração do Prometheus é feita através do arquivo [prometheus.yml](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/docker/config/prometheus/prometheus.yml) referenciado na construção do container.

A definição deste container é feita através do arquivo [src/ctf/docker/docker-compose.yml](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/docker/docker-compose.yml).

Este container expõe a porta [9090](http://localhost:9090) que contém a apĺicação web de acesso ao Prometheus.

### Container grafana
Este container contém a instância do Grafana versão 5.4.3 que deverá obter os dados do Prometheus através do [endpoint](http://localhost:9090) exposto pelo container deste e exibir o Dashboard Requests.

Este Dashboard é definido pelo arquivo [src/ctf/docker/config/grafana/dashboards/Requests.json](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/docker/config/grafana/dashboards/Requests.json) referenciado na construção do container e deve exibir um gráfico com o número de chamadas aos endpoints /accounts, /transactions e /accounts/{id} nos últimos 5 minutos, atualizados a cada 5 segundos.

A configuração do Grafana é feita através dos arquivos contidos nas subpastas da pasta [src/ctf/docker/config/grafana/provisioning](https://github.com/prbpedro/controle-transacional-financeiro/tree/main/src/ctf/docker/config/grafana/provisioning) referenciada na construção do container.

A definição deste container é feita através do arquivo [src/ctf/docker/docker-compose.yml](https://github.com/prbpedro/controle-transacional-financeiro/blob/main/src/ctf/docker/docker-compose.yml).

Este container expõe a porta [3000](http://localhost:3000) que contém a apĺicação web de acesso ao Grafana (usuário admin, senha password).

## A Fazer
 - Documentação do código fonte

Licença
----
MIT