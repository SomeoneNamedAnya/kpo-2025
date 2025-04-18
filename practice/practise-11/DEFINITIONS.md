# Список терминов семинара
###### Нужно написать определения с примером из жизни или кода
- Что такое Docker? зачем он нужен?
  Docker — программное обеспечение для автоматизации развёртывания и управления приложениями в средах с поддержкой контейнеризации.
Платформа позволяет «упаковать» приложение со всем окружением и зависимостями в контейнер, а затем доставить и запустить его в целевой системе.
Приложение, упакованное в контейнер, изолируется от операционной системы и других приложений
- Как поднять бд в докере?
- 1. Добавьте docker-compose.yml следующее (как шаблон)

```yaml
services:
  postgres:
    image: postgres:15-alpine
    container_name: postgres_db
    environment:
      POSTGRES_DB: car_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    volumes:
      - postgres_data:/var/lib/postgresql/data
    ports:
      - "5432:5432"
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U postgres -d postgres"]
      interval: 5s
      timeout: 5s
      retries: 5
```
      
- 2. docker-compose up

- Как подключить бд к приложению?
  Добавьте docker-compose.yml следующее (как шаблон)
```yaml
spring:
  application:
    name: kpo-app
  datasource:
    url: jdbc:postgresql://localhost:5432/car_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
server:
  port: 8080
```
- Что такое Repository?
Api для вз