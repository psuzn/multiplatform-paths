package me.sujanpoudel.mputils.platformIdentifier

internal fun getNodePlatform(): Platform.JS.Node {
  val osName = eval("require('os').type()") as String
  val archName = eval("require('os').arch()") as String
  val osVersion = eval("require('os').version()") as String

  return Platform.JS.Node(
    os = hostOs(osName = osName, archName = archName, version = osVersion),
    nodeVersion = eval("process.versions.node") as String,
    v8Version = eval("process.versions.v8") as String,
  )
}
