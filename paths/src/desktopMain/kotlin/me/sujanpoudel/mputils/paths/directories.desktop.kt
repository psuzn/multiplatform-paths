package me.sujanpoudel.mputils.paths

import me.sujanpoudel.mputils.platformIdentifier.hostOs

actual fun dataDirectory(appId: String) = desktopAppHomeDirectory(
  appId = appId,
  os = hostOs,
  getEnv = System::getenv,
)

actual fun cacheDirectory(appId: String) = desktopCacheDirectory(
  appId = appId,
  os = hostOs,
  getEnv = System::getenv,
)
