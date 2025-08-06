# 📬 Magalu - Desafio Backend

Este projeto foi desenvolvido como parte de um desafio técnico para uma vaga backend. O sistema é responsável por agendar notificações com mensagem, data, hora e mensagem. Conta também com um sistema automático que verifica notificações expiradas e altera seus status.

---

## 🚀 Tecnologias Utilizadas

- ✅ **Java 17**
- ✅ **Spring Boot 3**
- ✅ **Spring Web**
- ✅ **Spring Data JPA**
- ✅ **PostgreSQL**
- ✅ **Lombok**
- ✅ **MapStruct**
- ✅ **JUnit 5**
- ✅ **Mockito**

---

## 🧠 Funcionalidades

- ✅ Agendar notificações com dados personalizados.
- ✅ Consultar notificações por ID.
- ✅ Cancelar notificações agendadas.
- ✅ Alterar automaticamente o status de notificações vencidas (de `SCHEDULED` para `SENT`).
- ✅ Testes unitários cobrindo os principais métodos da camada de serviço.

---


## 🎯 Como Rodar o Projeto

### Pré-requisitos:
- Java 17 instalado
- Maven instalado
- PostgreSQL rodando e configurado no `application.properties`

### Passos para rodar:

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/magalu-desafio-backend.git
cd magalu-desafio-backend

# Compile e rode os testes
mvn clean test

# Rode a aplicação
mvn spring-boot:run
```

### 📌 Considerações Finais
Este projeto foi criado com foco em:

Boas práticas com Java e Spring Boot

Organização em camadas (Infra, Business, Service, Controller e DTO's)

Testes unitários bem estruturados com mocks

Clareza e legibilidade no código

### 👤 Autor
**Diego Lopes do Nascimento**

[LinkedIn](https://www.linkedin.com/in/diego-nascimento-b33311221/)
