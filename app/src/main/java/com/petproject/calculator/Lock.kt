package com.petproject.calculator

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.petproject.calculator.ui.theme.errorColor

fun lock(state : Boolean, alpha : String, alphaM: MutableState<String>, lockState: MutableState<String>, colorState: MutableState<Color>, color: Color = errorColor) {
  alphaM.value = alpha
  lockState.value = state.toString()
  colorState.value = color
   }