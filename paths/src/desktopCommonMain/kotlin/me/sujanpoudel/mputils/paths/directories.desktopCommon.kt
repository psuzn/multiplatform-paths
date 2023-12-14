package me.sujanpoudel.mputils.paths

import me.sujanpoudel.mputils.paths.utils.div
import me.sujanpoudel.mputils.paths.utils.toPath
import me.sujanpoudel.mputils.platformIdentifier.Platform

fun desktopAppHomeDirectory(
  appId: String,
  os: Platform.OS,
  getEnv: (String) -> String,
) = when (os) {
  is Platform.OS.MacOs -> getEnv("HOME").toPath() / "Library/Application Support" / appId
  is Platform.OS.Windows -> getEnv("APPDATA").toPath() / appId
  is Platform.OS.Linux -> getEnv("HOME").toPath() / ".local" / "share" / appId
  else -> getEnv("HOME").toPath() / ".$appId"
}

fun desktopCacheDirectory(
  appId: String,
  os: Platform.OS,
  getEnv: (String) -> String,
) = when (os) {
  is Platform.OS.MacOs -> getEnv("HOME").toPath() / "Library/Caches" / appId
  is Platform.OS.Windows -> getEnv("APPDATA").toPath() / "Caches" / appId
  is Platform.OS.Linux -> getEnv("HOME").toPath() / ".cache" / appId
  else -> getEnv("HOME").toPath() / ".cache" / appId
}
