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
