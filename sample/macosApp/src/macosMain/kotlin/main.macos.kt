import androidx.compose.ui.window.Window
import me.sujanpoudel.utils.sample.common.MainUI
import platform.AppKit.NSApp
import platform.AppKit.NSApplication

fun main() {
  NSApplication.sharedApplication()
  Window("Chat App") {
    MainUI()
  }
  NSApp?.run()
}
