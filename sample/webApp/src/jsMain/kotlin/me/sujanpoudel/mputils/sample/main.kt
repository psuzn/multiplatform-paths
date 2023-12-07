package me.sujanpoudel.mputils.sample

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import me.sujanpoudel.mputils.sample.common.MainUI
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() = onWasmReady {
  CanvasBasedWindow("title", canvasElementId = "root") {
    MainUI()
  }
}

