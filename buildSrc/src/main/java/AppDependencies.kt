import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {

    const val buildToolsClassPath = "com.android.tools.build:gradle:${Versions.gradle}"
    const val gradlePluginClassPath = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationSageArgsClassPath =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    // Std lib
    private const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    // Core
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    // Koin
    private const val koin = "io.insert-koin:koin-android:${Versions.koin}"

    // LifeCycle
    private const val liveDataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveDataKtx}"

    // Navigation
    private const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    private const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    // Network
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    private const val retrofitConverter = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val gson = "com.google.code.gson:gson:2.8.9"
    private const val okhttp = "com.squareup.okhttp3:okhttp:4.9.2"
    private const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.2"

    // UI
    private const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private const val material = "com.google.android.material:material:${Versions.material}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"

    // Unit test
    private const val junit = "junit:junit:${Versions.junit}"
    private const val koinTest = "io.insert-koin:koin-test:${Versions.koinTest}"
    private const val mockk = "io.mockk:mockk:${Versions.mockk}"
    private const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
    private const val coroutinesTesting =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTesting}"

    // Instrumented test
    private const val instrumentedJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    private const val espressoContrib =
        "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    private const val runner = "androidx.test:runner:${Versions.runner}"
    private const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    private const val navigationTesting = "androidx.navigation:navigation-testing:${Versions.navigationTesting}"

    // Debug Implementation For Instrumented Test
    const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragmentTesting}"

    val coreLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(coreKtx)
        add(appcompat)
    }

    val koinLibraries = arrayListOf<String>().apply {
        add(koin)
    }

    val lifecycleLibraries = arrayListOf<String>().apply {
        add(liveDataKtx)
    }

    val navigationLibraries = arrayListOf<String>().apply {
        add(navigationFragment)
        add(navigationUi)
    }

    val networkLibraries = arrayListOf<String>().apply {
        add(retrofit)
        add(retrofitConverter)
        add(gson)
        add(okhttp)
        add(okhttpInterceptor)
    }

    val uiLibraries = arrayListOf<String>().apply {
        add(constraintLayout)
        add(material)
    }

    val unitTestLibraries = arrayListOf<String>().apply {
        add(junit)
        add(koinTest)
        add(mockk)
        add(coreTesting)
        add(coroutinesTesting)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(instrumentedJUnit)
        add(espressoCore)
        add(espressoContrib)
        add(runner)
        add(koinTest)
        add(coroutinesTesting)
        add(mockkAndroid)
        add(navigationTesting)
        add(koin)
    }
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}