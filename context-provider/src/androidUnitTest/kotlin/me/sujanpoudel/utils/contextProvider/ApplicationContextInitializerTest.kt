package me.sujanpoudel.utils.contextProvider

import android.app.Application
import android.content.Context
import android.net.Uri
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowContentResolver

@RunWith(RobolectricTestRunner::class)
class ApplicationContextInitializerTest {
  @Test
  fun `should initialize application context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()

    val authority = "${context.packageName}.androidx-startup"

    val uri = Uri.Builder()
      .scheme("content")
      .authority(authority)
      .build()

    ShadowContentResolver.getProvider(uri)

    assertEquals(applicationContext, context)
    assertTrue(applicationContext is Application)
  }
}
