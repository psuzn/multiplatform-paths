plugins {
  id("android-application")
}

kotlin {

  applyDefaultHierarchyTemplate()

  androidTarget()

  sourceSets {

    androidMain {
      dependencies {
        implementation(projects.sample.shared)
        implementation(projects.platformIdentifier)
        implementation(libs.appcompat)
        implementation(libs.activity.compose)
      }
    }
  }
}
