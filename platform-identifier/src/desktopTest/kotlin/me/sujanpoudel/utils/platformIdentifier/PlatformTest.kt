package me.sujanpoudel.utils.platformIdentifier

import org.junit.Test
import kotlin.test.assertTrue

class PlatformTest {
  @Test
  fun should_have_correct_platform() {
    val platform = platform()

    val osName = System.getProperty("os.name").lowercase()

    when {
      osName.startsWith("mac") ||
        osName.startsWith("osx") ||
        osName.startsWith("darwin") -> assertTrue { platform is Platform.OS.MacOs }

      osName.startsWith("win") -> assertTrue { platform is Platform.OS.Windows }
      osName.startsWith("linux") -> assertTrue { platform is Platform.OS.Linux }
      else -> assertTrue { platform is Platform.OS.Unknown }
    }
  }
}
