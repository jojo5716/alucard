import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.flywaydb.flyway") version "6.0.7"

	kotlin("jvm") version "1.6.0"
	kotlin("plugin.spring") version "1.6.0"
	kotlin("plugin.jpa") version "1.6.0"
}

group = "com.goodcode"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
val jacksonVersion = "2.9.6"
val camundaVersion = "7.13.0"
val springVersion = "2.2.5.RELEASE"
val kafkaVersion = "2.8.0"

apply(plugin = "org.flywaydb.flyway")


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
	implementation("org.apache.httpcomponents:httpclient:4.5.12")
	implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter-external-task-client:7.16.0")
	implementation("org.flywaydb:flyway-core")
	implementation("org.camunda.bpm:camunda-engine:$camundaVersion")
	implementation("org.camunda.bpm.dmn:camunda-engine-dmn:$camundaVersion")
	implementation("org.springframework.kafka:spring-kafka:$kafkaVersion")

	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion") {
		exclude(module = "mockito-core")
		exclude(group = "org.junit.jupiter")
		exclude(group = "org.junit.vintage")
	}
	testImplementation("org.camunda.bpm.extension:camunda-bpm-junit5:1.0.0")
	testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
	testImplementation("junit:junit:4.13.2")
	testImplementation("org.camunda.bpm.assert:camunda-bpm-assert:5.0.0")

//	providedCompile group: 'javax.servlet', name: 'javax.servlet-api', version:'3.1.0'

//	compile group: 'commons-io', name: 'commons-io', version:'2.6'
//	compile group: 'com.camunda.consulting.util', name: 'camunda-util-demo-support', version:'0.4.3'
//	compile group: 'javax.xml.bind', name: 'jaxb-api', version:'2.2.11'
//	compile group: 'javax.activation', name: 'activation', version:'1.1.1'
//
//	testCompile group: 'org.camunda.bpm.assert', name: 'camunda-bpm-assert', version:'5.0.0'
//	testCompile group: 'org.assertj', name: 'assertj-core', version:'3.14.0'
//	testCompile group: 'org.camunda.spin', name: 'camunda-spin-dataformat-all', version:'1.5.1'
//	testCompile group: 'org.camunda.bpm', name: 'camunda-engine-plugin-spin', version: camundaBPMVersion
//	testCompile group: 'commons-lang', name: 'commons-lang', version: '2.4'
//	testCompile group: 'com.h2database', name: 'h2', version:'1.4.197'
//	testCompile group: 'ch.qos.logback', name: 'logback-classic', version:'1.2.3'
//	testCompile group: 'org.codehaus.groovy', name: 'groovy-all', version: '2.4.15'
//	testCompile group: 'org.amshove.kluent', name: 'kluent', version: '1.61'
//	testCompile group: 'com.pholser', name: 'junit-quickcheck-generators', version: '1.0'


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
