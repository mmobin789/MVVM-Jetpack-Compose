plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    viewBinding {
        android.buildFeatures.viewBinding = true
    }

    defaultConfig {
        applicationId = "com.scalio"
        minSdk = 24
        compileSdk = 31
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            // Enables code shrinking, obfuscation, and optimization for only
            // your project's release build type.
            isMinifyEnabled = true

            // Enables resource shrinking, which is performed by the
            // Android Gradle plugin.
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            applicationIdSuffix = ".dev"
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(Libs.Android.coreKtx)
    implementation(Libs.Android.appCompat)
    implementation(Libs.Android.lifecycle)

    testImplementation(Libs.Testing.jUnit)
    testImplementation(Libs.Testing.mockIO)
    androidTestImplementation(Libs.Testing.mockIOAndroid)
    androidTestImplementation(Libs.Testing.extJUnit)
    androidTestImplementation(Libs.Testing.espressoCore)
    androidTestImplementation(Libs.Testing.compose)

    debugImplementation(Libs.Compose.tooling)
    implementation(Libs.Compose.activity)
    implementation(Libs.Compose.material)
    implementation(Libs.Compose.toolingPreview)
    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.pagination)

    implementation(Libs.Nav.ui)
    implementation(Libs.Nav.composeSupport)
    implementation(Libs.Nav.hiltComposeSupport)
    androidTestImplementation(Libs.Testing.navigation)

    implementation(Libs.DI.hilt)
    kapt(Libs.DI.hiltCompiler)

    implementation(Libs.Google.material)

    implementation(Libs.Networking.retrofit)
    implementation(Libs.Networking.gsonConverter)
    implementation(Libs.Networking.okHttp)
}
