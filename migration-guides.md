## Migration guide

### v0.1.x to v.2.x

- Update the implementation

  ```kotlin

  implementation("me.sujanpoudel.mputils:paths:0.1.x")
  implementation("me.sujanpoudel.mputils:platform-identifier:0.1.x")
  implementation("me.sujanpoudel.mputils:context-provider:0.1.x")
  ```

  with following:

  ```kotlin

  implementation("me.sujanpoudel.multiplatform.utils:multiplatform-paths:0.2.0")
  implementation("me.sujanpoudel.multiplatform.utils:platform-identifier:0.2.0")
  implementation("me.sujanpoudel.multiplatform.utils:context-provider:0.2.0")
  ```

- Update the any import with `me.sujanpoudel.mputils.*` with `me.sujanpoudel.util.*`

  ```kotlin
  import me.sujanpoudel.mputils.paths.appDataDirectory // old import
  import me.sujanpoudel.utils.paths.appDataDirectory // replace with this


  import me.sujanpoudel.mputils.paths.appCacheDirectory // old import
  import me.sujanpoudel.utils.paths.appCacheDirectory // replace with this

  import me.sujanpoudel.mputils.platformIdentifier.platform // old import
  import me.sujanpoudel.multiplatform.utils.platformIdentifier.platform // replace with this


  import me.sujanpoudel.mputils.contextProvider.applicationContext // old import
  import me.sujanpoudel.utils.contextProvider.applicationContext // replace with this

  ```
