# DualShare Backend

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![Firebase](https://img.shields.io/badge/Firebase-Authentication-yellow)
![Cloudinary](https://img.shields.io/badge/Cloudinary-Media-blueviolet)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED)

Backend REST desarrollado con Spring Boot para DualShare, una aplicación móvil que permite compartir historias e imágenes entre usuarios de forma segura utilizando autenticación con Google.

---

# Características

- Inicio de sesión con Google mediante Firebase Authentication.
- Verificación segura del Firebase ID Token.
- Registro automático de usuarios.
- Gestión de usuarios.
- Creación de historias.
- Compartir historias con otros usuarios.
- Subida de imágenes a Cloudinary.
- API REST.
- Persistencia con MySQL.
- Docker.

---

# Tecnologías

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- Firebase Admin SDK
- Cloudinary
- Docker
- Maven

---

# Arquitectura

La aplicación sigue una arquitectura por capas.

![Arquitectura](docs/architecture.png)

---

# Modelo Entidad Relación

El siguiente diagrama representa el modelo de datos utilizado por la aplicación.

![ER Diagram](docs/database-er.png)

---

# Flujo de autenticación

1. El usuario inicia sesión con Google desde Android.
2. Firebase genera un ID Token.
3. Android envía el token al backend.
4. Spring Boot verifica el token mediante Firebase Admin SDK.
5. Si el usuario no existe, se registra automáticamente.
6. El backend devuelve la información del usuario.

---

# Flujo de subida de imágenes

1. Android selecciona una imagen.
2. La imagen se envía al backend.
3. El backend la sube a Cloudinary.
4. Cloudinary devuelve la URL.
5. La URL se almacena en la base de datos.

---

# Arquitectura del sistema

```
Android App
        │
        │ Firebase Login
        ▼
Firebase Authentication
        │
        │ ID Token
        ▼
Spring Boot API
      │         │
      │         └──────────► Cloudinary
      │
      └────────────────────► MySQL
```

---

# Estructura del proyecto

```
src
├── config
├── controller
├── dto
├── exception
├── mapper
├── model
├── repository
├── security
├── service
└── util
```

---

# Variables de entorno

```
SPRING_DATASOURCE_URL=
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=

CLOUDINARY_CLOUD_NAME=
CLOUDINARY_API_KEY=
CLOUDINARY_API_SECRET=

FIREBASE_PROJECT_ID=
FIREBASE_CLIENT_EMAIL=
FIREBASE_PRIVATE_KEY=
```

---

# Ejecutar el proyecto

Clonar

```bash
git clone https://github.com/Emir201G/dualshare-backend.git
```

Entrar

```bash
cd dualshare-backend
```

Ejecutar

```bash
mvn spring-boot:run
```

o

```bash
docker compose up
```

---

# Endpoints principales

## Autenticación

POST /api/auth/verify

## Usuarios

GET /api/users

GET /api/users/{id}

PUT /api/users/{id}

## Historias

POST /api/stories

GET /api/stories

DELETE /api/stories/{id}

## Compartir historias

POST /api/story-recipients

---

# Proyecto relacionado

Aplicación Android:

https://github.com/Emir201G/dualshare-android

---

# Autor

Emir Guanactolay

Backend Developer | Java | Spring Boot

GitHub

https://github.com/Emir201G
