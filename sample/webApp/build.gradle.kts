
plugins {
  kotlin("multiplatform")
  id("org.jetbrains.compose")
}

kotlin {
  applyDefaultHierarchyTemplate()

  js(IR) {
    browser {
      commonWebpackConfig {}
    }
    binaries.executable()
  }

  sourceSets {
    commonMain {
      dependencies {
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.ui)
        implementation(compose.material3)
        implementation(compose.materialIconsExtended)

        implementation(project(":sample:shared"))
      }
    }
  }

}

compose.experimental {
  web.application { }
}

