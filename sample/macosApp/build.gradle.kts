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

import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetWithHostTests

plugins {
  id("module")
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.compose)
}

fun KotlinNativeTargetWithHostTests.makeExecutableBinary() {
  binaries {
    executable {
      entryPoint = "main"
    }
  }
}

kotlin {
  sourceSets {
    macosArm64().makeExecutableBinary()
    macosX64().makeExecutableBinary()

    macosMain {
      dependencies {
        implementation(projects.sample.shared)

        implementation(compose.ui)
      }
    }
  }
}
