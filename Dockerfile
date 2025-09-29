# Usar imagem base do OpenJDK
FROM openjdk:17-jdk-slim

# Criar diretório da aplicação
WORKDIR /app

# Copiar o JAR gerado para dentro do container
COPY target/*.jar app.jar

# Expõe a porta do Spring Boot (padrão 8080)
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
