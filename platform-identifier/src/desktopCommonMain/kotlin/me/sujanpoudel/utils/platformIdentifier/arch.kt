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

internal fun hostArch(archName: String) = when (archName) {
  "i486", "i386", "i586", "i686", "x86" -> Arch.X86
  "x86_64", "amd64" -> Arch.X64
  "aarch_64", "arm64", "aarch64" -> Arch.ARM_X64
  "arm32" -> Arch.ARM_X32
  else -> Arch.UNKNOWN
}
