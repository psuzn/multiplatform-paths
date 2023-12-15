package me.sujanpoudel.mputils.platformIdentifier

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
