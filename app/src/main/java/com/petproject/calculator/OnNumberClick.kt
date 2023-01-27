package com.petproject.calculator

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color


fun onNumberClick(buttonText : String, shownTextM: MutableState<String>, answTextM: MutableState<String>, alphaM: MutableState<String>, lockState: MutableState<String>, colorState: MutableState<Color>) {
    opSwap = false
    dot.isClickable = true
    Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | Numbers is pressed  ")
    dbzFlag = false
    ansFlag = false
    if (buttonText == ".") { //Проверка и запрет повторного ввода точки в один операнд
        if (!commaFlag) {
            task += "."
            shownText += "."
            shownTextM.value = shownText
            commaFlag = true
            dot.isClickable = false
        }
        else {
            shownTextM.value = shownText
        }
    }
    else {
        task += buttonText //Вывод числа на экран
        shownText += buttonText
        shownTextM.value = shownText
        Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag |  Number in ")
    }
    leftFlag = true
    if (opFlag) rightFlag = true
    if (leftFlag and rightFlag and opFlag and !dbzFlag) {
        Log.i(TAG,"t: $task | a: $answer |  fl: $leftFlag,$rightFlag,$opFlag,$ansFlag | Answer out  ")
        try { answer = strOperands(task)
            if (!dbzFlag) answTextM.value = answer
        }
        catch (e: IllegalArgumentException) {
            Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | IllegalArgumentException ")
            lock(false,"0.3F", alphaM, lockState, colorState)
            answTextM.value = "Ошибка ввода.."
        }
    }
    Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag |  End of Number Fun ")

}