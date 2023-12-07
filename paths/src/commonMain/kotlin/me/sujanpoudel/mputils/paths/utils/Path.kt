package me.sujanpoudel.mputils.paths.utils

import kotlinx.io.files.Path


inline fun String.toPath(): Path = Path(this)

operator fun Path.div(child: String) = Path(this, child)
