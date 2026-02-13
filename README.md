# Autoflex

Este projeto foi desenvolvido como parte do processo técnico da Autoflex.

O sistema realiza o controle de produtos e matérias-primas, permitindo calcular quais produtos podem ser fabricados com base no estoque disponível de matérias-primas, priorizando os produtos de maior valor.

---

## 🧰 Technologias

### Backend

- Java 21
- Quarkus
- Hibernate ORM with Panache
- PostgreSQL
- Docker / Docker Compose

### Frontend

- React
- Vite
- Axios
- Responsive UI

---

## 🚀 Como Executar o Projeto

### Backend

#### Requisitos

- Java 21+
- Docker
- Docker Compose

#### Passos

```bash
docker compose up -d
./mvnw quarkus:dev #ou no windows .\mvnw quarkus:dev
```

A API estará disponível em:

```bash
http://localhost:8080
```

Swagger UI:

```bash
http://localhost:8080/q/swagger-ui
```

### Frontend

```bash
yarn install
yarn run dev
```

A aplicação estará disponível em:

```bash
http://localhost:5173
```

## 📌 Principais Funcionalidades

- CRUD de Produtos
- CRUD de Matérias-Primas
- Associação entre Produto e Matéria-Prima
- Sugestão de produção baseada no estoque disponível
- Priorização baseada no valor do produto

## 📄 Observações

- Todo o código, tabelas do banco de dados e endpoints estão escritos em inglês.
- O projeto segue uma arquitetura API-first, com separação entre backend e frontend.
