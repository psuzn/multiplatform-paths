
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.mavenPublish)
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
  namespace = "${Artifact.BASE_ID}.contextProvider"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()
  }

  buildTypes {
    release {
      isMinifyEnabled = false
    }
  }

  testOptions {
    unitTests {
      isIncludeAndroidResources = true
    }
  }
}
