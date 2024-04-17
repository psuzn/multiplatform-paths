plugins {
  id("module")
  alias(libs.plugins.kotlin.multiplatform)
}

kotlin {

  js(IR) {
    nodejs()

    binaries.executable()
  }

  sourceSets {
    commonMain {
      dependencies {
        implementation(projects.platformIdentifier)
        implementation(projects.multiplatformPaths)

        implementation(libs.kotlinx.io.core)
      }
    }
  }
}
