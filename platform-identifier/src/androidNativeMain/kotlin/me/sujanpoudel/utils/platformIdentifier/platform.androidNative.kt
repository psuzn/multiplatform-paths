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

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.__system_property_get
import kotlin.experimental.ExperimentalNativeApi

private const val SYSTEM_PROP_BUILD_VERSION: String = "ro.build.version.sdk"
private const val SYSTEM_PROP_OS_VERSION: String = "ro.build.version.release"

@OptIn(ExperimentalNativeApi::class)
actual fun platform(): Platform {
  val nativePlatform = kotlin.native.Platform

  val arch = nativePlatform.cpuArchitecture.asArch()

  val buildSdk = getSystemProp(SYSTEM_PROP_BUILD_VERSION).toInt()
  val androidVersion = getSystemProp(SYSTEM_PROP_OS_VERSION)

  return Platform.OS.Android(
    arch = arch,
    buildNumber = buildSdk,
    androidVersion = androidVersion,
    isWatch = false,
    isTv = false,
  )
}

@OptIn(ExperimentalForeignApi::class)
private fun getSystemProp(prop: String): String = memScoped {
  val value = allocArray<ByteVar>(1024L)
  __system_property_get(prop, value)
  value
}.toKString()
