package me.sujanpoudel.mputils.platformIdentifier


val hostOs: Platform.OS = hostOs(
  osName = System.getProperty("os.name"),
  archName = System.getProperty("os.arch"),
  version = System.getProperty("os.version"),
)

actual fun platform(): Platform = hostOs
