import org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType.IR
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
  kotlin("multiplatform")
  id("com.android.library")
  `maven-publish`
  signing
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
  applyDefaultHierarchyTemplate()

  androidTarget()

  jvm("desktop")

  iosArm64()
  iosX64()
  iosSimulatorArm64()

  macosX64()
  macosArm64()

  watchosArm32()
  watchosArm64()
  watchosSimulatorArm64()

  tvosArm64()
  tvosX64()
  tvosSimulatorArm64()

  androidNativeArm32()
  androidNativeArm64()
  androidNativeX64()
  androidNativeX86()

  mingwX64()

  js(IR) {
    nodejs()
    browser()
    generateTypeScriptDefinitions()
  }

  sourceSets {
   val commonMain by getting {
      dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.3.0")
      }
    }

    commonTest {
      dependencies {
        implementation(kotlin("test"))
      }
    }

    androidMain {
      dependencies {
        api(project(":contextProvider"))
      }
    }

    val appleMain by getting() {

    }

    val desktopCommonMain by creating {
      dependsOn(commonMain)
    }

    val desktopMain by getting {
      dependsOn(desktopCommonMain)
    }

    jsMain {
      dependsOn(desktopCommonMain)
    }

  }
}

android {
  namespace = "${Artifact.BASE_ID}.platformIdentifier"
  compileSdk = Artifact.ANDROID_COMPILE_SDK

  defaultConfig {
    minSdk = Artifact.ANDROID_MIN_SDK

    ndk {
      abiFilters += listOf(
        "armeabi-v7a",
        "arm64-v8a",
        "x86",
        "x86_64"
      )
    }
  }

  composeOptions {
    kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
  }
}
