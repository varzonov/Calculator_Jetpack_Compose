package com.petproject.calculator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petproject.calculator.ui.theme.textFields_color

@Composable
fun textField(shownText: MutableState<String>, answText: State<String>, screenWidth: Float, colorState: MutableState<Color>) {
    Card(
        androidx.compose.ui.Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35f)
            .clip(shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
        elevation = 4.dp,
        backgroundColor = textFields_color,
    ) {
        Column (
            horizontalAlignment = Alignment.End,
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)

        ){
            val colorText = colorState.value
            val taskText = shownText.value
            val answerText = answText.value
            val clipboardManager: ClipboardManager = LocalClipboardManager.current
            val text by remember { mutableStateOf("")}
            Text(
                taskText,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                fontSize = AutoTextSize(text = taskText, size = 56, screenWidth).sp,
                color = colorText,
                textAlign = TextAlign.End,
                modifier = androidx.compose.ui.Modifier
                    .padding(end = 25.dp)
                    .fillMaxHeight(0.7f)


            )
            Text(answerText,
                fontSize = AutoTextSize(text = answerText, size = 36, screenWidth).sp,
                color = colorText,
                textAlign = TextAlign.End,
                modifier = androidx.compose.ui.Modifier
                    .padding(end = 25.dp)
                    .fillMaxHeight()
                    .clickable{
                        clipboardManager.setText(AnnotatedString((text)))
                    }
            )

        }
    }
}

@Composable
fun AutoTextSize (text : String, size : Int, screenWidth: Float, mult: Float = 0.6f): Float {
    var autoSize: Float = size.toFloat()
    val delta: Int = text.length - 6
    if (screenWidth >= 390f) {
        when (text.length){
            in 0..9 -> autoSize = size.toFloat()
            in 10..25 -> autoSize = size - (delta * mult)  //29.7
            in 25..35 -> autoSize = 35f
            in 36..83 -> autoSize = 30f
            in 83..129 -> autoSize = 25f
            in 130..160 -> autoSize = 20f
            in 161..300 -> autoSize = 15f
        }
    }
    else {
        when (text.length){
            in 0..6 -> autoSize = size.toFloat()
            in 7..13 -> autoSize = size - (delta * mult)  //29.7
            in 14..25 -> autoSize = 35f
            in 26..36 -> autoSize = 30f
            in 37..83 -> autoSize = 25f
            in 84..129 -> autoSize = 20f
            in 130..300 -> autoSize = 15f
        }
    }

    return autoSize
}