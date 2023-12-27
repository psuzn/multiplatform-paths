package me.sujanpoudel.mputils.sample.common

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
import me.sujanpoudel.mputils.paths.appCacheDirectory
import me.sujanpoudel.mputils.paths.appDataDirectory
import me.sujanpoudel.mputils.platformIdentifier.Platform
import me.sujanpoudel.mputils.platformIdentifier.platform

@Composable
fun MainUI() {
  val appHomeDir = remember {
    if (platform() !is Platform.JS.Browser) appDataDirectory(Constants.appId) else "N/A"
  }

  val cacheDir = remember {
    if (platform() !is Platform.JS.Browser) appCacheDirectory(Constants.appId) else "N/A"
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
