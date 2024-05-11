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

    val androidUnitTest by getting {
      dependencies {
        implementation(libs.junit)
        implementation(libs.ext.junit)
        implementation(libs.espresso.core)
        implementation(libs.robolectric)
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

android {
  testOptions {
    unitTests {
      isIncludeAndroidResources = true
    }
  }
}

@Suppress("ktlint:standard:max-line-length")
mavenPublishing {
  pom {
    name.set("Multiplatform Paths")
    description.set(
      "Get platform specific app data and cache directory(equivalent to ApplicationInfo.dataDir or NSApplicationSupportDirectory) in Kotlin Multiplatform application",
    )
  }
}
