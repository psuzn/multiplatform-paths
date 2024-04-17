package me.sujanpoudel.utils.platformIdentifier

import kotlinx.cinterop.ExperimentalForeignApi
import platform.posix._winmajor
import platform.posix._winminor
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)
actual fun platform(): Platform = Platform.OS.Windows(
  arch = kotlin.native.Platform.cpuArchitecture.asArch(),
  version = "$_winmajor.$_winminor",
)
