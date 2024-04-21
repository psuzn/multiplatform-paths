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

import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
  id("multiplatform-library")
  alias(libs.plugins.compose)
}

version = "1.0-SNAPSHOT"
group = "${group()}.sample"

fun KotlinNativeTarget.configureFramework() {
  binaries.framework {
    baseName = "shared"
    isStatic = true
  }
}

kotlin {
  applyDefaultHierarchyTemplate()

  js {
    nodejs()
  }

  iosArm64().configureFramework()
  iosSimulatorArm64().configureFramework()
  macosArm64().configureFramework()
  macosX64().configureFramework()

  sourceSets {

    val commonMain by getting {
      dependencies {
        implementation(projects.platformIdentifier)
        implementation(projects.multiplatformPaths)

        implementation(compose.runtime)
        implementation(compose.ui)
        implementation(compose.material)
        implementation(libs.kotlinx.io.core)
      }
    }
  }
}

android {
  buildFeatures {
    compose = true
    buildConfig = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
  }
}
