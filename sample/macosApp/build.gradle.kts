import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetWithHostTests

plugins {
  kotlin("multiplatform")
  id("org.jetbrains.compose")
}

fun KotlinNativeTargetWithHostTests.makeExecutableBinary() {
  binaries {
    executable {
      entryPoint = "main"
    }
  }
}

kotlin {
  sourceSets {
    macosArm64().makeExecutableBinary()
    macosX64().makeExecutableBinary()

    macosMain {
      dependencies {
        implementation(compose.ui)
        implementation(compose.foundation)
        implementation(compose.material)
        @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
        implementation(compose.components.resources)
        implementation(project(":sample:shared"))
      }
    }
  }
}


