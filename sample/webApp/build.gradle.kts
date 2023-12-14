
plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.compose)
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
        
        implementation(project(":sample:shared"))
      }
    }
  }

}

compose.experimental {
  web.application { }
}

