/*
 * Copyright 2024 Sujan Poudel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.sujanpoudel.utils.platformIdentifier

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
