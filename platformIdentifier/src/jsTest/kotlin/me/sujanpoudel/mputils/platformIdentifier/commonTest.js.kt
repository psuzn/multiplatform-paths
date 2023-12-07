package me.sujanpoudel.mputils.platformIdentifier

import kotlin.test.assertIs

actual fun assertPlatform(platform: Platform) {
  if (isNode())
    assertIs<Platform.JS.Node>(platform)
  else if (isBrowser())
    assertIs<Platform.JS.Node>(platform)
}
