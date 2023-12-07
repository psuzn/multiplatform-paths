package me.sujanpoudel.mputils.platformIdentifier

import kotlin.test.assertTrue

actual fun assertPlatform(platform: Platform) {
  assertTrue { platform is Platform.OS.Android }
}
