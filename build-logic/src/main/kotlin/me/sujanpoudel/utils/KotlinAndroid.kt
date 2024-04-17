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

package me.sujanpoudel.utils

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.tasks.factory.AndroidUnitTest
import nameSpace
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension<*, *, *, *, *>) = with(commonExtension) {
  namespace = nameSpace()
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = libs.versions.minSdk.get().toString().toInt()
  }

  lint {
    warningsAsErrors = true
    abortOnError = true
    disable +=
      listOf(
        "GradleDependency",
        "AndroidGradlePluginVersion",
      )
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  buildFeatures {
    buildConfig = true
  }

  tasks.withType<AndroidUnitTest> {
    useJUnitPlatform()
  }

  testOptions {
    unitTests {
      isIncludeAndroidResources = true
    }
  }

  configureKotlin()
}

/**
 * Configure base Kotlin options
 */
fun Project.configureKotlin() {
  tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
      jvmTarget = JavaVersion.VERSION_17.toString()
    }
  }
}
