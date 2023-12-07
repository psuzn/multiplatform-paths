package me.sujanpoudel.mputils.sample

import me.sujanpoudel.mputils.paths.applicationCacheDirectory
import me.sujanpoudel.mputils.paths.applicationHomeDirectory
import me.sujanpoudel.mputils.platformIdentifier.platform

const val appId = "me.sujanpoudel.mpUtils.sample"

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

  """.trimIndent()
  )


  println("platform is: ${platform()}")
  println("Paths")
  println("Application directory: ${applicationHomeDirectory(appId)}")
  println("Cache directory: ${applicationCacheDirectory(appId)} \n\n")
}

