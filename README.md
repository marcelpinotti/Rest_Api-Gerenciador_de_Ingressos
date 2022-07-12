### 🛠️ Em construção 🛠️ ###

# Gerenciador de Ingressos #

### A aplicação em construção, utiliza a arquitetura Rest, e irá simular o gerenciamento de ingressos de diferentes eventos. ###

🛠️ Até o momento foram construídos, o DTO, o service, os testes e o controller da entidade Endereço.

- ✒️ Através de uma requisição GET ao webservice de CEP do ViaCEP, utilizando OpenFeign, a aplicação retorna as informações do CEP consultado.
- ✒️ Para estudo do CRUD utilizei a estratégia de salvar as informações do CEP no banco de dados.
- ✒️ Utilizei o Bean Validation para validações de campos.
- ✒️ Utilizei o ControllerAdvice e ExceptionHandler para o tramamento de exceções.
  - ✒️ Tratei as exceções de FeignClient, Validation e as demais exceções.   
- ✒️ Utilizei o JUnit 5, o Mockito, o Feign Mock e o Feign Jackson para criar os testes unitários.
- ✒️ Utilizei a biblioteca ModelMapper para implementar o padrão DTO.
- ✒️ Utilizei o Design Pattern Chain of Responsibility para validar o CEP. 

## Tecnologias ## 

- IntelliJ IDEA Community Edition;
- Java v.17;
- Spring Boot v.2.7.1;
- Spring Data JPA;
- Bean Validation;
- Model Mapper v.3.1.0 - Conversão entre entidades e dtos;
- Spring Cloud OpenFeign - Consumo de uma API externa;
- JUnit 5;
- Mockito;
- Feign Mock - Testes unitários das requisições client;
- Feign Jackson - Construir a simulação das respostas feitas pelo client;
- MySQL;
- Postman;
- OpenApi v.1.6.9 - Teste e documentação da aplicação;


## Inicialização ##

Projeto roda em: ``` http://localhost:8080 ```

Para ter acesso aos endpoints da aplicação após a inicialização, é só acessar a link: ``` http://localhost:8080/swagger-ui/index.html ```

O projeto pode ser inicializado de duas formas:

```
- 1ª Diretamente pela IDE;
- 2ª Abrindo um terminal no diretório raiz do projeto e digitando " mvn spring-boot:run " ou " mvnw spring-boot:run "
```

## Endpoints ##

Os endpoints criados até o momento para a interação com o banco de dados são:

**ENDEREÇO**

| Tipo | Descrição | Caminho |	Perfil de Acesso |
| --- | --- | --- | --- |
| GET | Lista todas os Endereços | /v1/endereco/todos | -- |
| GET | Busca um Endereço pelo CEP | /v1/endereco/cep/{cep} | -- |
| POST | Cria um novo Endereço | /v1/endereco/{cep} | -- |
| PUT | Atualiza um Endereço | /v1/endereco/{cep} | -- |
| DEL | Deleta um Endereço | /v1/endereco/{cep} | -- |

## Estrutura de Dados ##

Até o presentem momento o sistema possue a seguinte estrutura de dados.

**Endereco**
```
{
  "cep": "string",
  "logradouro": "string",
  "bairro": "string",
  "localidade": "string",
  "uf": "string"
}
```
**Local do Evento**
```
{
 ---
}
```
**Evento**
```
{
 ---
}
```
**Usuario**
```
{
 ---
}
```
**Historico de Compras**
```
{
 ---
}
```

## ViaCEP ##

O ViaCEP é um webservice gratuito e de alto desempenho para consultar Códigos de Endereçamento Postal (CEP) do Brasil.
  - O webservice tem três tipos de retorno: JSON, XML, JSONP.
  - Endereço: ``` https://viacep.com.br/ ``` 

Retorno utilizado no projeto: JSON. ``` URL: viacep.com.br/ws/{cep}/json/ ```

***EXEMPLO DE RETORNO DO CEP 01001-000***
```
    {
      "cep": "01001-000",
      "logradouro": "Praça da Sé",
      "complemento": "lado ímpar",
      "bairro": "Sé",
      "localidade": "São Paulo",
      "uf": "SP",
      "ibge": "3550308",
      "gia": "1004",
      "ddd": "11",
      "siafi": "7107"
    }
```

### 📋 Principais Desafios ###

Os principais desafios até o momento foram:
  - ✒️ Testes Unitários - Principamente entender como poderia ser feito os testes de requisições de uma API externa.
    - ✒️ A solução que optei foi mockar a resposta para analisar o retorno.
  - ✒️ Testes Unitários - Das exceções que não foram criadas por mim.
  - ✒️ Criar o tratamento das exceções vindas das validações.

