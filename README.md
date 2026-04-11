# ATV1 LDDM - 🎬 API de Filmes

**Yasmin Pires Belarmino**

---

## 📌 Descrição do Projeto

Esta é uma API desenvolvida com **Ktor** para gerenciamento de **filmes e diretores**.

O sistema permite realizar operações CRUD completas para:

* Diretores
* Filmes
---

## 🧱 Estrutura do Projeto

* `server/` → Backend com Ktor
* `shared/` → Models e interfaces de repositório
* `db/` → Tabelas e migrations
* `routes/` → Rotas da API

---

## 🚀 Tecnologias Utilizadas

* Kotlin
* Ktor
* Exposed ORM
* PostgreSQL
* Flyway
* Docker Compose
* Swagger (OpenAPI)

---

## 📂 Rotas da API

### 🎥 Filmes (`/movies`)

* `GET /movies` → Listar filmes
* `GET /movies/{id}` → Buscar por ID
* `POST /movies` → Criar filme
* `PUT /movies/{id}` → Atualizar filme
* `DELETE /movies/{id}` → Deletar filme

---

### 🎬 Diretores (`/directors`)

* `GET /directors` → Listar diretores
* `GET /directors/{id}` → Buscar por ID
* `POST /directors` → Criar diretor
* `PUT /directors/{id}` → Atualizar diretor
* `DELETE /directors/{id}` → Deletar diretor

---

## 📘 Documentação Swagger

A documentação está disponível em:

```
http://localhost:8080/swagger
```

---

## 🗄️ Banco de Dados

* Banco: PostgreSQL
* Gerenciado via **Docker Compose**
* Tabelas criadas com **Flyway migrations**
* Seed inicial incluído

---

## ⚙️ Configuração do Ambiente

Crie um arquivo `.env` baseado no exemplo:

### 📄 `.env.example`

---

## 🐳 Rodando com Docker

1. Suba o banco:

```
docker-compose up -d
```

2. Verifique se o container está rodando:

```
docker ps
```

---

## ▶️ Executando o Projeto

1. Abra o projeto no IntelliJ

2. Configure o **Gradle JVM**:

   ```
   jbr-21 (JetBrains Runtime 21)
   ```

3. Rode a aplicação:

```
Application.kt
```

4. A API estará disponível em:

```
http://localhost:8080
```

---
