package me.sujanpoudel.mputils.platformIdentifier

import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
fun CpuArchitecture.asArch(): Arch = when (this) {
  CpuArchitecture.UNKNOWN -> Arch.UNKNOWN
  CpuArchitecture.ARM32 -> Arch.ARM_X32
  CpuArchitecture.ARM64 -> Arch.ARM_X64
  CpuArchitecture.X86 -> Arch.X86
  CpuArchitecture.X64 -> Arch.X64
  else -> Arch.UNKNOWN
}
