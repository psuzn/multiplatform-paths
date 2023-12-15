# MP Utils

MP Utils is a collection of a few utility libraries for developing multiplatform apps using Kotlin Multiplatform.

## Libraries

- `me.sujanpoudel.mpUtils:paths` : Get app data and cache directory
- `me.sujanpoudel.mpUtils:platform-identifier` : Identify the current platform
- `me.sujanpoudel.mpUtils:context-provider` : Get android context anywhere on your android source-set.

## Table of content

- [Paths](#Paths)
  - [Setup](#setup)
  - [Usage](#usage)
    - [Getting App data directory](#getting-app-data-directory)
    - [Getting App cache directory](#getting-app-cache-directory)
- [Platform Identifier](#platform-identifier)
  - [Setup](#setup-1)
  - [Usage](#usage-1)
    - [Get current running platform info](#get-current-running-platform-info)
    - [Possible return values](#possible-return-values)
- [Context Provider](#Paths)
  - [Setup](#setup-2)
  - [Usage](#usage-2)
    - [Get Android Context]()
- [Contributions](#contributions)
- [License](#license)

## Paths

### Setup

> Coming soon!!

### Usage

#### Getting app data directory

```kotlin
import me.sujanpoudel.mputils.paths.appDataDirectory

val packageName = "example.com.app"

val dataDirectory = appDataDirectory(packageName)
```

This will return `ApplicationInfo.dataDir` on android, `NSHomeDirectory` on IOS and equivalent platform specific data
directory on other platforms

#### Getting App cache directory

```kotlin
import me.sujanpoudel.mputils.paths.applicationCacheDirectory

val packageName = "example.com.app"

val dataDirectory = applicationCacheDirectory(packageName)
```

This will return `Context.cacheDir` on android, `NSCachesDirectory` on IOS and equivalent platform specific caches
directory on other platforms

## Platform Identifier

### Setup

> Coming soon!!

### Usage

#### Get current running platform info

```kotlin
import me.sujanpoudel.mputils.platformIdentifier.platform

val currentPlatform = platform()

```

#### Possible return values

It returns `Platform` sealed class:

```kotlin
sealed class Platform {
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
      val isTv: Boolean
    ) : OS(arch)

    data class Linux(override val arch: Arch, val version: String) : OS(arch)
    data class Windows(override val arch: Arch, val version: String) : OS(arch)
  }
}
```

where,`Arch` is an enum representing CPU architecture

```kotlin
enum class Arch {
  UNKNOWN,
  X64,
  X86,
  ARM_X64,
  ARM_X32,
}
```

## Context Provider

### Setup

> Coming soon!!

### Usage

#### Get Android Context

```kotlin
import me.sujanpoudel.mputils.contextProvider.applicationContext

val context = applicationContext

```

### Contributions

Contributions are always welcome!. If you'd like to contribute, please feel free to create a PR or
open an issue.

## License

```
Copyright 2023 Sujan Poudel

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
