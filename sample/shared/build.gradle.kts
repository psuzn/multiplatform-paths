import com.android.build.gradle.tasks.factory.AndroidUnitTest
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.targets.js.JS

plugins {
  kotlin("multiplatform")
  id("com.android.library")
  id("org.jetbrains.compose")
}

version = "1.0-SNAPSHOT"

fun KotlinNativeTarget.configureFramework() {
  binaries.framework {
    baseName = "shared"
    isStatic = true
  }
}

kotlin {
  applyDefaultHierarchyTemplate()

  androidTarget()

  js {
    nodejs()
    browser()
    generateTypeScriptDefinitions()
  }

  jvm("desktop")

  iosArm64().configureFramework()
  iosSimulatorArm64().configureFramework()

  macosArm64().configureFramework()
  macosX64().configureFramework()

  sourceSets {

    val commonMain by getting {
      dependencies {
        implementation(compose.runtime)
        implementation(compose.ui)
        implementation(compose.material)
        implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.3.0")
        implementation(project(":platformIdentifier"))
        implementation(project(":paths"))

      }
    }

    val androidMain by getting {
      dependencies {

      }
    }

    val iosMain by getting {
      dependencies {
      }
    }

    val iosSimulatorArm64Main by getting {
      dependsOn(iosMain)
    }

    val desktopMain by getting {
      dependencies {

      }
    }
  }
}


android {
  namespace = "${Artifact.BASE_ID}.sample.common"
  compileSdk = Artifact.ANDROID_COMPILE_SDK
  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  sourceSets["main"].res.srcDirs("src/androidMain/res")
  sourceSets["main"].resources.srcDirs("src/commonMain/resources")

  defaultConfig {
    minSdk = Artifact.ANDROID_MIN_SDK
  }

  buildFeatures {
    compose = true
    buildConfig = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
}

tasks.withType<AndroidUnitTest> {
  useJUnitPlatform()
}
