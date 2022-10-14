object AppConfig {
    private const val applicationName = "Currencies"
    const val applicationId = "com.Currencies"

    const val buildToolsVersion = "30.0.3"

    const val compileSdk = 33
    const val minSdk = 21
    const val targetSdk = 31

    const val versionCode = AppVersion.versionCode
    const val versionName =
        "$applicationName ${AppVersion.majorVersion}.${AppVersion.minorVersion}${AppVersion.stage}.${AppVersion.patchVersion}"

    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
    const val proguardConsumerRules = "consumer-rules.pro"
}