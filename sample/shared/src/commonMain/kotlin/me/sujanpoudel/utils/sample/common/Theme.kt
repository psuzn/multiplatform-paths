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

package me.sujanpoudel.utils.sample.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
  background = Color.Black,
  onBackground = Color.White,
  primary = SampleGreen,
  primaryVariant = SampleGreenDark,
  onPrimary = Color.White,
  secondary = SampleGreen,
  onSecondary = Color.White,
  error = Red200,
)

private val LightColorPalette = lightColors(
  background = Color.White,
  onBackground = Color.Black,
  primary = SampleGreen,
  primaryVariant = SampleGreenDark,
  onPrimary = Color.White,
  secondary = SampleGreen,
  secondaryVariant = SampleGreenDark,
  onSecondary = Color.White,
  error = Red800,
)

@Composable
fun SampleTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
  val colors =
    if (darkTheme) {
      DarkColorPalette
    } else {
      LightColorPalette
    }

  MaterialTheme(
    colors = colors,
    content = content,
  )
}
