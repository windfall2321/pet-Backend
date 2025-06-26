# 使用官方 OpenJDK 运行环境
FROM openjdk:23-jdk-alpine

# 设置工作目录
WORKDIR /app

# 复制 jar 包到容器
COPY target/*.jar app.jar

# 启动 Spring Boot 应用
CMD ["java", "-jar", "app.jar"]