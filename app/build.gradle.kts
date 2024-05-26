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
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // habilitar viewBinding
        viewBinding {
            enable = true
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
}

    dependencies {
        val lifecycleVersion = "2.8.0"


        // Core KTX
        implementation("androidx.core:core-ktx:1.13.1")

        // ViewModel
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

        // LiveData
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

        // Lifecycle only (without ViewModel or LiveData)
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")

        // Retrofit
        implementation("com.squareup.retrofit2:retrofit:2.9.0")

        // Gson
        implementation("com.google.code.gson:gson:2.10.1")

        // Gson Converter
        implementation("com.squareup.retrofit2:converter-gson:2.3.0")

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
        annotationProcessor("androidx.room:room-compiler:2.6.1")

        // Navigation Component
        implementation(libs.androidx.navigation.runtime)
        implementation(libs.androidx.navigation.fragment.ktx)

        // Support Annotations
        implementation(libs.support.annotations)

}
}