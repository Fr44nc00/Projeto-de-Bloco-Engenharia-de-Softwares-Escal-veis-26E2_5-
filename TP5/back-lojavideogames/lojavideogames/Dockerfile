# Usar uma imagem base do Java
FROM eclipse-temurin:21-jdk

# Definir diretório de trabalho dentro do container
WORKDIR /app

# Copiar o JAR gerado pelo build do Maven/Gradle
COPY target/lojavideogames-0.0.1-SNAPSHOT.jar app.jar

# Comando para rodar o microsserviço
ENTRYPOINT ["java","-jar","app.jar"]
