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

import org.gradle.api.Project
import org.gradle.kotlin.dsl.support.uppercaseFirstChar

fun Project.group() = property("GROUP") as String

fun Project.versionName() = property("VERSION_NAME") as String

@Suppress("FunctionOnlyReturningConstant")
fun Project.sampleVersionCode() = 1

@Suppress("FunctionOnlyReturningConstant")
fun Project.sampleVersionName() = "0.0.1"

private fun Project.nameAsIdentifier() = name
  .split("-")
  .mapIndexed { index: Int, name: String ->
    if (index == 0) {
      name
    } else {
      name.uppercaseFirstChar()
    }
  }.joinToString("")

fun Project.nameSpace() = "$group.${nameAsIdentifier()}"
