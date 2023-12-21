import org.jetbrains.compose.jetbrainsCompose
import org.jetbrains.kotlin.gradle.dsl.jvm.JvmTargetValidationMode
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.KtlintPlugin


plugins {
  alias(libs.plugins.kotlin.jvm) apply false
  alias(libs.plugins.kotlin.multiplatform) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.compose) apply false
  alias(libs.plugins.ktlint) apply false
  alias(libs.plugins.mavenPublish) apply false
}

allprojects {
  group = group()
  version = versionName()

  apply<KtlintPlugin>()

  configure<KtlintExtension> {
    version.set("0.50.0")
  }

  repositories {
    google()
    mavenCentral()
    jetbrainsCompose()
  }

  tasks.withType<KotlinJvmCompile>().configureEach {
    jvmTargetValidationMode.set(JvmTargetValidationMode.WARNING)
  }

  afterEvaluate {

    extensions.findByType<PublishingExtension>()?.apply {

      publications.withType<MavenPublication>().configureEach {

        pom {
          url.set("https://github.com/psuzn/mp-utils")

          licenses {
            license {
              name.set("The Apache License, Version 2.0")
              url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
              distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
          }

          developers {
            developer {
              id.set("psuzn")
              name.set("Sujan Poudel")
              url.set("https://github.com/psuzn/")
            }
          }

          scm {
            connection.set("scm:git:https://github.com/psuzn/mp-utils.git")
            developerConnection.set("scm:git:ssh://github.com/psuzn/mp-utils.git")
            url.set("https://github.com/psuzn/mp-utils/tree/main")
          }
        }

        repositories {
          mavenLocal()
        }
      }

      extensions.findByType<SigningExtension>()?.apply {
        isRequired = true
      }
    }
  }
}
