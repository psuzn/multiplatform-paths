package me.sujanpoudel.mputils.platformIdentifier

import android.content.pm.PackageManager
import android.os.Build
import me.sujanpoudel.mputils.contextProvider.applicationContext


actual fun platform(): Platform {

  return Platform.OS.Android(
    arch = arch(),
    buildNumber = Build.VERSION.SDK_INT,
    androidVersion = Build.VERSION.RELEASE,
    isWatch = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_WATCH),
    isTv = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
      applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK_ONLY)
    else applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK)
  )

}


private fun arch(): Arch {
  Build.SUPPORTED_ABIS.orEmpty().forEach {
    when (it) {
      "arm64-v8a" -> return Arch.ARM_X64
      "armeabi-v7a" -> return Arch.ARM_X64
      "x86_64" -> return Arch.X64
      "x86" -> return Arch.X86
    }
  }
  return Arch.UNKNOWN
}
