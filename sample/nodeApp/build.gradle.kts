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
        implementation(project(":platformIdentifier"))
        implementation(project(":paths"))

        implementation(libs.kotlinx.io.core)
      }
    }
  }
}

