package me.sujanpoudel.mputils.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import me.sujanpoudel.mputils.sample.common.SampleMainAndroid

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      SampleMainAndroid()
    }
  }
}
