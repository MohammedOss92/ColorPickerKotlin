package com.sarrawi.mycolor

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.color.colorChooser
import com.afollestad.materialdialogs.list.listItems
import com.sarrawi.mycolor.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val PREFS_NAME = "color_prefs"
    private val KEY_COLOR = "selected_color"

    private var selectedColor: Int = Color.parseColor("#4CAF50") // لون افتراضي أخضر

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // استعادة اللون المحفوظ
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        selectedColor = prefs.getInt(KEY_COLOR, selectedColor)

        // تعيين اللون على الخلفية مباشرة عند تشغيل التطبيق
        binding.rootLayout.setBackgroundColor(selectedColor)

        binding.buttonPickColor.setOnClickListener {
            showColorPickerDialog()
        }

        // تعيين اللون على الخلفية
        binding.rootLayout.setBackgroundColor(selectedColor)

// تغيير لون زر اختيار اللون
        binding.buttonPickColor.setBackgroundColor(selectedColor)
        binding.buttonPickColor.setTextColor(getContrastingTextColor(selectedColor))

        applyColorToUI(selectedColor)

// إذا عندك نصوص ثانية مثل:
//        binding.textViewTitle.setTextColor(selectedColor)

// ... أضف المزيد حسب الحاجة
//        binding.toolbar.setBackgroundColor(selectedColor)
//        binding.toolbar.setTitleTextColor(getContrastingTextColor(selectedColor))
//        adapter.updateColor(selectedColor)


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
//        binding.textViewTitle.setTextColor(color)
        // كرر حسب عناصر الواجهة الأخرى
    }

}





class MainActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val PREFS_NAME = "color_prefs"
    private val KEY_COLOR_TYPE = "color_type"
    private val KEY_SOLID_COLOR = "selected_solid_color"
    private val KEY_GRADIENT_START_COLOR = "selected_gradient_start_color"
    private val KEY_GRADIENT_END_COLOR = "selected_gradient_end_color"

    private var selectedSolidColor: Int = Color.parseColor("#3F51B5")
    private var selectedGradientStartColor: Int = Color.parseColor("#2196F3")
    private var selectedGradientEndColor: Int = Color.parseColor("#3F51B5")

    private var isSolidColor: Boolean = true

    private val myColors = intArrayOf(
        Color.parseColor("#F44336"), Color.parseColor("#E91E63"),
        Color.parseColor("#9C27B0"), Color.parseColor("#673AB7"),
        Color.parseColor("#3F51B5"), Color.parseColor("#2196F3"),
        Color.parseColor("#03A9F4"), Color.parseColor("#00BCD4"),
        Color.parseColor("#009688"), Color.parseColor("#4CAF50"),
        Color.parseColor("#8BC34A"), Color.parseColor("#CDDC39"),
        Color.parseColor("#FFEB3B"), Color.parseColor("#FFC107"),
        Color.parseColor("#FF9800"), Color.parseColor("#FF5722"),
        Color.parseColor("#795548"), Color.parseColor("#9E9E9E"),
        Color.parseColor("#607D8B"),
        Color.parseColor("#FFFFFF"), Color.parseColor("#000000"),
        Color.parseColor("#AAAAAA"), Color.parseColor("#5C6BC0"),
        Color.parseColor("#B71C1C"), Color.parseColor("#1B5E20"),
        Color.parseColor("#BF360C"), Color.parseColor("#4A148C"),
        Color.parseColor("#880E4F")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        isSolidColor = prefs.getBoolean(KEY_COLOR_TYPE, true)

        if (isSolidColor) {
            selectedSolidColor = prefs.getInt(KEY_SOLID_COLOR, selectedSolidColor)
            applySolidColorToUI(selectedSolidColor)
        } else {
            selectedGradientStartColor = prefs.getInt(KEY_GRADIENT_START_COLOR, selectedGradientStartColor)
            selectedGradientEndColor = prefs.getInt(KEY_GRADIENT_END_COLOR, selectedGradientEndColor)
            applyGradientToUI(selectedGradientStartColor, selectedGradientEndColor)
        }

        binding.buttonPickColor.setOnClickListener {
            showColorTypeSelectionDialog()
        }

        if (isSolidColor) {
            binding.buttonPickColor.setBackgroundColor(selectedSolidColor)
            binding.buttonPickColor.setTextColor(getContrastingTextColor(selectedSolidColor))
        } else {
            binding.buttonPickColor.setBackgroundColor(selectedGradientStartColor)
            binding.buttonPickColor.setTextColor(getContrastingTextColor(selectedGradientStartColor))
        }
    }

    private fun showColorTypeSelectionDialog() {
        MaterialDialog(this).show {
            title(text = "اختر نوع الخلفية")
            listItems(items = listOf("لون ثابت", "تدرج لوني (Gradient)")) { _, index, text ->
                when (index) {
                    0 -> showSolidColorPickerDialog()
                    1 -> showGradientColorPickerDialog()
                }
            }
            negativeButton(text = "إلغاء")
        }
    }

    private fun showSolidColorPickerDialog() {
        MaterialDialog(this).show {
            title(text = "اختر لونًا ثابتًا")
            colorChooser(
                colors = myColors,
                // تم التعديل هنا: إزالة خيار ARGB المخصص
                allowCustomArgb = false,
                waitForPositiveButton = true
            ) { _, color ->
                selectedSolidColor = color
                isSolidColor = true
                applySolidColorToUI(selectedSolidColor)
                saveSolidColor(selectedSolidColor)
            }
            positiveButton(text = "اختيار")
            negativeButton(text = "إلغاء")
        }
    }

    private fun showGradientColorPickerDialog() {
        MaterialDialog(this).show {
            title(text = "اختر لون بداية التدرج")
            colorChooser(
                colors = myColors,
                // تم التعديل هنا: إزالة خيار ARGB المخصص
                allowCustomArgb = false,
                waitForPositiveButton = true
            ) { _, color ->
                selectedGradientStartColor = color
                showGradientEndColorPickerDialog()
            }
            positiveButton(text = "اختيار")
            negativeButton(text = "إلغاء")
        }
    }

    private fun showGradientEndColorPickerDialog() {
        MaterialDialog(this).show {
            title(text = "اختر لون نهاية التدرج")
            colorChooser(
                colors = myColors,
                // تم التعديل هنا: إزالة خيار ARGB المخصص
                allowCustomArgb = false,
                waitForPositiveButton = true
            ) { _, color ->
                selectedGradientEndColor = color
                isSolidColor = false
                applyGradientToUI(selectedGradientStartColor, selectedGradientEndColor)
                saveGradientColors(selectedGradientStartColor, selectedGradientEndColor)
            }
            positiveButton(text = "اختيار")
            negativeButton(text = "إلغاء")
        }
    }

    private fun saveSolidColor(color: Int) {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_COLOR_TYPE, true).apply()
        prefs.edit().putInt(KEY_SOLID_COLOR, color).apply()
    }

    private fun saveGradientColors(startColor: Int, endColor: Int) {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_COLOR_TYPE, false).apply()
        prefs.edit().putInt(KEY_GRADIENT_START_COLOR, startColor).apply()
        prefs.edit().putInt(KEY_GRADIENT_END_COLOR, endColor).apply()
    }

    private fun getContrastingTextColor(color: Int): Int {
        val darkness =
            1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
        return if (darkness < 0.5) Color.BLACK else Color.WHITE
    }

    private fun applySolidColorToUI(color: Int) {
        binding.rootLayout.setBackgroundColor(color)
        binding.buttonPickColor.setBackgroundColor(color)
        binding.buttonPickColor.setTextColor(getContrastingTextColor(color))
    }

    private fun applyGradientToUI(startColor: Int, endColor: Int) {
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(startColor, endColor)
        )
        binding.rootLayout.background = gradientDrawable
        binding.buttonPickColor.setBackgroundColor(startColor)
        binding.buttonPickColor.setTextColor(getContrastingTextColor(startColor))
    }
}


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val PREFS_NAME = "color_prefs"
    private val KEY_COLOR_TYPE = "color_type"
    private val KEY_SOLID_COLOR = "selected_solid_color"
    private val KEY_GRADIENT_START_COLOR = "selected_gradient_start_color"
    private val KEY_GRADIENT_MIDDLE_COLOR = "selected_gradient_middle_color" // مفتاح جديد للون الأوسط
    private val KEY_GRADIENT_END_COLOR = "selected_gradient_end_color"

    private var selectedSolidColor: Int = Color.parseColor("#3F51B5")
    private var selectedGradientStartColor: Int = Color.parseColor("#2196F3")
    private var selectedGradientMiddleColor: Int = Color.parseColor("#03A9F4") // لون افتراضي أوسط
    private var selectedGradientEndColor: Int = Color.parseColor("#3F51B5")

    private var isSolidColor: Boolean = true

    private val myColors = intArrayOf(
        Color.parseColor("#F44336"), Color.parseColor("#E91E63"),
        Color.parseColor("#9C27B0"), Color.parseColor("#673AB7"),
        Color.parseColor("#3F51B5"), Color.parseColor("#2196F3"),
        Color.parseColor("#03A9F4"), Color.parseColor("#00BCD4"),
        Color.parseColor("#009688"), Color.parseColor("#4CAF50"),
        Color.parseColor("#8BC34A"), Color.parseColor("#CDDC39"),
        Color.parseColor("#FFEB3B"), Color.parseColor("#FFC107"),
        Color.parseColor("#FF9800"), Color.parseColor("#FF5722"),
        Color.parseColor("#795548"), Color.parseColor("#9E9E9E"),
        Color.parseColor("#607D8B"),
        Color.parseColor("#FFFFFF"), Color.parseColor("#000000"),
        Color.parseColor("#AAAAAA"), Color.parseColor("#5C6BC0"),
        Color.parseColor("#B71C1C"), Color.parseColor("#1B5E20"),
        Color.parseColor("#BF360C"), Color.parseColor("#4A148C"),
        Color.parseColor("#880E4F")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        isSolidColor = prefs.getBoolean(KEY_COLOR_TYPE, true)

        if (isSolidColor) {
            selectedSolidColor = prefs.getInt(KEY_SOLID_COLOR, selectedSolidColor)
            applySolidColorToUI(selectedSolidColor)
        } else {
            selectedGradientStartColor = prefs.getInt(KEY_GRADIENT_START_COLOR, selectedGradientStartColor)
            selectedGradientMiddleColor = prefs.getInt(KEY_GRADIENT_MIDDLE_COLOR, selectedGradientMiddleColor) // استعادة اللون الأوسط
            selectedGradientEndColor = prefs.getInt(KEY_GRADIENT_END_COLOR, selectedGradientEndColor)
            applyGradientToUI(selectedGradientStartColor, selectedGradientMiddleColor, selectedGradientEndColor)
        }

        binding.buttonPickColor.setOnClickListener {
            showColorTypeSelectionDialog()
        }

        if (isSolidColor) {
            binding.buttonPickColor.setBackgroundColor(selectedSolidColor)
            binding.buttonPickColor.setTextColor(getContrastingTextColor(selectedSolidColor))
        } else {
            binding.buttonPickColor.setBackgroundColor(selectedGradientStartColor)
            binding.buttonPickColor.setTextColor(getContrastingTextColor(selectedGradientStartColor))
        }
    }

    private fun showColorTypeSelectionDialog() {
        MaterialDialog(this).show {
            title(text = "اختر نوع الخلفية")
            listItems(items = listOf("لون ثابت", "تدرج لوني (Gradient)")) { _, index, text ->
                when (index) {
                    0 -> showSolidColorPickerDialog()
                    1 -> showGradientStartColorPickerDialog() // تم تغيير اسم الدالة لبداية التدرج
                }
            }
            negativeButton(text = "إلغاء")
        }
    }

    private fun showSolidColorPickerDialog() {
        MaterialDialog(this).show {
            title(text = "اختر لونًا ثابتًا")
            colorChooser(
                colors = myColors,
                allowCustomArgb = false,
                waitForPositiveButton = true
            ) { _, color ->
                selectedSolidColor = color
                isSolidColor = true
                applySolidColorToUI(selectedSolidColor)
                saveSolidColor(selectedSolidColor)
            }
            positiveButton(text = "اختيار")
            negativeButton(text = "إلغاء")
        }
    }

    // دالة جديدة لبداية اختيار ألوان التدرج
    private fun showGradientStartColorPickerDialog() {
        MaterialDialog(this).show {
            title(text = "اختر لون بداية التدرج")
            colorChooser(
                colors = myColors,
                allowCustomArgb = false,
                waitForPositiveButton = true
            ) { _, color ->
                selectedGradientStartColor = color
                showGradientMiddleColorPickerDialog() // انتقل لاختيار اللون الأوسط
            }
            positiveButton(text = "اختيار")
            negativeButton(text = "إلغاء")
        }
    }

    // دالة جديدة لاختيار اللون الأوسط في التدرج
    private fun showGradientMiddleColorPickerDialog() {
        MaterialDialog(this).show {
            title(text = "اختر اللون الأوسط للتدرج")
            colorChooser(
                colors = myColors,
                allowCustomArgb = false,
                waitForPositiveButton = true
            ) { _, color ->
                selectedGradientMiddleColor = color
                showGradientEndColorPickerDialog() // انتقل لاختيار لون النهاية
            }
            positiveButton(text = "اختيار")
            negativeButton(text = "إلغاء")
        }
    }

    // دالة لاختيار لون النهاية في التدرج (التي كانت تسمى showGradientColorPickerDialog)
    private fun showGradientEndColorPickerDialog() {
        MaterialDialog(this).show {
            title(text = "اختر لون نهاية التدرج")
            colorChooser(
                colors = myColors,
                allowCustomArgb = false,
                waitForPositiveButton = true
            ) { _, color ->
                selectedGradientEndColor = color
                isSolidColor = false
                // تطبيق وحفظ الألوان الثلاثة
                applyGradientToUI(selectedGradientStartColor, selectedGradientMiddleColor, selectedGradientEndColor)
                saveGradientColors(selectedGradientStartColor, selectedGradientMiddleColor, selectedGradientEndColor)
            }
            positiveButton(text = "اختيار")
            negativeButton(text = "إلغاء")
        }
    }

    private fun saveSolidColor(color: Int) {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_COLOR_TYPE, true).apply()
        prefs.edit().putInt(KEY_SOLID_COLOR, color).apply()
    }

    private fun saveGradientColors(startColor: Int, middleColor: Int, endColor: Int) {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_COLOR_TYPE, false).apply()
        prefs.edit().putInt(KEY_GRADIENT_START_COLOR, startColor).apply()
        prefs.edit().putInt(KEY_GRADIENT_MIDDLE_COLOR, middleColor).apply() // حفظ اللون الأوسط
        prefs.edit().putInt(KEY_GRADIENT_END_COLOR, endColor).apply()
    }

    private fun getContrastingTextColor(color: Int): Int {
        val darkness =
            1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
        return if (darkness < 0.5) Color.BLACK else Color.WHITE
    }

    private fun applySolidColorToUI(color: Int) {
        binding.rootLayout.setBackgroundColor(color)
        binding.buttonPickColor.setBackgroundColor(color)
        binding.buttonPickColor.setTextColor(getContrastingTextColor(color))
    }

    // تم تعديل هذه الدالة لتقبل 3 ألوان
    private fun applyGradientToUI(startColor: Int, middleColor: Int, endColor: Int) {
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM, // يمكنك تغيير الاتجاه هنا
            intArrayOf(startColor, middleColor, endColor) // هنا يتم تحديد الألوان الثلاثة
        )
        binding.rootLayout.background = gradientDrawable
        binding.buttonPickColor.setBackgroundColor(startColor)
        binding.buttonPickColor.setTextColor(getContrastingTextColor(startColor))
    }
}