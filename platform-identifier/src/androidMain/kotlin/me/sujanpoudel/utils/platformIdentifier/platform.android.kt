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

import android.content.pm.PackageManager
import android.os.Build
import me.sujanpoudel.utils.contextProvider.applicationContext

actual fun platform(): Platform {
  return Platform.OS.Android(
    arch = arch(),
    buildNumber = Build.VERSION.SDK_INT,
    androidVersion = Build.VERSION.RELEASE,
    isWatch = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_WATCH),
    isTv = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK_ONLY)
    } else {
      applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK)
    },
  )
}

private fun arch(): Arch {
  Build.SUPPORTED_ABIS.orEmpty().forEach {
    when (it) {
      "arm64-v8a" -> return Arch.ARM_X64
      "armeabi-v7a" -> return Arch.ARM_X64
      "x86_64" -> return Arch.X64
      "x86" -> return Arch.X86
    }
  }
  return Arch.UNKNOWN
}
