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
import platform.Foundation.NSApplicationSupportDirectory
import platform.Foundation.NSCachesDirectory
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask

class IosDirectoriesTest : DirectoriesTest() {
  override fun exceptedCacheDir(appId: String): Path {
    return NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, true)
      .firstOrNull()?.toString()?.toPath() ?: error("Unable to get 'NSCachesDirectory'")
  }

  override fun exceptedDataDir(appId: String): Path {
    return NSSearchPathForDirectoriesInDomains(NSApplicationSupportDirectory, NSUserDomainMask, true)
      .firstOrNull()?.toString()?.toPath()
      ?.let { it / appId } ?: error("Unable to get 'NSApplicationSupportDirectory'")
  }
}
