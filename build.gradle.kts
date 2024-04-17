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
