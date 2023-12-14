plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.android.application)
  alias(libs.plugins.compose)
}

kotlin {

  applyDefaultHierarchyTemplate()

  androidTarget()

  sourceSets {

    androidMain {
      dependencies {
        implementation(project(":sample:shared"))
        implementation(project(":platformIdentifier"))
        implementation (libs.appcompat)
        implementation(libs.activity.compose)
      }
    }
  }
}

android {
  namespace = "${Artifact.BASE_ID}.sample.wearOs"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    applicationId = "${Artifact.BASE_ID}.sample"
    minSdk = 30
    targetSdk = libs.versions.targetSdk.get().toInt()
    versionCode = Artifact.VERSION_CODE
    versionName = Artifact.VERSION_NAME

    vectorDrawables {
      useSupportLibrary = true
    }
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
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


