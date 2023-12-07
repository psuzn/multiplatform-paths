
plugins {
  id("com.android.library")
  kotlin("multiplatform")
  `maven-publish`
  signing
}


kotlin {

  jvmToolchain(11)

  androidTarget()

  sourceSets {
    androidMain {
      dependencies {
        api("androidx.startup:startup-runtime:1.1.1")
      }
    }

    val androidUnitTest by getting {
      dependencies {
        implementation("junit:junit:4.13.2")
        implementation("androidx.test.ext:junit:1.1.5")
        implementation("androidx.test.espresso:espresso-core:3.5.1")
        implementation("org.robolectric:robolectric:4.11.1")
      }
    }
  }
}

android {
  namespace = "${Artifact.BASE_ID}.contextProvider"
  compileSdk = Artifact.ANDROID_COMPILE_SDK

  defaultConfig {
    minSdk = Artifact.ANDROID_MIN_SDK
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
