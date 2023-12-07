import org.jetbrains.kotlin.gradle.dsl.jvm.JvmTargetValidationMode
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.KtlintPlugin

plugins {
  kotlin("jvm") version Versions.KOTLIN apply false
  kotlin("multiplatform") version Versions.KOTLIN apply false
  kotlin("android") version Versions.KOTLIN apply false
  id("com.android.application") version Versions.AGP apply false
  id("com.android.library") version Versions.AGP apply false
  id("org.jetbrains.compose") version Versions.COMPOSE apply false
  id("org.jlleitschuh.gradle.ktlint") version "11.5.0" apply true
  id("com.vanniktech.maven.publish") version "0.25.3" apply false
}

allprojects {
  apply<KtlintPlugin>()

  repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }

  configure<KtlintExtension> {
    version.set("0.50.0")
    filter {
      exclude {
        it.file.path.contains("/build")
      }
    }
  }

  tasks.withType<KotlinJvmCompile>().configureEach {
    jvmTargetValidationMode.set(JvmTargetValidationMode.WARNING)
  }

}

