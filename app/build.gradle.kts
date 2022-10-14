plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    id ("kotlin-kapt")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildToolsVersion

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = AppConfig.androidTestInstrumentation

        val apiKey = findProperty("api.key") as String
        resValue("string", "API_KEY", apiKey)
    }

    buildTypes {
        // Release
        getByName("release") {

            isMinifyEnabled = false
//            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        // Debug
        getByName("debug") {
            isTestCoverageEnabled = true

        }
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

    //Modules
    implementation(project(":shared"))
    implementation(project(":model"))
    testImplementation(project(":test-shared"))
    testImplementation(project(":android-test-shared"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Libs.MATERIAL_DESIGN)

    //Base
    implementation(Libs.APP_COMPAT)
    implementation(Libs.CORE_KTX)
    implementation(Libs.CONSTRAINT_LAYOUT)

    //Lifecycle
    implementation(Libs.LIFECYCLE_COMPOSE)
    implementation(project(mapOf("path" to ":shared")))
    kapt(Libs.LIFECYCLE_KAPT)
    implementation(Libs.LIFECYCLE_LIVEDATA)
    implementation(Libs.LIFECYCLE_PROCESSLIFECYCLEOWNER)
    implementation(Libs.LIFECYCLE_RUNTIME)
    implementation(Libs.LIFECYCLE_VIEWMODEL)

    //Lottie
    implementation(Libs.LOTTIE)

    //Compose
    implementation(Libs.ACTIVITY_COMPOSE)
    implementation(Libs.COMPOSE_ACCOMPANIST)
    implementation(Libs.COMPOSE_ACCOMPANIST_DRAWABLE_PAINTER)
    implementation(Libs.COMPOSE_ACCOMPANIST_UI)
    implementation(Libs.COMPOSE_ACCOMPANIST_PAGER)
    implementation(Libs.COMPOSE_ACCOMPANIST_PAGER_INDICATOR)
    implementation(Libs.COMPOSE_ACCOMPANIST_SYSTEM_UI)
    implementation(Libs.COMPOSE_ACCOMPANIST_PERMISSIONS)
    implementation(Libs.COMPOSE_COMPILER)
    implementation(Libs.COMPOSE_CONSTRAINT)
    implementation(Libs.COMPOSE_UI)
    implementation(Libs.COMPOSE_UI_TOOLING_PREVIEW)
    implementation(Libs.COMPOSE_MATERIAL)
    implementation(Libs.COMPOSE_PAGING)
    implementation(Libs.COMPOSE_RUNTIME)

    //Coroutines
    implementation(Libs.COROUTINES)

    //Dagger Hilt
    implementation(Libs.DAGGER_HILT)
    implementation(Libs.DAGGER_HILT_ANDROID_PLUGIN)
    implementation(Libs.DAGGER_HILT_COMPOSE)
    kapt(Libs.DAGGER_HILT_COMPILER)

    //Flow
    testImplementation(Libs.TURBINE)

    // Local unit tests
    androidTestUtil(Libs.ANDROID_TEXT_ORCHESTRATOR)
    debugImplementation(Libs.COMPOSE_UI_TEST_MANIFEST)
    testImplementation(Libs.ASSERTJ)
    testImplementation(Libs.MOCKITO_CORE)
    testImplementation(Libs.MOCKITO_KOTLIN)
    testImplementation(Libs.MOCKK)
    testImplementation(Libs.POWER_MOCK)
    testImplementation(Libs.LIVEDATA_TEST)
    testImplementation(Libs.MOCKK_AGENT)
    kaptAndroidTest(Libs.DAGGER_HILT_COMPILER)
    testImplementation(Libs.COROUTINES_TEST)

    testImplementation(Libs.DAGGER_HILT_TESTING)
    kaptTest(Libs.DAGGER_HILT_COMPILER)
    testAnnotationProcessor(Libs.DAGGER_HILT_COMPILER)

    //Navigation
    implementation(Libs.NAVIGATION_COMPOSE)
    implementation(Libs.NAVIGATION_UI)
    implementation(Libs.NAVIGATION_FEATURE)
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

    implementation("androidx.annotation:annotation-experimental:1.3.0")
}