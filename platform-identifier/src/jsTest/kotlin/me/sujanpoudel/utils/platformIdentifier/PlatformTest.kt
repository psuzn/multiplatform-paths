package me.sujanpoudel.utils.platformIdentifier

import kotlin.test.Test
import kotlin.test.assertIs

class PlatformTest {
  @Test
  fun should_have_correct_platform() {
    val platform = platform()

    if (isNode()) {
      assertIs<Platform.JS.Node>(platform)
    } else if (isBrowser()) {
      assertIs<Platform.JS.Node>(platform)
    }
  }
}
