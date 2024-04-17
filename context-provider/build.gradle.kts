
plugins {
  id("module")
  id("packaging")
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.multiplatform)
}

kotlin {

  jvmToolchain(11)

  androidTarget()

  sourceSets {
    androidMain {
      dependencies {
        api(libs.startup.runtime)
      }
    }

    val androidUnitTest by getting {
      dependencies {
        implementation(libs.junit)
        implementation(libs.ext.junit)
        implementation(libs.espresso.core)
        implementation(libs.robolectric)
      }
    }
  }
}

android {
  namespace = "${group()}.contextProvider"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()
  }

  testOptions {
    unitTests {
      isIncludeAndroidResources = true
    }
  }
}

mavenPublishing {
  pom {
    name.set("Platform Identifier")
    description.set("Get android context anywhere on your android source-set.")
  }
}
