import org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType.IR
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.android.library)
  alias(libs.plugins.mavenPublish)
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
    browser{
      testTask{
        enabled = false
      }
    }
    generateTypeScriptDefinitions()
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(libs.kotlinx.io.core)
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

    val androidUnitTest by getting {
      dependencies {
        implementation(libs.junit)
        implementation(libs.robolectric)
        implementation(libs.ext.junit)
        implementation(libs.espresso.core)
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
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()

    ndk {
      abiFilters += listOf(
        "armeabi-v7a",
        "arm64-v8a",
        "x86",
        "x86_64"
      )
    }
  }

  testOptions {
    unitTests {
      isIncludeAndroidResources = true
    }
  }
}
