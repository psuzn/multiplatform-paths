package me.sujanpoudel.utils.platformIdentifier

enum class DesktopOs {
  Macos,
  Linux,
  Windows,
  Unknown,
}

private fun hostOs(name: String) = name.lowercase().let { osName ->
  when {
    osName.startsWith("mac") ||
      osName.startsWith("osx") ||
      osName.startsWith("darwin") -> DesktopOs.Macos

    osName.startsWith("win") -> DesktopOs.Windows
    osName.startsWith("linux") -> DesktopOs.Linux
    else -> DesktopOs.Unknown
  }
}

internal fun hostOs(osName: String, archName: String, version: String): Platform.OS {
  val arch = hostArch(archName)
  return when (hostOs(osName)) {
    DesktopOs.Macos -> Platform.OS.MacOs(arch, version)
    DesktopOs.Linux -> Platform.OS.Linux(arch, version)
    DesktopOs.Windows -> Platform.OS.Linux(arch, version)
    DesktopOs.Unknown -> Platform.OS.Unknown(arch, version)
  }
}
