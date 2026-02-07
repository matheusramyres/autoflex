# Autoflex - Practical Test

This project was developed as part of the Autoflex technical assessment.

The system controls products and raw materials, allowing the calculation of which products can be manufactured based on available raw material stock, prioritizing higher-value products.

---

## 🧰 Technologies

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

## 🚀 How to Run the Project

### Backend

#### Requirements

- Java 21+
- Docker
- Docker Compose

#### Steps

```bash
docker compose up -d
./mvnw quarkus:dev
```

API will be available at:

```bash
http://localhost:8080
```

Swagger UI:

```bash
http://localhost:8080/q/swagger-ui
```

### Frontend

```bash
npm install
npm run dev
```

Application will be available at:

```bash
http://localhost:5173
```

## 📌 Main Features

- Product CRUD
- Raw Material CRUD
- Product x Raw Material association
- Production suggestion based on available stock
- Priority based on product value

## 📄 Notes

- All code, database tables, and endpoints are written in English.
- The project follows API-first architecture, separating backend and frontend.
