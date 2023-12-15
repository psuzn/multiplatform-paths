pluginManagement {
  repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
  }
}

rootProject.name = "MP_Utils"

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
