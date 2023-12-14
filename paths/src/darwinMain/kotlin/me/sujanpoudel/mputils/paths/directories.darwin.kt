package me.sujanpoudel.mputils.paths

import kotlinx.io.files.Path
import me.sujanpoudel.mputils.paths.utils.div
import me.sujanpoudel.mputils.paths.utils.toPath
import me.sujanpoudel.mputils.platformIdentifier.Platform
import me.sujanpoudel.mputils.platformIdentifier.platform
import platform.Foundation.NSApplicationSupportDirectory
import platform.Foundation.NSCachesDirectory
import platform.Foundation.NSHomeDirectory
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask

actual fun dataDirectory(appId: String): Path = if (platform() is Platform.OS.MacOs) {
  NSSearchPathForDirectoriesInDomains(NSApplicationSupportDirectory, NSUserDomainMask, true)
    .firstOrNull()?.toString()?.toPath()
    ?.let { it / appId } ?: error("Unable to get 'NSApplicationSupportDirectory'")
} else {
  NSHomeDirectory().toPath()
}

actual fun cacheDirectory(appId: String): Path {
  val cachesDirectory = NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, true)
    .firstOrNull()?.toString()?.toPath() ?: error("Unable to get 'NSCachesDirectory'")

  return if (platform() is Platform.OS.MacOs) {
    cachesDirectory / appId
  } else {
    cachesDirectory
  }
}
