rootProject.name = "Multiplatform_Utils"

pluginManagement {
  includeBuild("build-logic")
  repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
  }
}

dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}

include(":multiplatform-paths")
include(":context-provider")
include(":platform-identifier")
include(":sample:androidApp")
include(":sample:desktopApp")
include(":sample:webApp")
include(":sample:shared")
include(":sample:wearosApp")
include(":sample:macosApp")
include(":sample:nodeApp")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
