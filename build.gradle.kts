// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("io.ktor.plugin") version "2.3.4"
}

application {
    mainClass.set("com.mgok.cafemenu.ApplicationKt")
}

dependencies {

}
