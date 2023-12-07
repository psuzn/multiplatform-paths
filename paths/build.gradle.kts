import org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType.IR

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
  iosArm64()
  iosSimulatorArm64()

  tvosArm64()
  tvosX64()
  tvosSimulatorArm64()

  macosX64()
  macosArm64()

  js() {
    nodejs()
    browser()
  }

  sourceSets {

    val commonMain by getting {
      dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.3.0")
        implementation(project(":platformIdentifier"))
      }
    }

    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
      }
    }


    val androidMain by getting {
      dependencies {
        api(project(":contextProvider"))
      }
    }

    val darwinMain by creating {
      dependsOn(commonMain)
    }

    val iosMain by getting {
      dependsOn(darwinMain)
    }

    val iosSimulatorArm64Main by getting {
      dependsOn(iosMain)
    }

    val desktopCommonMain by creating {
      dependsOn(commonMain)
    }

    val desktopMain by getting {
      dependsOn(desktopCommonMain)
    }

    val macosX64Main by getting {
      dependsOn(darwinMain)
    }

    val macosArm64Main by getting {
      dependsOn(darwinMain)
    }

    val jsMain by getting() {
      dependsOn(desktopCommonMain)
    }

    val tvosMain by getting {
      dependsOn(darwinMain)
    }
  }
}

android {
  namespace = "${Artifact.BASE_ID}.paths"
  compileSdk = Artifact.ANDROID_COMPILE_SDK

  defaultConfig {
    minSdk = Artifact.ANDROID_MIN_SDK
  }

  composeOptions {
    kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
  }

  publishing {
    singleVariant("release")
  }

}
