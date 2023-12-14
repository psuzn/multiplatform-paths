package me.sujanpoudel.mputils.platformIdentifier

import kotlin.test.Test
import kotlin.test.assertIs

class PlatformTest {

  @Test
  fun should_have_correct_platform() {
    val platform = platform()
    assertIs<Platform.OS.Android>(platform)
  }
}
