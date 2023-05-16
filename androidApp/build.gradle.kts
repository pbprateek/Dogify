plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "com.example.dogify.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.10"))
//
//
   implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
   implementation("androidx.core:core-ktx:1.9.0")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0-beta01")
//
    implementation("androidx.compose.ui:ui:1.3.3")
//    // Tooling support (Previews, etc.)
//    implementation("androidx.compose.ui:ui-tooling:1.3.3")
//    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.3.1")
//    // Material Design
//    implementation("androidx.compose.material:material:1.3.1")
//    // Material design icons
//    implementation("androidx.compose.material:material-icons-core:1.3.1")
//    implementation("androidx.compose.material:material-icons-extended:1.3.1")
//    // Integration with activities
    implementation("androidx.activity:activity-compose:1.6.1")
//    // Animations
//    implementation("androidx.compose.animation:animation:1.3.3")
//    // Integration with ViewModels
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
//
//    //Coil Image
//    implementation("io.coil-kt:coil-compose:2.2.2")
}