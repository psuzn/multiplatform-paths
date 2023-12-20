import com.vanniktech.maven.publish.SonatypeHost

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.mavenPublish)
  signing
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
  namespace = "$group.contextProvider"
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
  signAllPublications()
  publishToMavenCentral(SonatypeHost.S01)

  pom {
    name.set("Platform Identifier")
    description.set("Get android context anywhere on your android source-set.")
  }
}
