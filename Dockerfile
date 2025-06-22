FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY pom.xml ./
COPY mvnw ./
COPY .mvn .mvn

# Dependency’larni yuklab olish
RUN ./mvnw dependency:go-offline

# Loyihani to‘liq nusxalash va build
COPY . .

# Build (jar fayl yaratish)
RUN ./mvnw clean package -DskipTests

# --- Run stage ---
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Jar faylni nusxalash
COPY --from=builder /app/target/xbank-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
