import org.gradle.api.NamedDomainObjectContainer

//app level config constants
object AppConfig {
    const val compileSdk = 32
    const val minSdk = 21
    const val targetSdk = 32
    const val versionCode = 1
    const val versionName = "1.0.0"

    const val customInstrumentedRunner = "com.example.application.InstrumentedTestRunner"
    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
    const val proguardConsumerRules = "consumer-rules.pro"
    const val release = "release"
    const val proguardRules = "proguard-rules.pro"
    const val proguardOptimize = "proguard-android-optimize.txt"
}