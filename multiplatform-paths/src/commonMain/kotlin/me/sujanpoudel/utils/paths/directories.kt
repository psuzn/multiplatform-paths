/*
 * Copyright 2024 Sujan Poudel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.sujanpoudel.utils.paths

import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

internal expect fun dataDirectory(appId: String): Path

internal expect fun cacheDirectory(appId: String): Path

/**
 *  @returns Platform specific app data directory, on Android and IOS it is a secure directory
 * inaccessible from user and other application.
 *
 * @param appId unique app id, equivalent to packageName(android), bundle id (ios)
 * @param createDir whether explicitly should create the directory if it doesn't exist
 */
fun appDataDirectory(appId: String, createDir: Boolean = true): Path = dataDirectory(appId).also {
  if (createDir) {
    SystemFileSystem.createDirectories(it)
  }
}

/**
 * App specific cache directory
 * on android it is context.cacheDir and on IOS/darwin it is NSCachesDirectory
 *
 * @param appId unique app id, equivalent to packageName(android), bundle id (ios)
 * @param createDir whether explicitly should create the directory if it doesn't exist
 */
fun appCacheDirectory(appId: String, createDir: Boolean = true): Path = cacheDirectory(appId).also {
  if (createDir) {
    SystemFileSystem.createDirectories(it)
  }
}
