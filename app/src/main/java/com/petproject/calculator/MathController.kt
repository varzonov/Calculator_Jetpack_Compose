package com.petproject.calculator

import android.content.ContentValues.TAG
import android.util.Log
import kotlin.math.round


fun strOperands(strTask: String, previousTask : String = "" ): String {
    Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | strOperands fun in  ")
    val leftOp: String = strTask.substringBefore(" ")
    val rightOp: String = strTask.substringAfterLast(" ")
    var oper: String = strTask.substringBeforeLast(" ")
    oper = oper.substringAfter(" ") // обгрызли строку на операнды и оператор
    Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | $leftOp|$rightOp|$oper strOperands fun out  ")
    return solution(leftOp, rightOp, oper, previousTask).toString()
}

private fun solution(left: String, right: String, op: String, str : String): Double {
    Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | solution fun in  ")
    var op1 = ""
    var op2: String
    var x: Double = when (left) {
        "" -> 0.0
        else -> left.toDouble()
    }
    var y: Double = when (right) {
        "" -> 0.0
        "." -> 0.0
        "-." -> -0.0
        "+." -> 0.0
        else -> right.toDouble()
    }
    if (str.isNotEmpty()) {
        x = str.substringBefore(" ").toDouble()
        op2 = str.substringAfter(" ")
        op2 = op2.substringBefore(" ")
        y = str.substringAfterLast(" ").toDouble()
        Log.i(TAG,"task: $task | answer: $answer | op2$op2| op1$op1| x $x | y $y || |$str| | str isNotEmpty ")
        return math(op2, x, math("x", x, math("%", y, y))) //Где-то косячит возврат
    }
    when (true)
    {
        (op.contains("√")) ->
        { if (op.length > 1) {
            op2 = op.substringAfterLast(" ") //+
            op1 = op.substringBefore(" ")// sqrt x = 2, y = 4
            return math(op1, x, math(op2, x, y))
        }
        }
        else -> {}
    }
    Log.i(TAG,"task: $task | answer: $answer |  f$op|$x|$y | return of solution fun  ")
    return math(op,x,y)
}

fun opOrSign (str: String, fl: Boolean, fo: Boolean, fa: Boolean) : String{
    val newstr: String
    if ((!fl) or ((fl) and (fo)) or ((fl) and (fo) and(fa))) {
        newstr = str // число
        rightFlag = false
        commaFlag = false
        dot.isClickable = true
    }
    else {
        newstr = " $str " //оператор
        opFlag = true
        commaFlag = false
        dot.isClickable = true
    }
    return newstr
}

private fun math(operator : String, x :Double, y : Double): Double {
    var result = 0.0
    when (operator) {
        "%" -> result = x * 0.01
        "√" -> result = kotlin.math.sqrt(y)
        "+" -> result = x + y
        "-" -> result = x - y
        "x" -> result = x * y
        "/" -> {
            if (y == 0.0) {
                dbzFlag = true
                //return 0.666
                leftFlag = false

            } else result = x / y
        }
    }
    return result.round(5)
}
private fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

fun removeLast(str: String) :String{
    Log.i(TAG,"task: $task | answer: $answer |  flags:  $leftFlag, $rightFlag,$opFlag,$ansFlag | remove fun is present ")
    val nstr: String
    val leftOp: String = str.substringBefore(" ")
    val rightOp: String = str.substringAfterLast(" ")
    var oper: String
    if (rightOp.isNotEmpty()) {
        if ((leftOp.toDoubleOrNull() == rightOp.toDoubleOrNull()) and (str.length < (leftOp.length + rightOp.length))) nstr =  leftOp
        else
        {
            oper = str.substringBeforeLast(" ")
            oper = oper.substringAfter(" ")
            when (true){
                (rightOp.toDoubleOrNull() == null) -> rightFlag = false
                ((rightOp.toDouble() < 0) and (rightOp.toDouble() >=  -9)) -> rightFlag = false
                (rightOp.length == 1) -> rightFlag = false
                else -> {}
            }
            nstr = "$leftOp $oper $rightOp"
        }
    }
    else
    {
        oper = str.substringAfter(" ")
        oper = oper.substringBefore(" ")
        nstr = leftOp + oper
        opFlag = false
        rightFlag = false
    }
    return nstr.replaceFirst(".$".toRegex(), "")
}

/*
override fun onAnimationStart(p0: Animation?) {

}

override fun onAnimationEnd(p0: Animation?) {

}

override fun onAnimationRepeat(p0: Animation?) {

}
*/