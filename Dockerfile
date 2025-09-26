# ---------- Etapa de construcción ----------
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# Copiar Maven Wrapper y pom.xml primero para aprovechar la cache
COPY .mvn/ .mvn/
COPY mvnw mvnw
COPY pom.xml .

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Construcción del JAR
RUN ./mvnw clean package -DskipTests

# ---------- Etapa de ejecución ----------
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiar solo el JAR desde la etapa de compilación
COPY --from=builder /app/target/back-0.0.1-SNAPSHOT.jar app.jar

# Exponer puerto (por convención Spring Boot usa 8080)
EXPOSE 8080

# Ejecutar en el puerto dinámico que Koyeb define con la variable de entorno $PORT
CMD ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]