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

import io.gitlab.arturbosch.detekt.DetektPlugin
import org.jlleitschuh.gradle.ktlint.KtlintPlugin

plugins {
  `kotlin-dsl`
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.ktlint)
  alias(libs.plugins.detekt)
}

kotlin {
  jvmToolchain(11)
}

repositories {
  mavenCentral()
  gradlePluginPortal()
  google()
}

dependencies {
  compileOnly(libs.plugins.kotlin.jvm.asDependency())
  compileOnly(libs.plugins.kotlin.multiplatform.asDependency())
  compileOnly(libs.plugins.compose.asDependency())
  compileOnly(libs.plugins.android.application.asDependency())
  compileOnly(libs.plugins.android.library.asDependency())
  compileOnly(libs.plugins.ktlint.asDependency())
  compileOnly(libs.plugins.detekt.asDependency())
  compileOnly(libs.plugins.mavenPublish.asDependency())

  implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

allprojects {
  apply<KtlintPlugin>()
  apply<DetektPlugin>()

  ktlint {
    version = rootProject.libs.versions.ktlint.get()

    filter {
      exclude {
        it.file.absoluteFile.startsWith(layout.buildDirectory.asFile.get().absolutePath)
      }
    }
  }

  detekt {
    config.setFrom(rootProject.files("./../config/detekt/detekt.yml"))
  }
}

gradlePlugin {
  plugins {
    register("modulePlugin") {
      id = "module"
      implementationClass = "ModuleConventionPlugin"
    }

    register("androidApplication") {
      id = "android-application"
      implementationClass = "AndroidAppConventionPlugin"
    }

    register("multiplatformLibraryPlugin") {
      id = "multiplatform-library"
      implementationClass = "MultiPlatformLibraryConventionPlugin"
    }

    register("packagingPlugin") {
      id = "packaging"
      implementationClass = "PackagingConventionPlugin"
    }
  }
}

fun Provider<PluginDependency>.asDependency(): Provider<String> =
  this.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }
