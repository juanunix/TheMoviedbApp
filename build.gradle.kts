// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.benmanes.versions).apply(false)
    alias(libs.plugins.cash.paparazzi).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.detekt).apply(true) // Needs to be applied at the root, unlike others.
    alias(libs.plugins.google.dagger.hilt).apply(false)
    alias(libs.plugins.google.ksp).apply(false) version "2.1.20-2.0.0" apply false
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlinter).apply(false)
    alias(libs.plugins.square.sort.dependencies).apply(false)
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false

}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48.1")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.50.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    }
}


apply(from = "buildscripts/githooks.gradle")
apply(from = "buildscripts/versionsplugin.gradle")

subprojects {
    apply(from = "../buildscripts/detekt.gradle")

    apply(plugin = "com.squareup.sort-dependencies")
    apply(plugin = "org.jmailen.kotlinter")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

afterEvaluate {
    // We install the hook at the first occasion
    tasks.named("clean") {
//        dependsOn(":installGitHooks")
    }
}

tasks {
    /**
     * The detektAll tasks enables parallel usage for detekt so if this project
     * expands to multi module support, detekt can continue to run quickly.
     *
     * https://proandroiddev.com/how-to-use-detekt-in-a-multi-module-android-project-6781937fbef2
     */
    @Suppress("UnusedPrivateMember")
    val detektAll by registering(io.gitlab.arturbosch.detekt.Detekt::class) {
        parallel = true
        setSource(files(projectDir))
        include("**/*.kt")
        include("**/*.kts")
        exclude("**/resources/**")
        exclude("**/build/**")
        config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
        buildUponDefaultConfig = true
    }
}
