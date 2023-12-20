plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.android.application)
  alias(libs.plugins.compose)
}

kotlin {
  androidTarget()

  sourceSets {
    val androidMain by getting {
      dependencies {
        implementation(project(":sample:shared"))
        implementation(project(":platform-identifier"))

        implementation(libs.activity.compose)
        implementation(libs.core.ktx)
      }
    }
  }
}

android {
  compileSdk = libs.versions.compileSdk.get().toInt()
  namespace = "$group.sample"

  defaultConfig {
    applicationId = "$group.sample"
    minSdk = libs.versions.minSdk.get().toInt()
    targetSdk = libs.versions.targetSdk.get().toInt()
    versionCode = sampleVersionCode()
    versionName = sampleVersionName()
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
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
