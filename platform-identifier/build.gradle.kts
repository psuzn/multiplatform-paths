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
  pom {
    name.set("Platform Identifier")
    description.set("Identify the current platform in Kotlin Multiplatform application")
  }
}
