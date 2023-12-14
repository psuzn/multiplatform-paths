package me.sujanpoudel.mputils.paths

import me.sujanpoudel.mputils.contextProvider.applicationContext
import me.sujanpoudel.mputils.paths.utils.toPath

actual fun dataDirectory(appId: String) = applicationContext.applicationInfo.dataDir.toPath()

actual fun cacheDirectory(appId: String) = applicationContext.cacheDir.absolutePath.toPath()
