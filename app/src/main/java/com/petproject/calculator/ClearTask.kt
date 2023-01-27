package com.petproject.calculator

import androidx.compose.runtime.MutableState

fun clearTask(shownTextM: MutableState<String>, answTextM: MutableState<String>){
    task = ""
    answer = ""
    shownText = ""
    answTextM.value = ""
    rightFlag = false
    leftFlag = false
    opFlag = false
    commaFlag = false
    ansFlag = false
    dot.isClickable = true
    shownTextM.value = task
}
