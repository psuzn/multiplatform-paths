package me.sujanpoudel.utils.platformIdentifier

fun getBrowserPlatform(): Platform.JS.Browser = Platform.JS.Browser(
  eval("navigator.userAgent") as String,
)
