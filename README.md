


# Multiplatform Paths

[![Maven Central](https://img.shields.io/maven-central/v/me.sujanpoudel.mputils/paths?label=version&color=blue)](https://search.maven.org/search?q=me.sujanpoudel.mputils)
[![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/me.sujanpoudel.mputils/paths?label=snapshot&server=https%3A%2F%2Fs01.oss.sonatype.org)](https://s01.oss.sonatype.org/content/repositories/snapshots/me/sujanpoudel/mputils/)
[![CI](https://github.com/psuzn/mp-utils/actions/workflows/CI.yaml/badge.svg)](https://github.com/psuzn/mp-utils/actions/workflows/CI.yaml)

[![kotlin](https://img.shields.io/badge/kotlin-1.9.21-blue?logo=kotlin)](http://kotlinlang.org)
[![compose-multiplatform](https://img.shields.io/badge/Compose_Multiplatform-1.5.11-blue?logo=jetpackcompose)](https://github.com/JetBrains/compose-jb)

![license](https://img.shields.io/github/license/psuzn/mp-utils?label=License)

Platform-specific application home and cache directories for KMP

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
  implementation("me.sujanpoudel.mputils:paths:0.1.1")
}
```

### Usage

#### App data directory

```kotlin
import me.sujanpoudel.mputils.paths.appDataDirectory

val packageName = "example.com.app"

val dataDirectory = appDataDirectory(packageName)
```

This will return `ApplicationInfo.dataDir` on android, `NSHomeDirectory` on IOS and equivalent platform specific data
directory on other platforms.

#### App cache directory

```kotlin
import me.sujanpoudel.mputils.paths.appCacheDirectory

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
  implementation("me.sujanpoudel.mputils:platform-identifier:0.1.1")
}
```

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
  implementation("me.sujanpoudel.mputils:context-provider:0.1.1")
}
```

### Usage

#### Get Android Context

```kotlin
import me.sujanpoudel.mputils.contextProvider.applicationContext

val context = applicationContext

```

### Snapshots

Snapshots of the development version are available in Sonatype's
snapshots [repository](https://s01.oss.sonatype.org/content/repositories/snapshots/me/sujanpoudel/mputils/).

```kotlin
repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
  implementation("me.sujanpoudel.mputils:paths:0.1.1-2-SNAPSHOT")
  implementation("me.sujanpoudel.mputils:platform-identifier:0.1.1-2-SNAPSHOT")
  implementation("me.sujanpoudel.mputils:context-provider:0.1.1-2-SNAPSHOT")
}
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
