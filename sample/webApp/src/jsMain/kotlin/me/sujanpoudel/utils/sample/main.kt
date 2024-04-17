package me.sujanpoudel.utils.sample

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import me.sujanpoudel.utils.sample.common.MainUI
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() = onWasmReady {
  CanvasBasedWindow("title", canvasElementId = "root") {
    MainUI()
  }
}
