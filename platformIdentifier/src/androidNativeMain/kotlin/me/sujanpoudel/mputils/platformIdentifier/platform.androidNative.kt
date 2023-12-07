package me.sujanpoudel.mputils.platformIdentifier

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.__system_property_get
import kotlin.experimental.ExperimentalNativeApi


const val SYSTEM_PROP_BUILD_VERSION = "ro.build.version.sdk"
const val SYSTEM_PROP_OS_VERSION = "ro.build.version.release"

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
    isTv = false
  )
}


@OptIn(ExperimentalForeignApi::class)
private fun getSystemProp(prop: String): String = memScoped {
  val value = allocArray<ByteVar>(1024L)
  __system_property_get(prop, value)
  value
}.toKString()
