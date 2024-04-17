import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetWithHostTests

plugins {
  id("module")
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.compose)
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
        implementation(projects.sample.shared)

        implementation(compose.ui)
      }
    }
  }
}
