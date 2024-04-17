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

import com.android.build.gradle.LibraryExtension
import me.sujanpoudel.utils.apply
import me.sujanpoudel.utils.configureCommonMultiplatformTargets
import me.sujanpoudel.utils.configureKotlinAndroid
import me.sujanpoudel.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiPlatformLibraryConventionPlugin : Plugin<Project> {
  override fun apply(target: Project): Unit = with(target) {
    with(pluginManager) {
      apply(libs.plugins.android.library)
      apply(libs.plugins.kotlin.multiplatform)
      apply("module")
    }

    configure<LibraryExtension> {
      configureKotlinAndroid(this)
    }

    configure<KotlinMultiplatformExtension> {
      configureCommonMultiplatformTargets()
    }
  }
}
