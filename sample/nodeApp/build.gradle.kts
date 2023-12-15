plugins {
  alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
  applyDefaultHierarchyTemplate()

  js(IR) {
    nodejs {}

    binaries.executable()
  }

  sourceSets {
    commonMain {
      dependencies {
        implementation(project(":platform-identifier"))
        implementation(project(":paths"))

        implementation(libs.kotlinx.io.core)
      }
    }
  }
}

