package me.sujanpoudel.mputils.platformIdentifier

import kotlin.test.assertIs

actual fun assertPlatform(platform: Platform) {
  assertIs<Platform.OS.Windows>(platform)
}
