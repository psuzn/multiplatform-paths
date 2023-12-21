import com.android.build.gradle.tasks.factory.AndroidUnitTest
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.android.library)
  alias(libs.plugins.compose)
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
        implementation(project(":platform-identifier"))
        implementation(project(":paths"))

        implementation(compose.runtime)
        implementation(compose.ui)
        implementation(compose.material)
        implementation(libs.kotlinx.io.core)
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
  namespace = "$group.sample.common"
  compileSdk = libs.versions.compileSdk.get().toInt()

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  sourceSets["main"].res.srcDirs("src/androidMain/res")
  sourceSets["main"].resources.srcDirs("src/commonMain/resources")

  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()
  }

  buildFeatures {
    compose = true
    buildConfig = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
}

tasks.withType<AndroidUnitTest> {
  useJUnitPlatform()
}
