package com.petproject.calculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp


@Composable
fun numpad (scrSize: Int, shownTextM: MutableState<String>, answTextM: MutableState<String>, alphaM: MutableState<String>,lockState : MutableState<String>, colorState: MutableState<Color>){
    BoxWithConstraints (
        androidx.compose.ui.Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ){

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight(0.97f)
                .padding(bottom = 0.dp)
        ){
            ButtonRow(AC,sqrt,div,proc,scrSize, shownTextM, answTextM, alphaM, lockState, colorState)
            ButtonRow(seven, eight, nine,mult,scrSize, shownTextM, answTextM, alphaM, lockState, colorState)
            ButtonRow(four,five,six,plus,scrSize, shownTextM, answTextM, alphaM, lockState, colorState)
            ButtonRow(one,two,three,minus,scrSize, shownTextM, answTextM, alphaM, lockState, colorState)
            ButtonRow(zero,dot,del,eqv,scrSize, shownTextM, answTextM, alphaM, lockState, colorState)
        }
    }
}



@Composable
fun ButtonRow (btn1: ButSign,btn2: ButSign,btn3: ButSign,btn4: ButSign,scrSize : Int, shownText: MutableState<String>, answTextM: MutableState<String>, alphaM: MutableState<String>,lockState : MutableState<String>, colorState: MutableState<Color>){
    Row (
        modifier = Modifier
            .fillMaxWidth(0.90f)
            .padding(top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Button(btn1,scrSize, shownText, answTextM, alphaM, lockState, colorState)
        Button(btn2,scrSize, shownText, answTextM, alphaM, lockState, colorState)
        Button(btn3,scrSize, shownText, answTextM, alphaM, lockState, colorState)
        Button(btn4,scrSize, shownText, answTextM, alphaM, lockState, colorState)

    }

}

@Composable
fun Button (butImage: ButSign, scrSize: Int, shownTextM: MutableState<String>, answTextM: MutableState<String>, alphaM: MutableState<String>, lockState: MutableState<String>, colorState: MutableState<Color>){
    BoxWithConstraints (
        modifier = Modifier
            .clip(shape = CircleShape),
        contentAlignment = Alignment.Center,
    ){
        if (butImage.sign != "AC") {
            Image(ImageBitmap.imageResource(id = butImage.label),
                contentDescription = butImage.desc,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .clickable(enabled = lockState.value.toBoolean()) {
                        when (butImage.sign) {
                            in "0".."9" -> onNumberClick(buttonText = butImage.sign, shownTextM, answTextM, alphaM, lockState, colorState)
                            "."         -> onNumberClick(buttonText = butImage.sign, shownTextM, answTextM, alphaM, lockState, colorState)
                            else        -> {onOperationClick(buttonText = butImage.sign, shownTextM, answTextM, alphaM, lockState, colorState)}
                        }
                    }
                    .clip(shape = CircleShape)
                    .size(scrSize.dp)
                    .alpha(alphaM.value.toFloat())
            )
        }
        else {
            Image(ImageBitmap.imageResource(id = butImage.label),
                contentDescription = butImage.desc,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .clickable(enabled = true) {
                        when (butImage.sign) {
                            in "0".."9" -> onNumberClick(buttonText = butImage.sign, shownTextM, answTextM, alphaM, lockState, colorState)
                            "."         -> onNumberClick(buttonText = butImage.sign, shownTextM, answTextM, alphaM, lockState, colorState)
                            else        -> {onOperationClick(buttonText = butImage.sign, shownTextM, answTextM, alphaM, lockState, colorState)}
                        }
                    }
                    .clip(shape = CircleShape)
                    .size(scrSize.dp)
                    .alpha(1f)
            )
        }

    }
}