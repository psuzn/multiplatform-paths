package me.sujanpoudel.mputils.platformIdentifier

import kotlin.test.Test

expect fun assertPlatform(platform: Platform)

class TestPlatform {

  @Test
  fun should_have_correct_platform() {
    assertPlatform(platform())
  }

}
