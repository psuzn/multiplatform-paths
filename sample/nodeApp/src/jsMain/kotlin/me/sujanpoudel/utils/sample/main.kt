package me.sujanpoudel.utils.sample

import me.sujanpoudel.utils.paths.appCacheDirectory
import me.sujanpoudel.utils.paths.appDataDirectory
import me.sujanpoudel.utils.platformIdentifier.platform

const val APP_ID: String = "me.sujanpoudel.multiplatform.utils.sample"

fun main() {
  println(
    """
     _   _           _                 _           _____                       _
    | \ | |         | |               | |         / ____|                     | |
    |  \| | ___   __| | ___           | |___     | (___   __ _ _ __ ___  _ __ | | ___
    | . ` |/ _ \ / _` |/ _ \      _   | / __|     \___ \ / _` | '_ ` _ \| '_ \| |/ _ \
    | |\  | (_) | (_| |  __/     | |__| \__ \     ____) | (_| | | | | | | |_) | |  __/
    |_| \_|\___/ \__,_|\___|      \____/|___/    |_____/ \__,_|_| |_| |_| .__/|_|\___|
                                                                  | |
                                                                  |_|

    """.trimIndent(),
  )

  println("platform is: ${platform()}")
  println("Paths")
  println("Application directory: ${appDataDirectory(APP_ID)}")
  println("Cache directory: ${appCacheDirectory(APP_ID)} \n\n")
}
