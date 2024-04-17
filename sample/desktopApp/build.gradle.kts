plugins {
  id("module")
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.compose)
}

kotlin {
  jvm {}
  sourceSets {
    val jvmMain by getting {
      dependencies {
        implementation(projects.sample.shared)

        implementation(compose.desktop.currentOs)
      }
    }
  }
}

compose.desktop {
  application {
    mainClass = "MainKt"
  }
}
