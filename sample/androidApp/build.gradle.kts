plugins {
  id("android-application")
}

kotlin {
  androidTarget()

  sourceSets {
    val androidMain by getting {
      dependencies {
        implementation(projects.sample.shared)
        implementation(projects.platformIdentifier)

        implementation(libs.activity.compose)
        implementation(libs.core.ktx)
      }
    }
  }
}
