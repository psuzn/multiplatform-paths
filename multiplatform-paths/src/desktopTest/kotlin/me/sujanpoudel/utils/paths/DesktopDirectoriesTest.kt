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
import me.sujanpoudel.utils.paths.utils.div
import me.sujanpoudel.utils.paths.utils.toPath
import me.sujanpoudel.utils.platformIdentifier.Platform
import me.sujanpoudel.utils.platformIdentifier.hostOs

class DesktopDirectoriesTest : DirectoriesTest() {
  private fun getEnv(key: String) = System.getenv(key) as String

  override fun exceptedCacheDir(appId: String): Path {
    return when (hostOs) {
      is Platform.OS.Linux -> getEnv("HOME").toPath() / ".cache" / appId
      is Platform.OS.MacOs -> getEnv("HOME").toPath() / "Library/Caches" / appId
      is Platform.OS.Windows -> getEnv("APPDATA").toPath() / "Caches" / appId
      else -> error("not supported")
    }
  }

  override fun exceptedDataDir(appId: String): Path {
    return when (hostOs) {
      is Platform.OS.Linux -> getEnv("HOME").toPath() / ".local/share" / appId
      is Platform.OS.MacOs -> getEnv("HOME").toPath() / "Library/Application Support" / appId
      is Platform.OS.Windows -> getEnv("APPDATA").toPath() / appId
      else -> error("not supported")
    }
  }
}
