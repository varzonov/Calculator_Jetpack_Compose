package com.petproject.calculator


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.petproject.calculator.*
import com.petproject.calculator.ui.theme.calcBackground
import com.petproject.calculator.ui.theme.textColor

var task:String = ""
var prTask: String = ""
var answer: String = ""
var shownText: String = ""
var opSwap: Boolean = false
var opFlag: Boolean = false
var rightFlag: Boolean = false
var leftFlag: Boolean = false
var dbzFlag: Boolean = false
var commaFlag: Boolean = false
var ansFlag : Boolean = false


class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContent {
            val shownTextM = mutableStateOf("")
            val answTextM = mutableStateOf("")
            val alphaM = mutableStateOf("1f")
            val lockState =  mutableStateOf("true")
            val colorState =  mutableStateOf(textColor)
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .background(calcBackground)
            ) {
                val scrSize: Double
                val maxW = maxWidth.value
                scrSize = ((maxW * 0.9) - (3 * 5.5)) / 4
                Column {
                    textField(shownTextM,answTextM,maxW,colorState)
                    numpad(scrSize.toInt(),shownTextM,answTextM,alphaM,lockState,colorState)
                }
            }
        }

    }
}
