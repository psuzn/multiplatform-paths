import org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType.IR

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
  iosArm64()
  iosSimulatorArm64()

  tvosArm64()
  tvosX64()
  tvosSimulatorArm64()

  macosX64()
  macosArm64()

  js() {
    nodejs()
  }

  sourceSets {

    val commonMain by getting {
      dependencies {
        implementation(project(":platformIdentifier"))
        implementation(libs.kotlinx.io.core)
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
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()
  }

  publishing {
    singleVariant("release")
  }

}
