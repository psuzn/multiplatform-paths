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

/**
 * CPU Architecture
 */
enum class Arch {
  UNKNOWN,
  X64,
  X86,
  ARM_X64,
  ARM_X32,
}

sealed class Platform {
  /**
   * Running on JS vm environment
   */
  sealed class JS : Platform() {
    data class Node(val os: OS, val nodeVersion: String, val v8Version: String) : JS()

    data class Browser(val userAgent: String) : JS()
  }

  sealed class OS(open val arch: Arch) : Platform() {
    data class Unknown(override val arch: Arch, val version: String) : OS(arch)

    data class MacOs(override val arch: Arch, val version: String) : OS(arch)

    data class IOS(override val arch: Arch, val version: String, val isSimulator: Boolean) : OS(arch)

    data class WatchOs(override val arch: Arch, val version: String, val isSimulator: Boolean) : OS(arch)

    data class TvOs(override val arch: Arch, val version: String, val isSimulator: Boolean) : OS(arch)

    data class Android(
      override val arch: Arch,
      val buildNumber: Int,
      val androidVersion: String,
      val isWatch: Boolean,
      val isTv: Boolean,
    ) : OS(arch)

    data class Linux(override val arch: Arch, val version: String) : OS(arch)

    data class Windows(override val arch: Arch, val version: String) : OS(arch)
  }
}

/**
 * Get current platform
 */
expect fun platform(): Platform
