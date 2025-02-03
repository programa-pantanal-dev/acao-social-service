![Frame 2](https://github.com/user-attachments/assets/9f117baf-5760-4ae9-b5d9-0295651db6b4)

# Microserviço de Ação Social

Este microserviço é responsável pela gestão das ações sociais dentro do sistema. Ele fornece endpoints para criação, edição, exclusão e consulta de ações sociais, garantindo integração eficiente com os demais serviços do ecossistema.

## Tecnologias Utilizadas
- **Linguagem**: [Java]
- **Framework**: [Spring Boot]
- **Banco de Dados**: [PostgreSQL]
- **Autenticação**: [KeyCloak | OAuth2]

## Estrutura do Projeto
```
/acao-social-service
├── src
│   ├── main
|   |   ├── java/br/com/b3social/acaosocialservice
|   |   |   ├── annotations
|   |   |   ├── api
|   |   |   |   ├── controllers
|   |   |   |   └── dtos
|   |   |   ├── config
|   |   |   ├── domain
|   |   |   |   ├── models
|   |   |   |   |   └── enums
|   |   |   |   ├── repositories
|   |   |   |   └── services
|   |   |   └── security
|   |   |   |   └── expressions
|   |   └── resoures
│   └── test/java/br/com/b3social/acaosocialservice
└── README.md
```

## Diagramas e Arquitetura

### Diagrama BPMN
Diagrama do fluxo geral da aplicação completa.

![inscrição-de-ação-social](https://github.com/user-attachments/assets/b388bf58-0a01-47e9-a688-047e8d5e70ca)

### Diagrama de micro serviços
Diagrama de micro serviços da aplicação completa.

![image](https://github.com/user-attachments/assets/dc5e22bd-9458-477a-ae50-9c18c404f7e4)

## Endpoints
Endpoints documentados automaticamente pelo swagger.

![endpoints](https://github.com/user-attachments/assets/2fec79a1-c52a-4027-b55d-22e9554c416f)

## Request Bodies
Principais DTOs desse micro serviço.

### DTO de Criação
![create](https://github.com/user-attachments/assets/ab0f5e37-fb80-4dbd-bf6b-1d3e02ab18ad)


### DTO de Atualização
![update](https://github.com/user-attachments/assets/d9d32570-ddc4-4a0b-b6e6-a2102eb0089b)
