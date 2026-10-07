plugins {
    `java-library`
    `maven-publish`
    id("org.openjfx.javafxplugin") version "0.1.0"
}
group = "dev.normlanguage"
version = "2"
repositories { mavenCentral() }
java { toolchain { languageVersion = JavaLanguageVersion.of(25) } }
javafx { version = "25.0.2"; modules("javafx.base", "javafx.graphics", "javafx.controls"); configuration = "api" }
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
tasks.withType<JavaCompile>().configureEach { options.encoding = "UTF-8"; options.compilerArgs.add("-parameters") }
tasks.withType<Test>().configureEach { useJUnitPlatform(); jvmArgs("--enable-native-access=ALL-UNNAMED"); testLogging { events("passed", "failed") } }
tasks.withType<Jar>().configureEach { from("LICENSE") { into("META-INF") } }
tasks.withType<AbstractArchiveTask>().configureEach { isPreserveFileTimestamps = false; isReproducibleFileOrder = true }
publishing {
    publications { create<MavenPublication>("library") { from(components["java"]) } }
    repositories { maven { url = uri(layout.buildDirectory.dir("repository")) } }
}
