# Multiplatform Paths

[![Maven Central](https://img.shields.io/maven-central/v/me.sujanpoudel.multiplatform.utils/multiplatform-paths?label=version&color=blue)](https://search.maven.org/search?q=me.sujanpoudel.multiplatform.utils)
[![CI](https://github.com/psuzn/multiplatform-paths/actions/workflows/CI.yaml/badge.svg)](https://github.com/psuzn/mp-utils/actions/workflows/CI.yaml)

[![kotlin](https://img.shields.io/badge/kotlin-1.9.23-blue?logo=kotlin)](http://kotlinlang.org)

![license](https://img.shields.io/github/license/psuzn/multiplatform-paths?label=License)

Platform-specific application home and cache directories for KMP.
<br />

> **Note** <br />
> Version 0.2.x has some breaking changes from v0.1.x. To update to 0.2.x please follow the [migration guide](https://github.com/psuzn/multiplatform-paths/blob/main/migration-guides.md##v01x-to-v2x)
<br />

![badge-JVM](https://img.shields.io/badge/JVM(desktop)-orange)
![badge-Android](https://img.shields.io/badge/Android-dodgerblue?logo=android&logoColor=white)
![badge-iOS](https://img.shields.io/badge/iOS-gray?logo=apple&logoColor=silver)
![badge-macOS](https://img.shields.io/badge/macOS-gray?logo=apple&logoColor=silver)
![badge-Js(Node)](https://img.shields.io/badge/Js(Node)-limegreen?logo=nodedotjs&logoColor=white)

- [Path Mapping](#setup)
- [Setup](#setup)
- [Usage](#usage)
  - [App data directory](#app-data-directory)
  - [App cache directory](#app-cache-directory)

### Path Mapping

| Platform              | Cache Directory                            | Data Directory                             |
|-----------------------|--------------------------------------------|--------------------------------------------|
| Android               | `context.cacheDir`                         | `ApplicationInfo.dataDir`                  |
| IOS/IpadOs/WatchOs    | `NSCachesDirectory`                        | `NSHomeDirectory`                          |
| Mac (native/jvm/node) | `~/Library/Caches/<app-id>`                | `~/Library/Application Support/<app-id>`   |
| Windows (jvm/node)    | `C:\Users\<user>\AppData/Caches/<app-id>`  | `C:\Users\<user>\AppData/<app-id>>`        |
| Linux (jvm/node)      | `~/.cache/<app-id>`                        |  `~/local/share/<app-id>`                  |

### Setup

```kotlin
repositories {
  mavenCentral()
}

dependencies {
  implementation("me.sujanpoudel.multiplatform.utils:multiplatform-paths:0.2.0")
}
```

### Usage

#### App data directory

```kotlin
import me.sujanpoudel.utils.paths.appDataDirectory

val packageName = "example.com.app"

val dataDirectory = appDataDirectory(packageName)
```

This will return `ApplicationInfo.dataDir` on android, `NSHomeDirectory` on IOS and equivalent platform specific data
directory on other platforms.

#### App cache directory

```kotlin
import me.sujanpoudel.utils.paths.appCacheDirectory

val packageName = "example.com.app"

val dataDirectory = appCacheDirectory(packageName)
```

This will return `Context.cacheDir` on android, `NSCachesDirectory` on IOS and equivalent platform specific caches
directory on other platforms


## Other Libraries from this Repository.
- `platform-identifier` : Identify the current platform.
- `context-provider` : Get android context anywhere on your android source set.

## Table of content

- [Platform Identifier](#platform-identifier)
  - [Setup](#setup-1)
  - [Usage](#usage-1)
    - [Get current running platform info](#get-current-running-platform-info)
    - [Possible return values](#possible-return-values)
- [Context Provider](#context-provider)
  - [Setup](#setup-2)
  - [Usage](#usage-2)
    - [Get Android Context]()
- [Snapshots](#snapshots)
- [Contributions](#contributions)
- [License](#license)



## Platform Identifier

![JVM(desktop)](https://img.shields.io/badge/JVM_(desktop)-orange?logo=freedesktopdoporg)
![Android](https://img.shields.io/badge/Android-dodgerblue?logo=android&logoColor=white)
![Android-native](https://img.shields.io/badge/Native-dodgerblue?logo=android&logoColor=white)
![iOS](https://img.shields.io/badge/iOS-gray?logo=apple&logoColor=silver)
![macOS](https://img.shields.io/badge/macOS-gray?logo=apple&logoColor=silver)
![windows](https://img.shields.io/badge/Windows-deepskyblue?logo=windows&logoColor=white)
![Js(Node)](https://img.shields.io/badge/Javascript-lightslategrey?logo=javascript&logoColor=white)

### Setup

```kotlin
repositories {
  mavenCentral()
}

dependencies {
  implementation("me.sujanpoudel.multiplatform.utils:platform-identifier:0.2.0")
}
```

### Usage

#### Get current running platform info

```kotlin
import me.sujanpoudel.multiplatform.utils.platformIdentifier.platform

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
    data class Android(override val arch: Arch, val buildNumber: Int, val androidVersion: String, val isWatch: Boolean, val isTv: Boolean) : OS(arch)

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

![Android](https://img.shields.io/badge/Android-dodgerblue?logo=android&logoColor=white)

### Setup

```kotlin
repositories {
  mavenCentral()
}

dependencies {
  implementation("me.sujanpoudel.multiplatform.utils:context-provider:0.2.0")
}
```

### Usage

#### Get Android Context

```kotlin
import me.sujanpoudel.utils.contextProvider.applicationContext
import android.content.Context

val context: Context = applicationContext

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
