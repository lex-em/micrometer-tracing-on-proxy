plugins {
    id("org.springframework.boot") version Versions.springBoot
    id("io.spring.dependency-management") version Versions.springBootDependencyManagement
    java
    jacoco
    idea
    `project-report`
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-actuator-autoconfigure")
    compileOnly("org.projectlombok:lombok:${Versions.lombok}")
    annotationProcessor("org.projectlombok:lombok:${Versions.lombok}")
    implementation("io.micrometer:micrometer-tracing-bridge-otel")
    implementation("io.opentelemetry:opentelemetry-exporter-otlp")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${Versions.openApiStarterVersion}")
    runtimeOnly("com.h2database:h2")
}