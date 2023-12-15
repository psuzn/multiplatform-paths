package me.sujanpoudel.mputils.platformIdentifier

/**
 * CPU Architecture
 */
enum class Arch {
  UNKNOWN,
  X64,
  X86,
  ARM_X64,
  ARM_X32,
}

sealed class Platform {

  /**
   * Running on JS vm environment
   */
  sealed class JS : Platform() {
    data class Node(val os: OS, val nodeVersion: String, val v8Version: String) : JS()
    data class Browser(val userAgent: String) : JS()
  }

  sealed class OS(open val arch: Arch) : Platform() {
    data class Unknown(override val arch: Arch, val version: String) : OS(arch)
    data class MacOs(override val arch: Arch, val version: String) : OS(arch)
    data class IOS(override val arch: Arch, val version: String, val isSimulator: Boolean) : OS(arch)
    data class WatchOs(override val arch: Arch, val version: String, val isSimulator: Boolean) : OS(arch)
    data class TvOs(override val arch: Arch, val version: String, val isSimulator: Boolean) : OS(arch)

    data class Android(
      override val arch: Arch,
      val buildNumber: Int,
      val androidVersion: String,
      val isWatch: Boolean,
      val isTv: Boolean,
    ) : OS(arch)

    data class Linux(override val arch: Arch, val version: String) : OS(arch)
    data class Windows(override val arch: Arch, val version: String) : OS(arch)
  }
}

/**
 * Get current platform
 */
expect fun platform(): Platform
