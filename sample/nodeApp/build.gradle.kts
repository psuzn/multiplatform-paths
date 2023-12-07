plugins {
  kotlin("multiplatform")
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
        implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.3.0")
        implementation(project(":platformIdentifier"))
        implementation(project(":paths"))
      }
    }
  }
}

