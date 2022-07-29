package com.example.densitytesting

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.TextView
import kotlin.math.hypot

class MainActivity : AppCompatActivity() {

    private lateinit var metricsTextDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var x = 0
        var y = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            x = windowManager.maximumWindowMetrics.bounds.width()
            y = windowManager.maximumWindowMetrics.bounds.height()
        } else {
            x = resources.displayMetrics.heightPixels
            y = resources.displayMetrics.widthPixels
        }

        metricsTextDisplay = findViewById(R.id.metricsText)

        metricsTextDisplay.text = resources.displayMetrics.toString()

        getScreenSize(x, y)
    }

    private fun getScreenSize(width: Int, height: Int) {
        val screenSize =
            hypot(
                (width / resources.displayMetrics.xdpi).toDouble(),
                (height / resources.displayMetrics.ydpi).toDouble()
            )

        var x =
            metricsTextDisplay.text.toString() + "\nscreenSize = $screenSize inch \n densityDPI = ${resources.displayMetrics.densityDpi}dpi \n densityType = ${getDpiType()}"
        metricsTextDisplay.text = x

    }

    private fun getDpiType(): String {
       return when (resources.displayMetrics.densityDpi) {
            in 0..DisplayMetrics.DENSITY_LOW -> "ldpi"
            in DisplayMetrics.DENSITY_LOW..DisplayMetrics.DENSITY_MEDIUM -> "mdpi"
            in DisplayMetrics.DENSITY_MEDIUM..DisplayMetrics.DENSITY_HIGH -> "hdpi"
            in DisplayMetrics.DENSITY_HIGH..DisplayMetrics.DENSITY_XHIGH -> "xhdpi"
            in DisplayMetrics.DENSITY_XHIGH..DisplayMetrics.DENSITY_XXHIGH -> "xxhdpi"
            in DisplayMetrics.DENSITY_XXHIGH..DisplayMetrics.DENSITY_XXXHIGH -> "xxxhdpi"
            else -> "xxxhdpi"
        }
    }
}