import com.vanniktech.maven.publish.SonatypeHost

plugins {
  id("packaging")
  id("multiplatform-library")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {

  watchosArm32()
  watchosArm64()
  watchosSimulatorArm64()

  tvosArm64()
  tvosX64()
  tvosSimulatorArm64()

  androidNativeArm32()
  androidNativeArm64()
  androidNativeX64()
  androidNativeX86()

  mingwX64()

  js(IR) {
    nodejs()
    browser {
      testTask {
        enabled = false
      }
    }
    generateTypeScriptDefinitions()
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(libs.kotlinx.io.core)
      }
    }

    commonTest {
      dependencies {
        implementation(kotlin("test"))
      }
    }

    androidMain {
      dependencies {
        api(project(":context-provider"))
      }
    }

    androidUnitTest {
      dependencies {
        implementation(libs.junit)
        implementation(libs.robolectric)
        implementation(libs.ext.junit)
        implementation(libs.espresso.core)
      }
    }

    val desktopCommonMain by creating {
      dependsOn(commonMain)
    }

    desktopMain {
      dependsOn(desktopCommonMain)
    }

    jsMain {
      dependsOn(desktopCommonMain)
    }
  }
}

android {
  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()
  }
}

mavenPublishing {
  signAllPublications()
  publishToMavenCentral(SonatypeHost.S01)

  pom {
    name.set("Platform Identifier")
    description.set("Identify the current platform in Kotlin Multiplatform application")
  }
}
