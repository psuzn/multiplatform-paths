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

import io.gitlab.arturbosch.detekt.Detekt
import org.jlleitschuh.gradle.ktlint.tasks.KtLintCheckTask
import org.jlleitschuh.gradle.ktlint.tasks.KtLintFormatTask


plugins {
  alias(libs.plugins.kotlin.jvm) apply false
  alias(libs.plugins.kotlin.multiplatform) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.compose) apply false
  alias(libs.plugins.ktlint) apply false
  alias(libs.plugins.detekt) apply false
  alias(libs.plugins.mavenPublish) apply false
  alias(libs.plugins.binaryCompatibilityValidator) apply false
  id("module")
  base
}

tasks.withType<KtLintFormatTask> {
  dependsOn(gradle.includedBuild("build-logic").task(":ktlintFormat"))
}

tasks.withType<KtLintCheckTask> {
  dependsOn(gradle.includedBuild("build-logic").task(":ktlintCheck"))
}

tasks.withType<Detekt> {
  dependsOn(gradle.includedBuild("build-logic").task(":detekt"))
}
