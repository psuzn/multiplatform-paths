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

import platform.Foundation.NSProcessInfo
import platform.darwin.TARGET_OS_SIMULATOR
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
actual fun platform(): Platform {
  val nativePlatform = Platform

  val arch = nativePlatform.cpuArchitecture.asArch()
  val version = NSProcessInfo.processInfo.operatingSystemVersionString

  val isSimulator = TARGET_OS_SIMULATOR != 0

  return when (nativePlatform.osFamily) {
    OsFamily.MACOSX -> Platform.OS.MacOs(arch, version)
    OsFamily.IOS -> Platform.OS.IOS(arch, version, isSimulator)
    OsFamily.TVOS -> Platform.OS.TvOs(arch, version, isSimulator)
    OsFamily.WATCHOS -> Platform.OS.WatchOs(arch, version, isSimulator)
    else -> Platform.OS.Unknown(arch, version)
  }
}
