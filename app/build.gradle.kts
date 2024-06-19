plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
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

        // habilitar viewBinding
        viewBinding {
            enable = true
        }
        buildFeatures {
            viewBinding = true
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

        viewBinding {
            enable = true
        }
    }

    dependencies {
        val lifecycleVersion = "2.8.0"
        val navVersion = "2.7.3"
        implementation(libs.androidx.navigation.fragment.ktx.v273)
        implementation(libs.androidx.navigation.ui.ktx.v273)

        // Core KTX
        implementation(libs.androidx.core.ktx)

        // ViewModel
        implementation(libs.androidx.lifecycle.viewmodel.ktx)

        // LiveData
        implementation(libs.androidx.lifecycle.livedata.ktx.v280)

        // Lifecycle only (without ViewModel or LiveData)
        implementation(libs.androidx.lifecycle.runtime.ktx)

        // Retrofit
        implementation(libs.retrofit)

        // Gson
        implementation(libs.gson)

        // Gson Converter
        implementation(libs.converter.gson)

        // AndroidX AppCompat
        implementation(libs.androidx.appcompat)

        // Material Design
        implementation(libs.material)

        // AndroidX Activity
        implementation(libs.androidx.activity)

        // AndroidX ConstraintLayout
        implementation(libs.androidx.constraintlayout)

        // Testing
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)


        // Room
        annotationProcessor(libs.androidx.room.compiler)
        annotationProcessor(libs.androidx.room.compiler)

        // Navigation Component
        implementation(libs.androidx.navigation.runtime)
        implementation(libs.androidx.navigation.fragment.ktx)

        // Support Annotations
        implementation(libs.support.annotations)

        //Implementar las liberias para el uso de Retrofit
        implementation(libs.retrofit)
        implementation(libs.converter.gson)

        implementation("androidx.vectordrawable:vectordrawable:1.1.0")

        implementation(libs.filament.android)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.activity)
        implementation(libs.androidx.constraintlayout)
        implementation(libs.navigation.fragment.ktx)
        implementation(libs.navigation.ui.ktx)
}
}