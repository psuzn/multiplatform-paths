package me.sujanpoudel.utils.paths

import me.sujanpoudel.utils.platformIdentifier.hostOs

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
