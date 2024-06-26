# Changelog

All notable changes to this project will be documented in this file.

## [Unreleased]

### Fixes

### Changes

## [0.2.2] - 2024-05-13

### Fixes

- [BUG] Wrong os type on Windows #36

## [0.2.1] - 2024-04-23

### Fixes

- NSHomeDirectory is only allowed to be written on iOS Simulators, but not on physical devices #33

## [0.2.1] - 2024-04-23

### Fixes

- NSHomeDirectory is only allowed to be written on iOS Simulators, but not on physical devices #33

## [0.2.0] - 2024-04-21

This version contains general house keeping, dependencies updates and ci improvement for better maintainability.

### Breaking Changes

- Group name has been updated to `me.sujanpoudel.multiplatform.utils` from `me.sujanpoudel.mputils`
- Artifact `paths` has been renamed to `multiplatform-paths`.

> [!NOTE]
> Follow [migration guide](https://github.com/psuzn/multiplatform-paths/blob/main/migration-guides.md##v01x-to-v2x) to migrate from v0.1 to v0.2.

## [0.1.1] - 2023-12-28

### Added

- Support for `iosX64` target.

### Changed

- Renamed `applicationCacheDir` to `appCacheDirectory`.
- Exposed `kotlinx-io-core` as a transitive dependency.

## [0.1.0] - 2023-12-21

Initial Cut (Get it?).
