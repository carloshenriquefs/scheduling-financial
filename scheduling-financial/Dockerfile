# Use a imagem oficial do Maven para construir o aplicativo
FROM maven:3.8-openjdk-11 AS build

# Configuração do diretório de trabalho
WORKDIR /app

# Copie o arquivo POM e os arquivos necessários
COPY pom.xml .
COPY src ./src

# Construa o aplicativo com o Maven
RUN mvn clean package

# Use a imagem oficial do OpenJDK 11 para executar o aplicativo
FROM openjdk:11-jre-slim

# Configuração do diretório de trabalho
WORKDIR /app

# Copie o JAR construído a partir do estágio de construção
COPY --from=build /app/target/*.jar app.jar

# Comando para executar o aplicativo
CMD ["java", "-jar", "app.jar"]