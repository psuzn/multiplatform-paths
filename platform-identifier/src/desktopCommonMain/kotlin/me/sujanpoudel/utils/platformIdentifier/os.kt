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

package me.sujanpoudel.utils.platformIdentifier

enum class DesktopOs {
  Macos,
  Linux,
  Windows,
  Unknown,
}

private fun hostOs(name: String) = name.lowercase().let { osName ->
  when {
    osName.startsWith("mac") ||
      osName.startsWith("osx") ||
      osName.startsWith("darwin") -> DesktopOs.Macos

    osName.startsWith("win") -> DesktopOs.Windows
    osName.startsWith("linux") -> DesktopOs.Linux
    else -> DesktopOs.Unknown
  }
}

internal fun hostOs(osName: String, archName: String, version: String): Platform.OS {
  val arch = hostArch(archName)
  return when (hostOs(osName)) {
    DesktopOs.Macos -> Platform.OS.MacOs(arch, version)
    DesktopOs.Linux -> Platform.OS.Linux(arch, version)
    DesktopOs.Windows -> Platform.OS.Windows(arch, version)
    DesktopOs.Unknown -> Platform.OS.Unknown(arch, version)
  }
}
