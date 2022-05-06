object Libs {

    object Android {
        // android ui
        const val appCompat = "androidx.appcompat:appcompat:${Versions.UI.appCompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.UI.coreKtx}"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.UI.lifecycle}"
        /*    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.UI.lifecycle}"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.UI.lifecycle}"*/
    }

    object Google {
        const val material =
            "com.google.android.material:material:${Versions.UI.googleMaterial}"
    }

    object Compose {
        // compose
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val toolingPreview =
            "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val activity =
            "androidx.activity:activity-compose:${Versions.UI.composeActivity}"
        const val pagination = "androidx.paging:paging-compose:${Versions.UI.pagination}"
    }
    // navigation graph

    object Nav {
        const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.UI.navigation}"
        const val composeSupport = "androidx.navigation:navigation-compose:2.5.0-alpha01"
        const val hiltComposeSupport = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"
    }

    object Testing {
        // test libs
        const val jUnit = "junit:junit:${Versions.Testing.JUnit}"
        const val extJUnit = "androidx.test.ext:junit:${Versions.Testing.extJUnit}"
        const val espressoCore =
            "androidx.test.espresso:espresso-core:${Versions.Testing.espressoCore}"
        const val compose = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
        const val navigation =
            "androidx.navigation:navigation-testing:${Versions.UI.navigation}"
        const val mockIO = "io.mockk:mockk:${Versions.Testing.mockIO}"
        const val mockIOAndroid = "io.mockk:mockk-android:${Versions.Testing.mockIO}"
    }

    object Networking {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Networking.retrofit}"
        const val gsonConverter =
            "com.squareup.retrofit2:converter-gson:${Versions.Networking.retrofit}"
        const val okHttp = "com.squareup.okhttp3:logging-interceptor:${Versions.Networking.okHttp}"
    }

    object DI {
        // DI
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }

    object ThirdParty {
        const val landscape = "com.github.skydoves:landscapist-coil:1.5.1"
    }
}
