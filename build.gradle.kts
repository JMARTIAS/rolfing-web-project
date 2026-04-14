plugins {
    id("java")
    id("war")
}

group = "com.rolfing"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // Servlet API
    providedCompile("javax.servlet:javax.servlet-api:4.0.1")
    
    // Logging
    implementation("org.slf4j:slf4j-api:2.0.5")
    implementation("ch.qos.logback:logback-classic:1.4.6")
    
    // Jetty para ejecutar como servidor embebido
    implementation("org.eclipse.jetty:jetty-server:11.0.15")
    implementation("org.eclipse.jetty:jetty-servlet:11.0.15")
    implementation("org.eclipse.jetty:jetty-webapp:11.0.15")
    implementation("org.eclipse.jetty:jetty-util:11.0.15")
    
    // Testing
    testImplementation("junit:junit:4.13.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.war {
    archiveFileName.set("rolfing.war")
}

// Crear un JAR ejecutable (Fat JAR) con todas las dependencias
tasks.register<Jar>("fatJar") {
    group = "build"
    description = "Crea un JAR ejecutable con todas las dependencias"
    archiveFileName.set("rolfing-app.jar")
    
    // Incluir archivos compilados
    from(sourceSets["main"].output)
    
    // Incluir todas las dependencias
    from(configurations.runtimeClasspath.get().map { 
        if (it.isDirectory) it else zipTree(it) 
    })
    
    // Configurar el manifest con la clase principal
    manifest {
        attributes("Main-Class" to "com.rolfing.server.JettyServer")
        attributes("Implementation-Title" to "Rolfing Web Project")
        attributes("Implementation-Version" to version)
    }
}

// Evitar conflictos de duplicados
tasks.getByName<Jar>("fatJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.build {
    dependsOn("fatJar")
}

// Crear una tarea customizada para ejecutar el servidor
tasks.register<JavaExec>("runServer") {
    group = "application"
    description = "Ejecuta el servidor Jetty"
    mainClass.set("com.rolfing.server.JettyServer")
    classpath = sourceSets["main"].runtimeClasspath
    dependsOn("classes")
}
