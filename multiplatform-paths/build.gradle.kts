import com.vanniktech.maven.publish.SonatypeHost

plugins {
  id("packaging")
  id("multiplatform-library")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {

  tvosArm64()
  tvosX64()
  tvosSimulatorArm64()

  js(IR) {
    nodejs()
    generateTypeScriptDefinitions()
  }

  sourceSets {

    val commonMain by getting {
      dependencies {
        implementation(projects.platformIdentifier)
        api(libs.kotlinx.io.core)
      }
    }

    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
      }
    }

    val androidMain by getting {
      dependencies {
        implementation(projects.contextProvider)
      }
    }

    val darwinMain by creating {
      dependsOn(commonMain)
    }

    val iosMain by getting {
      dependsOn(darwinMain)
    }

    val iosSimulatorArm64Main by getting {
      dependsOn(iosMain)
    }

    val desktopCommonMain by creating {
      dependsOn(commonMain)
    }

    val desktopMain by getting {
      dependsOn(desktopCommonMain)
    }

    val macosX64Main by getting {
      dependsOn(darwinMain)
    }

    val macosArm64Main by getting {
      dependsOn(darwinMain)
    }

    val jsMain by getting {
      dependsOn(desktopCommonMain)
    }

    val tvosMain by getting {
      dependsOn(darwinMain)
    }
  }
}

@Suppress("ktlint:standard:max-line-length")
mavenPublishing {
  signAllPublications()
  publishToMavenCentral(SonatypeHost.S01)

  pom {
    name.set("Paths")
    description.set(
      "Get platform specific app data and cache directory(equivalent to ApplicationInfo.dataDir or NSHomeDirectory) in Kotlin Multiplatform application",
    )
  }
}
