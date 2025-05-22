package com.sarrawi.mycolor

import android.graphics.Color

fun getContrastingTextColor(backgroundColor: Int): Int {
    val r = Color.red(backgroundColor)
    val g = Color.green(backgroundColor)
    val b = Color.blue(backgroundColor)

    // حساب السطوع (luminance)
    val brightness = (r * 299 + g * 587 + b * 114) / 1000

    // إذا كانت الخلفية فاتحة، اجعل النص غامق (أسود)، والعكس
    return if (brightness >= 128) Color.BLACK else Color.WHITE
}
