package me.sujanpoudel.mputils.platformIdentifier

internal fun hostArch(archName: String) = when (archName) {
  "i486", "i386", "i586", "i686", "x86" -> Arch.X86
  "x86_64", "amd64" -> Arch.X64
  "aarch_64", "arm64", "aarch64" -> Arch.ARM_X64
  "arm32" -> Arch.ARM_X32
  else -> Arch.UNKNOWN
}
