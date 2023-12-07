import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import me.sujanpoudel.mputils.sample.common.MainDesktopView

fun main() = singleWindowApplication(
  title = "Sample",
  state = WindowState(
    size = DpSize(400.dp, 800.dp),
  ),
  resizable = false,
) {
  MainDesktopView()
}
