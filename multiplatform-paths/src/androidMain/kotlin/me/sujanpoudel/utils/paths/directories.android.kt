package me.sujanpoudel.utils.paths

import me.sujanpoudel.utils.contextProvider.applicationContext
import me.sujanpoudel.utils.paths.utils.toPath

actual fun dataDirectory(appId: String) = applicationContext.applicationInfo.dataDir.toPath()

actual fun cacheDirectory(appId: String) = applicationContext.cacheDir.absolutePath.toPath()
