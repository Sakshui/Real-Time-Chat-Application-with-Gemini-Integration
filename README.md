# Real-Time Chat Application with Gemini AI Integration

A real-time chat application built with **Spring Boot** that integrates **Gemini (AI)** for intelligent chatbot responses. The project demonstrates WebSocket-based real-time messaging, JWT-based authentication, and server-side AI integration to power conversational features.

---

## 🔥 Highlights / Features

* Real-time messaging using **WebSockets / STOMP**
* **GeminiService**: Server-side integration with the Gemini API for AI responses
* **JWT Authentication** for secure user access
* Simple static frontend included (`src/main/resources/static/ChatApp.html`) for demo/testing
* User management (signup / login) and persistence with Spring Data JPA
* CORS configuration for single-page apps or external frontends

---

## 🛠 Tech Stack

* Java 17+ (project configured with Maven wrapper)
* Spring Boot (Web, WebSocket, Security, Data JPA)
* Gemini API for AI responses
* JWT for authentication

---

## 📂 Project Structure (selected files)

```
src/main/java/com/sakshisb/chatbot/
  ├─ config/
  │   ├─ WebSocketConfig.java
  │   ├─ SecurityConfig.java
  │   └─ CorsConfig.java
  ├─ controller/
  │   ├─ AuthController.java        # signup / login (JWT)
  │   └─ ChatController.java        # websocket endpoints / message routing
  ├─ model/
  │   ├─ ChatMessage.java
  │   └─ User.java
  ├─ repository/
  │   └─ UserRepository.java
  ├─ security/
  │   └─ JwtAuthenticationFilter.java
  └─ service/
      ├─ GeminiService.java         # integrates with Gemini API
      ├─ JwtService.java
      └─ UserService.java

src/main/resources/static/
  ├─ ChatApp.html
  └─ LoginSignup.html

application.properties
pom.xml
```

---

## ⚙️ Configuration

Update `src/main/resources/application.properties` with your environment settings. Important values to configure:

```properties
# Server
server.port=8080

# Datasource (H2 default or MySQL)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA
spring.jpa.hibernate.ddl-auto=update

# Gemini API
# Set your Gemini API key / endpoint as environment variables or application properties
gemini.api.key=${GEMINI_API_KEY}
gemini.api.url=https://api.gemini.example/...

# JWT
jwt.secret=your_jwt_secret
jwt.expiration=3600000
```

**Security note:** Keep `jwt.secret` and `GEMINI_API_KEY` out of source control — use environment variables in production.

---

## ▶️ Running Locally

### Using Maven wrapper (recommended)

```bash
# from project root
./mvnw spring-boot:run
```

### Build and run jar

```bash
./mvnw clean package
java -jar target/chatbot-0.0.1-SNAPSHOT.jar
```

### Demo frontend (static)

Open `http://localhost:8080/ChatApp.html` (or `LoginSignup.html`), create an account, login, and start a chat.

---

## 👨‍💻 Author

**Sakshi Acharekar** — [sakshiacharekar202@gmail.com](mailto:sakshiacharekar202@gmail.com)

---
