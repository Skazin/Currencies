plugins {
    id("com.android.library")
    kotlin("android")
    id ("kotlin-kapt")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildToolsVersion

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = AppConfig.androidTestInstrumentation

        val serverUrl = findProperty("api.url") as String
        buildConfigField("String", "API_URL", serverUrl)

        val apiKey = findProperty("api.key") as String
        buildConfigField("String", "API_KEY", apiKey)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

        }
        getByName("debug") {}
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER_VERSION
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        val options = this
        options.jvmTarget = "1.8"
    }
}

dependencies {

    //Model module
    implementation(project(":model"))

    // Test shared module
    testImplementation(project(":test-shared"))
    testImplementation(project(":android-test-shared"))

    implementation(Libs.MATERIAL_DESIGN)

    //Base
    implementation(Libs.APP_COMPAT)
    implementation(Libs.CORE_KTX)

    //Lifecycle
    implementation(Libs.LIFECYCLE_COMPOSE)
    kapt(Libs.LIFECYCLE_KAPT)
    implementation(Libs.LIFECYCLE_LIVEDATA)
    implementation(Libs.LIFECYCLE_PROCESSLIFECYCLEOWNER)
    implementation(Libs.LIFECYCLE_RUNTIME)
    implementation(Libs.LIFECYCLE_VIEWMODEL)

    //Lottie
    implementation(Libs.LOTTIE)

    //Compose
    implementation(Libs.ACTIVITY_COMPOSE)
    implementation(Libs.COMPOSE_COMPILER)
    implementation(Libs.COMPOSE_MATERIAL)
    implementation(Libs.COMPOSE_UI)
    implementation(Libs.COMPOSE_UI_TOOLING_PREVIEW)

    //Coroutines
    implementation(Libs.COROUTINES)

    //Dagger Hilt
    implementation(Libs.DAGGER_HILT)
    implementation(Libs.DAGGER_HILT_ANDROID_PLUGIN)
    kapt(Libs.DAGGER_HILT_COMPILER)

    //Data Store
    api(Libs.DATA_STORE)
    api(Libs.DATA_STORE_CORE)

    //Room
    implementation(Libs.ROOM_RUNTIME)
    implementation(Libs.ROOM_KTX)
    kapt(Libs.ROOM_COMPILER)

    // Local unit tests
    testImplementation(Libs.ASSERTJ)
    androidTestImplementation(Libs.ASSERTJ)
    testImplementation(Libs.JUNIT)
    testImplementation(Libs.TURBINE)
    androidTestImplementation(Libs.TURBINE)
    testImplementation(Libs.MOCKITO_CORE)
    testImplementation(Libs.MOCKITO_KOTLIN)
    testImplementation(Libs.MOCKK)
    testImplementation(Libs.POWER_MOCK)
    testImplementation(Libs.LIVEDATA_TEST)
    testImplementation(Libs.NAVIGATION_TEST)
    testImplementation(Libs.COROUTINES_TEST)

    //Navigation
    implementation(Libs.NAVIGATION_COMPOSE)
    implementation(Libs.NAVIGATION_UI)
    implementation(Libs.NAVIGATION_FRAGMENT)

    //Retrofit
    api(Libs.MOSHI_CONVERTER)
    api(Libs.OKHTTP)
    api(Libs.RETROFIT)

    //Moshi
    api(Libs.MOSHI)
    api(Libs.MOSHI_KOTLIN)
    kapt(Libs.MOSHI_KAPT)

    //Timber
    implementation(Libs.TIMBER)

    //Dagger Hilt
    testImplementation(Libs.DAGGER_HILT)
    testImplementation(Libs.DAGGER_HILT_ANDROID_PLUGIN)
    testImplementation(Libs.DAGGER_HILT_COMPOSE)
    kaptTest(Libs.DAGGER_HILT_COMPILER)
}