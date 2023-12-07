plugins {
  kotlin("multiplatform")
  id("com.android.application")
  id("org.jetbrains.compose")
}

kotlin {
  androidTarget()

  sourceSets {
    val androidMain by getting {
      dependencies {
        implementation(project(":sample:shared"))
        implementation(project(":platformIdentifier"))
        api("androidx.activity:activity-compose:1.8.1")
        api("androidx.core:core-ktx:1.12.0")
      }
    }
  }
}

android {
  compileSdk = Artifact.ANDROID_COMPILE_SDK
  namespace = "${Artifact.BASE_ID}.sample"

  defaultConfig {
    applicationId = "${Artifact.BASE_ID}.sample"
    minSdk = Artifact.ANDROID_MIN_SDK
    targetSdk = Artifact.ANDROID_TARGET_SDK
    versionCode = Artifact.VERSION_CODE
    versionName = Artifact.VERSION_NAME
  }

  composeOptions {
    kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
    }
  }


}
