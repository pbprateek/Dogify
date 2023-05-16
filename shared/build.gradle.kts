import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("io.realm.kotlin") version "1.8.0"
}

kotlin {
    android()

    jvm("desktop") {
        jvmToolchain(11)
    }

//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach {
//        it.binaries.framework {
//            baseName = "shared"
//        }
//    }

    sourceSets {

        val ktorVersion = "2.3.0"
        val koin_version= "3.4.0"
        val voyagerVersion = "1.0.0-rc06"

        val commonMain by getting {
            dependencies {

                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                //Koin
                api("io.insert-koin:koin-core:$koin_version")
                implementation ("io.insert-koin:koin-compose:1.0.1")

                //voyager
                implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")

                //kotr
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                //Searalization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.1")
                //Coroutine
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.7.1")
                //relm
                implementation("io.realm.kotlin:library-base:1.8.0")

                //Image Loader
                api("io.github.qdsfdhvh:image-loader:1.4.2")
            }
        }



        //Android
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }


        //Desktop
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }




        //Ios
//        val iosX64Main by getting
//        val iosArm64Main by getting
//        val iosSimulatorArm64Main by getting
//        val iosMain by creating {
//            dependsOn(commonMain)
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
//            dependencies {
//                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
//            }
//        }
//        val iosX64Test by getting
//        val iosArm64Test by getting
//        val iosSimulatorArm64Test by getting
//        val iosTest by creating {
//            dependsOn(commonTest)
//            iosX64Test.dependsOn(this)
//            iosArm64Test.dependsOn(this)
//            iosSimulatorArm64Test.dependsOn(this)
//        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}