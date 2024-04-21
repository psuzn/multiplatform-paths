import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import me.sujanpoudel.utils.apply
import me.sujanpoudel.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.getByType
import org.jlleitschuh.gradle.ktlint.KtlintExtension

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

class ModuleConventionPlugin : Plugin<Project> {
  override fun apply(target: Project): Unit = with(target) {
    group = group()
    version = versionName()

    with(target.pluginManager) {
      apply(libs.plugins.ktlint)
      apply(libs.plugins.detekt)
    }

    configureKtLint()
    configureDetekt()
  }
}

private fun Project.configureKtLint(): KtlintExtension = extensions.getByType<KtlintExtension>().apply {
  version = rootProject.libs.versions.ktlint.get()

  enableExperimentalRules = false
  coloredOutput = true

  filter {
    exclude {
      it.file.absoluteFile.startsWith(layout.buildDirectory.asFile.get().absolutePath)
    }
  }
}

private fun Project.configureDetekt(): DetektExtension = extensions.getByType<DetektExtension>().apply {
  config.setFrom(rootProject.files("config/detekt/detekt.yml"))
}
