
# 🗳️ Voting System

Sistema de Votação em Tempo Real construído com arquitetura de microsserviços, utilizando Java, Spring Boot e RabbitMQ.

## 📌 Visão Geral

Este projeto simula um sistema de votação em tempo real, ideal para competições, enquetes ou eventos ao vivo. Foi desenvolvido com foco em boas práticas de engenharia de software, segurança, mensageria e escalabilidade.

## 🧱 Arquitetura

-   Arquitetura baseada em **microsserviços**
    
-   Separação clara entre os serviços de autenticação, gerenciamento de votos e apuração de resultados
    
-   Comunicação entre serviços via **REST APIs** e **mensageria com RabbitMQ**
    
-   Autenticação com **JWT** e **Spring Security**
    

## 🛠️ Tecnologias Utilizadas

-   Java 17+
    
-   Spring Boot
    
-   Spring Security + JWT
    
-   Spring AMQP + RabbitMQ
    
-   JPA/Hibernate
    
-   MySQL
    
-   Docker
    
-   Swagger (Springdoc OpenAPI)
    
-   Maven
    

## 🚀 Como Rodar o Projeto

### Pré-requisitos

-   Docker + Docker Compose
    
-   JDK 17+
    
-   Git
    
### Passos

1.  Clone o repositório:
```bash
git clone https://github.com/seu-usuario/voting-system.git cd voting-system
   ```
2.  Inicie os containers com Docker Compose:
```bash
docker-compose up --build
   ```
4.  Acesse o Swagger UI:
    
    `http://localhost:8080/swagger-ui.html` 
    
5.  Acesse o painel do RabbitMQ (credenciais padrão: guest / guest):
    
    `http://localhost:15672` 
    

## 🔐 Autenticação

O sistema utiliza JWT para autenticação. Após fazer login via `/auth/login`, copie o token JWT e cole no botão "Authorize" do Swagger para testar os endpoints protegidos.

## 📦 Estrutura de Pastas

```code
voting-system/
├── auth-service/         # Serviço de autenticação
├── voting-service/       # Serviço de registro de votos
├── results-service/      # Serviço de apuração de votos
├── docker-compose.yml
└── README.md
```

## 💡 Próximos Passos

-   Interface web com Angular ou React
    
-   Monitoramento com Prometheus e Grafana
    
-   Persistência de mensagens com Dead Letter Queues no RabbitMQ
    
-   Tolerância a falhas e balanceamento de carga entre os serviços
    

## 🤝 Contribuição

Contribuições são bem-vindas! Fique à vontade para abrir issues ou enviar pull requests.
