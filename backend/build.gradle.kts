plugins {
	java
	id("org.springframework.boot") version "3.5.7"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.graciano"
version = "0.0.1-SNAPSHOT"
description = "RAG system for intelligent document analysis. Upload your documents and chat about them using AI"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

extra["springAiVersion"] = "1.0.3"

dependencies {
    // Spring Boot Starters
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")

    // Spring AI - Core
	implementation("org.springframework.ai:spring-ai-starter-model-ollama")
	implementation("org.springframework.ai:spring-ai-starter-vector-store-pgvector")
	implementation("org.springframework.ai:spring-ai-tika-document-reader")

    // Apache Tika (Document Processing)
    implementation("org.apache.tika:tika-parsers-standard-package:3.2.3")

    // JWT Authentication
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")

    // Language Detection
    //implementation("com.github.pemistahl:lingua:1.2.2")

    // Database
	runtimeOnly("org.postgresql:postgresql")
    implementation("org.liquibase:liquibase-core")

    // Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

    // Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.ai:spring-ai-bom:${property("springAiVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
