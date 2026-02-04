plugins {
    id("java")
    id("war")
    id("io.freefair.lombok") version "9.1.0"
    id("application") // ¡Debe ir aquí!
}

group = "com.programacion.avanzada"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.jboss:jandex:3.1.2")
    implementation("jakarta.enterprise:jakarta.enterprise.cdi-api:4.0.1")

    // Weld SE (CDI en consola)
    implementation("org.jboss.weld.se:weld-se-core:5.1.2.Final")

    // Source: https://mvnrepository.com/artifact/org.mongodb.morphia/morphia
    implementation("dev.morphia.morphia:morphia-core:2.4.8")
    implementation("org.mongodb:mongodb-driver-sync:4.11.1")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("programacion.avanzada.Main") // tu clase Main completa
}
