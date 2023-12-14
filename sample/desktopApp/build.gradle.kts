import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.compose)
}

kotlin {
  jvm {}
  sourceSets {
    val jvmMain by getting {
      dependencies {
        implementation(project(":sample:shared"))

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
