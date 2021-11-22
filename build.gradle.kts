import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
//	id("com.github.davidmc24.gradle.plugin.avro") version "1.1.0"

	kotlin("jvm") version "1.6.0"
	kotlin("plugin.spring") version "1.6.0"
	kotlin("plugin.jpa") version "1.6.0"
}

group = "com.goodcode"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
val jacksonVersion = "2.9.6"
val kafkaVersion = "2.6.0"

//avro {
//	fieldVisibility.set("PRIVATE")
//	stringType.set("CharSequence")
//}

repositories {
	mavenCentral()
}


dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.postgresql:postgresql")

//	implementation("org.springframework.kafka:spring-kafka")
//	implementation("org.springframework.kafka:spring-kafka-test")
//	implementation("org.apache.kafka:kafka-clients:$kafkaVersion")
//	implementation("org.springframework.boot:spring-boot-starter-tomcat")
//	implementation("io.projectreactor.kafka:reactor-kafka:1.2.2.RELEASE")

	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
