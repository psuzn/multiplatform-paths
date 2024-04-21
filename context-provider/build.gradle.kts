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
  id("module")
  id("packaging")
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.multiplatform)
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
  namespace = "${group()}.contextProvider"
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
  pom {
    name.set("Platform Identifier")
    description.set("Get android context anywhere on your android source-set.")
  }
}
