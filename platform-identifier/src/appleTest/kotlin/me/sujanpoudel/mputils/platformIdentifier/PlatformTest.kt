package me.sujanpoudel.mputils.platformIdentifier

import kotlin.experimental.ExperimentalNativeApi
import kotlin.test.Test
import kotlin.test.assertIs

@OptIn(ExperimentalNativeApi::class)
class PlatformTest {

  @Test
  fun should_have_correct_platform() {
    val platform = platform()

    when (kotlin.native.Platform.osFamily) {
      OsFamily.MACOSX -> assertIs<Platform.OS.MacOs>(platform)
      OsFamily.IOS -> assertIs<Platform.OS.IOS>(platform)
      OsFamily.TVOS -> assertIs<Platform.OS.TvOs>(platform)
      OsFamily.WATCHOS -> assertIs<Platform.OS.WatchOs>(platform)
      else -> error("invalid platform")
    }
  }
}
