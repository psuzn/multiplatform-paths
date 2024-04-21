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

package me.sujanpoudel.utils.paths

import kotlinx.io.files.Path
import me.sujanpoudel.utils.platformIdentifier.Platform
import me.sujanpoudel.utils.platformIdentifier.platform

private val platform = platform() as Platform.JS

actual fun dataDirectory(appId: String): Path = when (platform) {
  is Platform.JS.Node -> desktopAppHomeDirectory(
    appId = appId,
    os = platform.os,
    getEnv = { eval("""process.env["$it"]""") as String },
  )

  else -> error("Non node environment")
}

actual fun cacheDirectory(appId: String): Path = when (platform) {
  is Platform.JS.Node -> desktopCacheDirectory(
    appId = appId,
    os = platform.os,
    getEnv = {
      eval("""process.env["$it"]""") as String
    },
  )

  else -> error("Non node environment")
}
