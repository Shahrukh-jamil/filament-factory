plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.factory.placeholder"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.factory.placeholder"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        ndk {
            abiFilters += listOf("arm64-v8a", "armeabi-v7a")
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("release-key.jks")
            // ifEmpty handles both null and empty string from GitHub Actions env
            storePassword = System.getenv("KEYSTORE_PASSWORD")?.ifEmpty { "sj292004sja@A12" } ?: "sj292004sja@A12"
            keyAlias = System.getenv("KEY_ALIAS")?.ifEmpty { "drawmathic-pi-key" } ?: "drawmathic-pi-key"
            keyPassword = System.getenv("KEY_PASSWORD")?.ifEmpty { "sj292004sja@A12" } ?: "sj292004sja@A12"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false
            signingConfig = signingConfigs.getByName("release")
        }
    }

    aaptOptions {
        noCompress += listOf("filamat", "ktx", "glb", "gltf")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")

    // Filament Math Utilities
    implementation("dev.romainguy:kotlin-math:1.5.3")

    // Google Filament 3D PBR Engine, glTF 2.0 Loader & Lifecycle Utilities
    implementation("com.google.android.filament:filament-android:1.56.0")
    implementation("com.google.android.filament:gltfio-android:1.56.0")
    implementation("com.google.android.filament:filament-utils-android:1.56.0")
}
