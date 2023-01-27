package com.petproject.calculator

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.petproject.calculator.ui.theme.textColor


fun onOperationClick(buttonText : String, shownTextM: MutableState<String>, answTextM: MutableState<String>, alphaM: MutableState<String>, lockState: MutableState<String>, colorState: MutableState<Color>) {
    Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag |  Operator is pressed ")
    if (buttonText == "DEL") {
        Log.i(TAG,"task: $task | answer: $answer |  prT: $prTask | shwn: $shownText| DEL is present")
        opSwap = true
        minus.isClickable = true
        Log.i(TAG,"task: $task | answer: $answer |  prT: $prTask | shwn: $shownText| 1")
        task = removeLast(task)
        Log.i(TAG,"task: $task | answer: $answer |  prT: $prTask | shwn: $shownText| 2")
        shownText = task.replace("\\s".toRegex(), "")
        Log.i(TAG,"task: $task | answer: $answer |  prT: $prTask | shwn: $shownText| 3")
        shownTextM.value = shownText
        Log.i(TAG,"task: $task | answer: $answer |  prT: $prTask | shwn: $shownText| 4")
        commaFlag = false
        dot.isClickable = true
        answTextM.value = ""
        if (leftFlag and rightFlag and opFlag and !dbzFlag) {
            try { answer = strOperands(task)
                if (!dbzFlag) answTextM.value = answer
            }
            catch (e: IllegalArgumentException) {
                Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | IllegalArgumentException ")
                lock(false,"0.3F", alphaM, lockState, colorState)
                answTextM.value = "Ошибка ввода.."
            }
        }
        opSwap = false
    } else {
        if (answTextM.value.isNotEmpty()) {
            when (ansFlag) {
                false -> { //Знак - оператор, можно считать
                    if (leftFlag and rightFlag and opFlag and !dbzFlag) {
                        try { answer = strOperands(task)
                            if (!dbzFlag) answTextM.value = answer
                            rightFlag = false}
                        catch (e: IllegalArgumentException) {
                            Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | IllegalArgumentException ")
                            lock(false,"0.3F", alphaM, lockState, colorState)
                            answTextM.value = "Ошибка ввода"
                        }
                    }
                    prTask = task
                    task = answer
                    leftFlag = true
                    rightFlag = false
                    opFlag = false
                    ansFlag = true
                    Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag |   Answer field isNotEmpty")
                }
                true -> { //Знак - знак перед числом, ждем ввод еще одного числа
                    leftFlag = true
                    rightFlag = false
                }
            }
        }
        if (buttonText == "=") {
            if (answer == "0.0") {
                clearTask(shownTextM, answTextM)
            }
            else {
                shownTextM.value = answTextM.value
                shownText = answer
                task = answer
                answTextM.value = ""
                leftFlag = true
                answer = ""
            }
            rightFlag = false
            opFlag = false
            commaFlag = false
            opSwap = false
            dot.isClickable = true
            if (dbzFlag) {
                lock(false,"0.3F", alphaM, lockState, colorState)
                answTextM.value = "На 0 делить нельзя"
                shownTextM.value = prTask
            }
            Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | Equals is in  ")
        }
        else {
            if (buttonText == "AC") {
                lock(true,"1F", alphaM, lockState, colorState, color = textColor)
                clearTask(shownTextM, answTextM)
            } else {
                when (buttonText) {

                    "/" -> {
                        if (opSwap) {
                            Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | DEL is present")
                            task = removeLast(task)
                            shownText = task.replace("\\s".toRegex(), "")
                            shownTextM.value = shownText
                            commaFlag = false
                            dot.isClickable = true
                            answTextM.value = ""
                            task += opOrSign("/", leftFlag, opFlag, ansFlag)
                            shownText += "/"
                            shownTextM.value = shownText
                            if (leftFlag and rightFlag and opFlag and !dbzFlag) {
                               try { answer = strOperands(task)
                                }
                                catch (e: IllegalArgumentException) {
                                    Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | IllegalArgumentException ")
                                    lock(false,"0.3F", alphaM, lockState, colorState)
                                    answTextM.value = "Ошибка ввода.."
                                }
                                if (!dbzFlag) answTextM.value = answer
                            }
                        }
                        else {
                            task += opOrSign("/", leftFlag, opFlag, ansFlag)
                            shownText += "/"
                            shownTextM.value = shownText
                            opSwap = true
                        }

                    }
                    "x" -> {
                        if (opSwap) {
                            Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | DEL is present")
                            task = removeLast(task)
                            shownText = task.replace("\\s".toRegex(), "")
                            shownTextM.value = shownText
                            commaFlag = false
                            dot.isClickable = true
                            answTextM.value = ""
                            task += opOrSign("x", leftFlag, opFlag, ansFlag)
                            shownText += "x"
                            shownTextM.value = shownText
                            if (leftFlag and rightFlag and opFlag and !dbzFlag) {
                                try { answer = strOperands(task)
                                    if (!dbzFlag) answTextM.value = answer
                                }
                                catch (e: IllegalArgumentException) {
                                    Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | IllegalArgumentException ")
                                    lock(false,"0.3F", alphaM, lockState, colorState)
                                    answTextM.value = "Ошибка ввода.."
                                }
                            }
                        }
                        else {
                            task += opOrSign("x", leftFlag, opFlag, ansFlag)
                            shownText += "x"
                            shownTextM.value = shownText
                            opSwap = true
                        }


                    }
                    "+" -> {
                        if (opSwap) {
                            Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | DEL is present")
                            task = removeLast(task)
                            shownText = task.replace("\\s".toRegex(), "")
                            shownTextM.value = shownText
                            commaFlag = false
                            dot.isClickable = true
                            task += opOrSign("+", leftFlag, opFlag, ansFlag)
                            shownText += "+"
                            shownTextM.value = shownText


                        }
                        else {
                            task += opOrSign("+", leftFlag, opFlag, ansFlag)
                            shownText += "+"
                            shownTextM.value = shownText
                            opSwap = true
                        }

                    }
                    "-" -> {
                        task += opOrSign("-", leftFlag, opFlag, ansFlag)
                        shownText += "-"
                        shownTextM.value = shownText

                    }
                    "√" -> {
                        task += " √ "
                        shownText += "√"
                        shownTextM.value = shownText
                        opFlag = true
                        leftFlag = true
                        opSwap = true



                    }
                    "%" -> {
                        task += " % "
                        shownText += "%"
                        shownTextM.value = shownText
                        opSwap = true
                        Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | % in  ")
                        if (!ansFlag) { //Считаем процент 50% = 0.5
                            Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | Naked percent  ")
                            opFlag = true
                            rightFlag = true
                            if (leftFlag and rightFlag and opFlag and !dbzFlag) {
                                try { answer = strOperands(task)
                                    if (!dbzFlag) answTextM.value = answer
                                }
                                catch (e: IllegalArgumentException) {
                                    Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | IllegalArgumentException ")
                                    lock(false,"0.3F", alphaM, lockState, colorState)
                                    answTextM.value = "Ошибка ввода.."
                                }
                            }
                            opSwap = false
                        }
                        else { //Считаем процент от какого-то числа Х + n%
                            opFlag = true
                            rightFlag = true
                            Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | Percent from N in  ")
                            if (leftFlag and rightFlag and opFlag and !dbzFlag) {
                                try { answer = strOperands(task,prTask)
                                    if (!dbzFlag) answTextM.value = answer
                                    task = answer
                                    opFlag = false}
                                catch (e: IllegalArgumentException) {
                                    Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | IllegalArgumentException ")
                                    lock(false,"0.3F", alphaM, lockState, colorState)
                                    shownTextM.value = "Ошибка ввода"
                                }
                                Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | Percent from N answer  ")
                            }
                            opSwap = false
                        }

                    }
                }
                Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | End of Operator Fun  ")
            }
        }
    }

}
