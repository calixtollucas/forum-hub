# 📌 API Forum-Hub

API REST desenvolvida em **Java 17** com **Spring Boot 3** para gerenciamento de tópicos, usuários e respostas de um fórum educacional.  
Projeto criado como parte do **Challenge Fórum-Hub do Oracle ONE**.

---

## 🚀 Tecnologias Utilizadas

- ☕ Java 17
- 🌱 Spring Boot 3
- 🔐 Spring Security + JWT
- 💾 Spring Data JPA (Hibernate)
- 🐘 PostgreSQL
- 🧰 Lombok
- 📖 SpringDoc OpenAPI (Swagger)

---

## ⚙️ Como Rodar Localmente

### ✅ Pré-requisitos
- **Java 17**
- **Maven 3.8+**
- **Docker** instalado e rodando

### ▶️ Passos

1. **Suba o banco de dados com Docker:**
```bash
   cd docker
   docker-compose up
```

2. **Defina as variáveis de ambiente:**

```bash
   APPLICATION_PROFILE=dev
   DB_USERNAME=root
   DB_PASSWORD=forumhub122
   ```

3. **Volte para a raiz do projeto e rode a aplicação:**

```bash
   mvn spring-boot:run
   ```

A API estará disponível em:
👉 **[http://localhost:8080](http://localhost:8080)**

---

## 🔑 Autenticação

A API utiliza **JWT** para autenticação.

* Para obter o token:

    * Faça um `POST` em **`/auth/login`** com as credenciais de usuário.
    * O token JWT retornado deve ser enviado no header `Authorization` em todas as requisições protegidas:

      ```
      Authorization: Bearer <seu_token_aqui>
      ```

---

## 📌 Principais Endpoints

| Recurso      | Endpoint      | Método              | Descrição         |
| ------------ | ------------- | ------------------- | ----------------- |
| Autenticação | `/auth/login` | POST                | Gera o token JWT  |
| Tópicos      | `/topicos`    | GET/POST/PUT/DELETE | CRUD de tópicos   |
| Usuários     | `/usuarios`   | GET/POST/PUT/DELETE | CRUD de usuários  |
| Respostas    | `/respostas`  | GET/POST/PUT/DELETE | CRUD de respostas |

> **Dica:** acesse a documentação gerada pelo **SpringDoc** em:
> 👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
---

💡 **Projeto desenvolvido como parte do challenge Forum-Hub do Oracle ONE.**