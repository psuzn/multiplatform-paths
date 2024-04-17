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

package me.sujanpoudel.utils.contextProvider

import android.content.Context
import androidx.startup.Initializer

/**
 * Application scoped context for current app session
 */
lateinit var applicationContext: Context

internal class ApplicationContextInitializer : Initializer<Context> {
  override fun create(context: Context): Context = context.also {
    applicationContext = it.applicationContext
  }

  override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
