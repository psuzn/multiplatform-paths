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

package me.sujanpoudel.utils.sample.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.sujanpoudel.utils.paths.appCacheDirectory
import me.sujanpoudel.utils.paths.appDataDirectory
import me.sujanpoudel.utils.platformIdentifier.Platform
import me.sujanpoudel.utils.platformIdentifier.platform

@Composable
fun MainUI() {
  val appHomeDir = remember {
    if (platform() !is Platform.JS.Browser) {
      appDataDirectory(Constants.APP_ID)
    } else {
      "N/A"
    }
  }

  val cacheDir = remember {
    if (platform() !is Platform.JS.Browser) {
      appCacheDirectory(Constants.APP_ID)
    } else {
      "N/A"
    }
  }

  val scrollState = rememberScrollState()

  SampleTheme {
    Scaffold {
      Column(
        modifier = Modifier
          .verticalScroll(scrollState)
          .padding(it)
          .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Text("Platform:", style = MaterialTheme.typography.h6)
        Text(platform().toString(), style = MaterialTheme.typography.caption)

        Text("App Directories:", style = MaterialTheme.typography.h6)
        Column(
          modifier = Modifier.padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          Text("App Home: $appHomeDir", style = MaterialTheme.typography.caption)
          Text("App Cache: $cacheDir", style = MaterialTheme.typography.caption)
        }
      }
    }
  }
}
