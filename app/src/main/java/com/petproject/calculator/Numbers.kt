package com.petproject.calculator

data class ButSign(
    val id: Int,
    val label: Int,
    val sign: String,
    val desc: String = sign,
    var alpha: String,
    var isClickable: Boolean,
        )
val one: ButSign = ButSign(1, R.drawable.but__5_,"1",alpha = "1f", isClickable = true)
val two: ButSign = ButSign(2, R.drawable.but__6_,"2",alpha = "1f", isClickable = true)
val three: ButSign = ButSign(3, R.drawable.but__7_,"3",alpha = "1f", isClickable = true)
val four: ButSign = ButSign(4, R.drawable.but__8_,"4",alpha = "1f", isClickable = true)
val five: ButSign = ButSign(5, R.drawable.but__9_,"5",alpha = "1f", isClickable = true)
val six: ButSign = ButSign(6,R.drawable.but__10_,"6",alpha = "1f", isClickable = true)
val seven: ButSign = ButSign(7, R.drawable.but__11_,"7",alpha = "1f", isClickable = true)
val eight: ButSign = ButSign(8, R.drawable.but__12_,"8",alpha = "1f", isClickable = true)
val nine: ButSign = ButSign(9, R.drawable.but__13_,"9",alpha = "1f", isClickable = true)
val zero: ButSign = ButSign(10, R.drawable.but__14_,"0",alpha = "1f", isClickable = true)
val dot: ButSign = ButSign(11, R.drawable.but__2_,".",alpha = "1f", isClickable = true)
val plus: ButSign = ButSign(12, R.drawable.but__15_,"+",alpha = "1f", isClickable = true)
val minus: ButSign = ButSign(13, R.drawable.but__16_,"-",alpha = "1f", isClickable = true)
val mult: ButSign = ButSign(14, R.drawable.but__18_,"x",alpha = "1f", isClickable = true)
val div: ButSign = ButSign(15, R.drawable.but__20_,"/",alpha = "1f", isClickable = true)
val proc: ButSign = ButSign(16, R.drawable.but__19_,"%",alpha = "1f", isClickable = true)
val del: ButSign = ButSign(17,R.drawable.but__1_,"DEL",alpha = "1f", isClickable = true)
val AC: ButSign = ButSign(18, R.drawable.but__3_,"AC",alpha = "1f", isClickable = true)
val sqrt: ButSign = ButSign(19, R.drawable.but__4_,"√",alpha = "1f", isClickable = true)
val eqv: ButSign = ButSign(20, R.drawable.but__17_,"=",alpha = "1f", isClickable = true)
