/*
 * Copyright 2024 Sujan Poudel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
