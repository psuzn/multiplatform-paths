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

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import me.sujanpoudel.utils.apply
import me.sujanpoudel.utils.configureKotlinAndroid
import me.sujanpoudel.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class AndroidAppConventionPlugin : Plugin<Project> {
  override fun apply(target: Project): Unit = with(target) {
    with(pluginManager) {
      apply(libs.plugins.android.application)
      apply(libs.plugins.kotlin.multiplatform)
      apply(libs.plugins.compose)
      apply("module")
    }

    configure<BaseAppModuleExtension> {
      configureKotlinAndroid(this)

      defaultConfig {
        applicationId = nameSpace()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = sampleVersionCode()
        versionName = sampleVersionName()

        vectorDrawables {
          useSupportLibrary = true
        }

        composeOptions {
          kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
        }

        packaging {
          resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
          }
        }
      }
    }

    configure<KotlinMultiplatformExtension> {
      androidTarget()
    }
  }
}
