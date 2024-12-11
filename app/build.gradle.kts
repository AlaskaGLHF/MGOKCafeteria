plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.mgokcafeteria"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mgokcafeteria"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    packaging {
        resources {
            excludes.add("META-INF/io.netty.versions.properties")
            excludes.add("META-INF/INDEX.LIST")
            excludes.add("META-INF/gradle/incremental.annotation.processors")
            excludes.add("META-INF/DEPENDENCIES")
            excludes.add("META-INF/LICENSE")
            excludes.add("META-INF/LICENSE.txt")
            excludes.add("META-INF/NOTICE")
            excludes.add("META-INF/NOTICE.txt")
        }

        buildFeatures {
            compose = true
        }
    }

    dependencies {
        // Android dependencies
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.ui)
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.ui.tooling.preview)
        implementation(libs.androidx.material3)
        implementation(libs.androidx.constraintlayout)
        implementation(libs.androidx.cardview)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)

        // Ktor client dependencies
        implementation("io.ktor:ktor-client-core:2.3.1") // Основной клиент
        implementation("io.ktor:ktor-client-android:2.3.1") // Клиент для Android
        implementation("io.ktor:ktor-client-cio:2.3.1") // Клиент с использованием CIO
        implementation("io.ktor:ktor-client-json:2.3.1") // Для работы с JSON
        implementation("io.ktor:ktor-client-serialization:2.3.1") // Для сериализации JSON

        // Сериализация
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0") // Сериализация JSON

        // Exposed and PostgreSQL dependencies
        implementation("org.jetbrains.exposed:exposed-core:0.43.0")
        implementation("org.jetbrains.exposed:exposed-dao:0.43.0")
        implementation("org.jetbrains.exposed:exposed-jdbc:0.43.0")
        implementation("org.postgresql:postgresql:42.6.0")

        // Lottie for animations
        implementation("com.airbnb.android:lottie:5.0.3")

        // RecyclerView
        implementation("androidx.recyclerview:recyclerview:1.2.1")

        // Navigation
        implementation("androidx.navigation:navigation-compose:2.6.0")

        // Material3
        implementation("androidx.compose.material3:material3:1.0.0-alpha15")
        implementation("androidx.compose.material:material-icons-extended:1.4.0")

        implementation("org.jetbrains.exposed:exposed-core:0.43.0")
        implementation("org.jetbrains.exposed:exposed-dao:0.43.0")
        implementation("org.jetbrains.exposed:exposed-jdbc:0.43.0")
        implementation("org.postgresql:postgresql:42.6.0")

        implementation ("androidx.compose.material:material:1.4.0")  // Библиотека Material
        implementation ("androidx.compose.ui:ui:1.4.0")

        implementation ("androidx.compose.material3:material3:1.0.0")

    }
}
