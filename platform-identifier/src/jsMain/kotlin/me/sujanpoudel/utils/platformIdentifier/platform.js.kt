package me.sujanpoudel.utils.platformIdentifier

fun isBrowser() = js("""typeof window !== "undefined" && typeof window.document !== "undefined;" """) as Boolean

fun isNode() =
  js("""typeof process !== "undefined" && process.versions != null && process.versions.node != null;""") as Boolean

actual fun platform(): Platform = when {
  isBrowser() -> getBrowserPlatform()
  isBrowser() -> getBrowserPlatform()
  isNode() -> getNodePlatform()
  else -> getBrowserPlatform()
}
