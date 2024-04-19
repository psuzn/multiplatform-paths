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

package me.sujanpoudel.utils.platformIdentifier

fun isBrowser(): Boolean =
  js("""typeof window !== "undefined" && typeof window.document !== "undefined;" """) as Boolean

fun isNode(): Boolean =
  js("""typeof process !== "undefined" && process.versions != null && process.versions.node != null;""") as Boolean

actual fun platform(): Platform = when {
  isBrowser() -> getBrowserPlatform()
  isNode() -> getNodePlatform()
  else -> getBrowserPlatform()
}
