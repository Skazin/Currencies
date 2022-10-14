plugins {
    id("java-library")
    kotlin("jvm")
}

dependencies {

    // Kotlin
    implementation(Libs.KOTLIN)

    // Test
    implementation(Libs.JUNIT)
    api(Libs.COROUTINES_TEST)
}