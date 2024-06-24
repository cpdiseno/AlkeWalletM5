plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    //id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.kapt")

    id("com.google.devtools.ksp") version "1.9.0-1.0.13"

}

android {
    namespace = "pena.camila.alkewalletm5"
    compileSdk = 34

    defaultConfig {
        applicationId = "pena.camila.alkewallet"
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
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        kotlinOptions {
            jvmTarget = "1.8"
        }

        // habilitar viewBinding
        viewBinding {
            enable = true
        }

    }

    dependencies {
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat) // Librería de compatibilidad para características más antiguas de Android
        implementation(libs.material) // Librería de Material Design para componentes de interfaz de usuario
        implementation(libs.androidx.activity)// Librería para actividades de Android
        implementation(libs.androidx.constraintlayout) // Librería para diseños ConstraintLayout
        implementation(libs.androidx.navigation.fragment.ktx) // Librería para navegación entre fragmentos
        implementation(libs.androidx.navigation.ui.ktx) // Librería para integrar la navegación con la interfaz de usuario
        implementation(libs.androidx.annotation) // Librería deanotaciones de AndroidX
        implementation(libs.androidx.lifecycle.livedata.ktx) // Librería para LiveData con extensiones de Kotlin
        implementation(libs.androidx.lifecycle.viewmodel.ktx) // Librería para ViewModel con extensiones de Kotlin

        // Librerías de redandroidx.room:room-compiler:2.4.2
        implementation(libs.retrofit) // Librería para realizar solicitudes de red
        implementation(libs.converter.gson) // Conversor de Gson para Retrofit, para serializar/deserializar datos JSON
        implementation(libs.logging.interceptor) // Interceptor para registrar solicitudes y respuestas de red

        // Librerías de base de datos ROOM
        implementation(libs.androidx.room.runtime)
       ksp(libs.androidx.room.compiler)
        implementation(libs.androidx.room.ktx) // Extensiones de Kotlin para Room

        // Librerías de Firebase
        //  implementation(platform(libs.firebase.bom)) // Plataforma de Firebase para gestionar dependencias
        // implementation(libs.firebase.analytics) // Librería de análisis de Firebase

        // Librerías de prueba
        androidTestImplementation(libs.androidx.junit) // Librería de pruebas de AndroidX JUnit
        androidTestImplementation(libs.androidx.espresso.core) // Librería de pruebas de AndroidX Espresso
        //   androidTestImplementation(libs.mockwebserver.v490) // Librería MockWebServer para pruebas de red
        testImplementation(libs.junit) // Librería JUnit para pruebas unitarias
        // testImplementation(libs.mockito.mockito.core) // Librería Mockito para crear objetos simulados
        //  testImplementation(libs.mockito.inline) // Soporte en línea para Mockito
        //  testImplementation(libs.mockwebserver) // Librería MockWebServer para pruebas de red
        //  testImplementation(libs.retrofit.mock) // Adaptador de Retrofit para MockWebServer
        //  testImplementation(libs.converter.gson.v290) // Conversor de Gson para Retrofit
    }