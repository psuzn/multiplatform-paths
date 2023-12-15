package me.sujanpoudel.mputils.platformIdentifier

import android.content.Context
import android.net.Uri
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowContentResolver
import kotlin.test.assertIs

@RunWith(RobolectricTestRunner::class)
class PlatformTest {

  @Test
  fun should_have_correct_platform() {
    val context = ApplicationProvider.getApplicationContext<Context>()

    val authority = "${context.packageName}.androidx-startup"

    val uri = Uri.Builder()
      .scheme("content")
      .authority(authority)
      .build()

    ShadowContentResolver.getProvider(uri)

    val platform = platform()

    assertIs<Platform.OS.Android>(platform)
  }
}
