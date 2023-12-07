package me.sujanpoudel.mputils.paths

import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

internal expect fun homeDirectory(appId: String): Path

internal expect fun cacheDirectory(appId: String): Path


fun applicationHomeDirectory(appId: String, createDir: Boolean = true): Path = homeDirectory(appId).also {
  if (createDir) {
    SystemFileSystem.createDirectories(it)
  }
}

fun applicationCacheDirectory(appId: String, createDir: Boolean = true): Path = cacheDirectory(appId).also {
  if (createDir) {
    SystemFileSystem.createDirectories(it)
  }
}
