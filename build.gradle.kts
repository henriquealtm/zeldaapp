// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(AppDependencies.buildToolsClassPath)
        classpath(AppDependencies.gradlePluginClassPath)
        classpath(AppDependencies.navigationSageArgsClassPath)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}