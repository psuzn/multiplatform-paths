package me.sujanpoudel.mputils.platformIdentifier

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.UIntVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import platform.posix._winmajor
import platform.posix._winminor
import platform.windows.DWORD
import platform.windows.GetVersion
import platform.windows.RtlGetProductInfo
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)
actual fun platform(): Platform = Platform.OS.Windows(
  arch = kotlin.native.Platform.cpuArchitecture.asArch(),
  version = "$_winmajor.$_winminor"
)

