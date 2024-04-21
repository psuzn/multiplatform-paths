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

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import me.sujanpoudel.utils.apply
import me.sujanpoudel.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.signing.SigningExtension

class PackagingConventionPlugin : Plugin<Project> {
  override fun apply(target: Project): Unit = with(target) {
    group = group()
    version = versionName()

    with(pluginManager) {
      apply(libs.plugins.mavenPublish)
      apply("signing")
      apply(libs.plugins.binaryCompatibilityValidator)
    }

    configurePublishingMetadata()

    extensions.getByType<MavenPublishBaseExtension>().apply {
      signAllPublications()
      publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    }
  }
}

fun Project.configurePublishingMetadata(): PublishingExtension = extensions.getByType<PublishingExtension>().apply {
  publications.withType<MavenPublication>().configureEach {
    pom {
      url.set("https://github.com/psuzn/multiplatform-paths")

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
        connection.set("scm:git:https://github.com/psuzn/multiplatform-paths.git")
        developerConnection.set("scm:git:ssh://github.com/psuzn/multiplatform-paths.git")
        url.set("https://github.com/psuzn/multiplatform-paths/tree/main")
      }
    }

    repositories {
      mavenLocal()
    }
  }

  extensions.getByType<SigningExtension>().apply {
    isRequired = true
  }
}
