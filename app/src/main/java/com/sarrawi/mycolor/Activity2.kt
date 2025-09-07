
package com.sarrawi.mycolor

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.color.colorChooser
import com.sarrawi.mycolor.databinding.ActivityMain2Binding



class Activity22 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    private val PREFS_NAME = "color_prefs"
    private val KEY_COLOR = "selected_color"

    private var selectedColor: Int = Color.parseColor("#4CAF50") // لون افتراضي أخضر

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // استعادة اللون المحفوظ
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        selectedColor = prefs.getInt(KEY_COLOR, selectedColor)

        // تعيين اللون على الخلفية مباشرة عند تشغيل التطبيق
        binding.rootLayout.setBackgroundColor(selectedColor)

        binding.buttonPickColor.setOnClickListener {
            showColorPickerDialog()
        }
    }

    private fun showColorPickerDialog() {
        MaterialDialog(this).show {
            title(text = "اختر لون")
            colorChooser(
                colors = intArrayOf(
                    Color.parseColor("#F44336"), // أحمر
                    Color.parseColor("#E91E63"), // وردي
                    Color.parseColor("#9C27B0"), // بنفسجي
                    Color.parseColor("#673AB7"), // أزرق غامق
                    Color.parseColor("#3F51B5"), // أزرق
                    Color.parseColor("#2196F3"), // أزرق سماوي
                    Color.parseColor("#03A9F4"), // أزرق فاتح
                    Color.parseColor("#00BCD4"), // تركواز
                    Color.parseColor("#009688"), // أخضر غامق
                    Color.parseColor("#4CAF50"), // أخضر
                    Color.parseColor("#8BC34A"), // أخضر فاتح
                    Color.parseColor("#CDDC39"), // أصفر أخضر
                    Color.parseColor("#FFEB3B"), // أصفر
                    Color.parseColor("#FFC107"), // أصفر غامق
                    Color.parseColor("#FF9800"), // برتقالي
                    Color.parseColor("#FF5722"), // برتقالي غامق
                    Color.parseColor("#795548"), // بني
                    Color.parseColor("#9E9E9E"), // رمادي
                    Color.parseColor("#607D8B")  // أزرق رمادي
                ),
                allowCustomArgb = true,  // يسمح باختيار لون مخصص
                waitForPositiveButton = true
            ) { dialog, color ->
                selectedColor = color
                binding.rootLayout.setBackgroundColor(selectedColor)
                saveColor(selectedColor)
            }
            positiveButton(text = "اختيار")
            negativeButton(text = "إلغاء")
        }
    }

    private fun saveColor(color: Int) {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putInt(KEY_COLOR, color).apply()
    }
}

class Activity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    private val PREFS_NAME = "color_prefs"
    private val KEY_COLOR = "selected_color"

    // لون افتراضي ثابت
    private var selectedColor: Int = Color.parseColor("#4CAF50")

    // قائمة الألوان التي تريدها في منتقي الألوان
    private val myColors = intArrayOf(
        Color.parseColor("#F44336"), // أحمر
        Color.parseColor("#E91E63"), // وردي
        Color.parseColor("#9C27B0"), // بنفسجي
        Color.parseColor("#673AB7"), // أزرق غامق
        Color.parseColor("#3F51B5"), // أزرق
        Color.parseColor("#2196F3"), // أزرق سماوي
        Color.parseColor("#03A9F4"), // أزرق فاتح
        Color.parseColor("#00BCD4"), // تركواز
        Color.parseColor("#009688"), // أخضر غامق
        Color.parseColor("#4CAF50"), // أخضر
        Color.parseColor("#8BC34A"), // أخضر فاتح
        Color.parseColor("#CDDC39"), // أصفر أخضر
        Color.parseColor("#FFEB3B"), // أصفر
        Color.parseColor("#FFC107"), // أصفر غامق
        Color.parseColor("#FF9800"), // برتقالي
        Color.parseColor("#FF5722"), // برتقالي غامق
        Color.parseColor("#795548"), // بني
        Color.parseColor("#9E9E9E"), // رمادي
        Color.parseColor("#607D8B"), // أزرق رمادي
        Color.parseColor("#FFFFFF"), // أبيض
        Color.parseColor("#000000"), // أسود
        Color.parseColor("#AAAAAA"), // رمادي فاتح
        Color.parseColor("#5C6BC0"), // بنفسجي سماوي
        Color.parseColor("#B71C1C"), // أحمر داكن
        Color.parseColor("#1B5E20"), // أخضر داكن جدًا
        Color.parseColor("#BF360C"), // برتقالي محروق
        Color.parseColor("#4A148C"), // بنفسجي أرجواني داكن
        Color.parseColor("#880E4F")  // قرمزي داكن
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // استعادة اللون المحفوظ
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        selectedColor = prefs.getInt(KEY_COLOR, selectedColor)

        // تعيين اللون على الخلفية مباشرة عند تشغيل التطبيق
        binding.rootLayout.setBackgroundColor(selectedColor)

        binding.buttonPickColor.setOnClickListener {
            showColorPickerDialog()
        }

        // تعيين اللون على الخلفية وزر الاختيار
        applyColorToUI(selectedColor)

    }

    private fun showColorPickerDialog() {
        MaterialDialog(this).show {
            title(text = "اختر لون")
            colorChooser(
                colors = myColors, // استخدام قائمة الألوان الكاملة
                allowCustomArgb = false, // تم إزالة خيار ARGB
                waitForPositiveButton = true
            ) { dialog, color ->
                selectedColor = color
                applyColorToUI(selectedColor)
                saveColor(selectedColor)
            }
            positiveButton(text = "اختيار")
            negativeButton(text = "إلغاء")
        }
    }

    private fun saveColor(color: Int) {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putInt(KEY_COLOR, color).apply()
    }

    // تعطيك لون نص مناسب حسب خلفية اللون
    private fun getContrastingTextColor(color: Int): Int {
        val darkness =
            1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
        return if (darkness < 0.5) Color.BLACK else Color.WHITE
    }

    private fun applyColorToUI(color: Int) {
        binding.rootLayout.setBackgroundColor(color)
        binding.buttonPickColor.setBackgroundColor(color)
        binding.buttonPickColor.setTextColor(getContrastingTextColor(color))
    }

}