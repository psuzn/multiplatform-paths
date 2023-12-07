package me.sujanpoudel.mputils.paths

import kotlinx.io.files.FileSystem
import me.sujanpoudel.mputils.platformIdentifier.hostOs


actual fun homeDirectory(appId: String) = desktopAppHomeDirectory(
  appId = appId,
  os = hostOs,
  getEnv = System::getenv
)


actual fun cacheDirectory(appId: String) = desktopCacheDirectory(
  appId = appId,
  os = hostOs,
  getEnv = System::getenv
)

