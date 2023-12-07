plugins {
  kotlin("multiplatform")
  id("com.android.application")
  id("org.jetbrains.compose")
}

kotlin {

  applyDefaultHierarchyTemplate()

  androidTarget()

  sourceSets {

    androidMain {
      dependencies {

        implementation("com.google.android.gms:play-services-wearable:18.1.0")
        implementation(project.dependencies.platform("androidx.compose:compose-bom:2023.08.00"))
        implementation(project(":sample:shared"))
        implementation(project(":platformIdentifier"))

        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.wear.compose:compose-material:1.2.1")
        implementation("androidx.wear.compose:compose-foundation:1.2.1")
        implementation("androidx.activity:activity-compose:1.8.1")
      }
    }
  }
}

android {
  namespace = "${Artifact.BASE_ID}.sample.wearOs"
  compileSdk = Artifact.ANDROID_COMPILE_SDK

  defaultConfig {
    applicationId = "${Artifact.BASE_ID}.sample"
    minSdk = 30
    targetSdk = Artifact.ANDROID_TARGET_SDK
    versionCode = Artifact.VERSION_CODE
    versionName = Artifact.VERSION_NAME

    vectorDrawables {
      useSupportLibrary = true
    }
  }

  composeOptions {
    kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}


