rootProject.name = "MP_Utils"

pluginManagement {
  repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
  }
}

plugins {
  id("build.less") version "1.0.0-rc2"
  id("org.gradle.toolchains.foojay-resolver-convention") version("0.7.0")
}

buildscript {
  dependencies {
    classpath("com.google.guava:guava") {
      version {
        strictly("32.1.3-android")
      }
    }
  }
}

include(":paths")
include(":context-provider")
include(":platform-identifier")
include(":sample:androidApp")
include(":sample:desktopApp")
include(":sample:webApp")
include(":sample:shared")
include(":sample:wearosApp")
include(":sample:macosApp")
include(":sample:nodeApp")
