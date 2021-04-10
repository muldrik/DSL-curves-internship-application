import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.31"
    application
}

group = "me.muldrik"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    maven("https://jetbrains.bintray.com/lets-plot-maven")
}

dependencies {
    val lets_plot_version = "2.0.1"
    val lets_plot_api_version = "1.3.0"
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.lets-plot:lets-plot-batik:$lets_plot_version")
    api("org.jetbrains.lets-plot:lets-plot-common:$lets_plot_version")
    api("org.jetbrains.lets-plot-kotlin:lets-plot-kotlin-api:$lets_plot_api_version")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    testImplementation(kotlin("test-junit5"))
}

tasks.withType<Test>().all {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}