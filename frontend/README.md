# Desafio Fullstack Integrado – Sistema de Benefícios

## Visão Geral
Este projeto implementa uma solução fullstack completa composta por:
- Módulo EJB responsável pela regra de negócio
- Backend Spring Boot expondo API REST
- Frontend Angular
- Integração via EJB remoto
- Persistência com JPA
- Documentação via Swagger (OpenAPI)

O sistema permite o gerenciamento de benefícios e a transferência de valores entre eles, com controle de concorrência.

---

## Arquitetura

frontend (Angular)
→ backend-module (Spring Boot)
→ ejb-module (EJB remoto)
→ Banco de Dados (H2)

---

## Tecnologias Utilizadas
- Java 11 / 17
- Spring Boot 3.x
- EJB 3.2
- JPA / Hibernate
- Jakarta Validation
- WildFly / JBoss EAP
- H2 Database
- Angular
- Swagger (OpenAPI 3)

---

## Como Executar

### 1. Subir o EJB (obrigatório primeiro)
- Iniciar o WildFly / JBoss
- Fazer deploy do `ejb-module`

JNDI utilizado:
java:global/ejb-module-1.0.0/BeneficioEjbService!com.example.ejb.BeneficioEjbRemote

---

### 2. Executar o Backend
cd backend-module  
mvn spring-boot:run

Backend:
http://localhost:8081

---

### 3. Executar o Frontend
cd frontend  
npm install  
ng serve

Frontend:
http://localhost:4200

---

## Documentação da API (Swagger)
http://localhost:8081/swagger-ui.html

---

## Endpoints Principais

### Listar Benefícios
GET /api/v1/beneficios

### Buscar Benefício por ID
GET /api/v1/beneficios/{id}

### Criar Benefício
POST /api/v1/beneficios

Body:
{
  "nome": "Benefício A",
  "valor": 100.00
}

### Atualizar Benefício
PUT /api/v1/beneficios/{id}

### Excluir Benefício
DELETE /api/v1/beneficios/{id}

---

## Transferência de Benefícios

### Endpoint
POST /api/v1/transferencias

### Payload
{
  "fromId": 1,
  "toId": 2,
  "amount": 50.00
}

### Regras de Negócio
- fromId e toId obrigatórios
- fromId diferente de toId
- amount maior que zero
- saldo suficiente
- controle de concorrência com lock otimista (@Version)

---

## Concorrência
A transferência utiliza:
- Lock otimista
- @Version na entidade
- Tratamento de OptimisticLockException

Garantindo consistência mesmo com múltiplas requisições simultâneas.

---

## Testes

### Testes Funcionais
- Executados via Swagger
- Executados via Postman
- Integração real Backend → EJB → Banco

### Testes Unitários
- Serviços e validações
- Complementam os testes funcionais

---

## Estrutura do Backend

backend-module
├── controller
├── service
├── integration
│   └── ejb
├── dto
├── entity
├── repository
└── config

---

## Critérios do Desafio Atendidos
- Arquitetura em camadas
- Integração EJB remoto
- Backend desacoplado
- API REST documentada
- Concorrência tratada
- Testes funcionais
- Integração frontend/backend

---

## Observação Final
Projeto desenvolvido seguindo rigorosamente o escopo do desafio, priorizando clareza arquitetural, separação de responsabilidades e consistência transacional.
