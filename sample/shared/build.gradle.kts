import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
  id("multiplatform-library")
  alias(libs.plugins.compose)
}

version = "1.0-SNAPSHOT"
group = "${group()}.sample"

fun KotlinNativeTarget.configureFramework() {
  binaries.framework {
    baseName = "shared"
    isStatic = true
  }
}

kotlin {
  applyDefaultHierarchyTemplate()

  js {
    nodejs()
  }

  iosArm64().configureFramework()
  iosSimulatorArm64().configureFramework()
  macosArm64().configureFramework()
  macosX64().configureFramework()

  sourceSets {

    val commonMain by getting {
      dependencies {
        implementation(projects.platformIdentifier)
        implementation(projects.multiplatformPaths)

        implementation(compose.runtime)
        implementation(compose.ui)
        implementation(compose.material)
        implementation(libs.kotlinx.io.core)
      }
    }
  }
}

android {
  buildFeatures {
    compose = true
    buildConfig = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
  }
}
