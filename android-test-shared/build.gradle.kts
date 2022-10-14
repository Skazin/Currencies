plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = AppConfig.compileSdk
    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    lint {
        // Version changes are beyond our control, so don't warn. The IDE will still mark these.
        disable("GradleDependency")
    }

}

dependencies {
    implementation(project(":model"))
    implementation(project(":shared"))

    // Kotlin
    implementation(Libs.KOTLIN)
}