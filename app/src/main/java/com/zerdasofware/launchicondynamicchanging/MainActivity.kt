package com.zerdasofware.launchicondynamicchanging

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    enum class ICON_COLOUR { RED, GREEN, BLUE, MainActivity }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        red.setOnClickListener { setIcon(ICON_COLOUR.RED) }
        green.setOnClickListener { setIcon(ICON_COLOUR.GREEN) }
        blue.setOnClickListener { setIcon(ICON_COLOUR.BLUE) }
        btn_default.setOnClickListener { setIcon(ICON_COLOUR.MainActivity) }
    }

    private fun setIcon(targetColour: ICON_COLOUR) {
        val manager = packageManager
        for (value in ICON_COLOUR.values()) {
            val action = if (value == targetColour) {
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            } else {
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED
            }
            manager.setComponentEnabledSetting(
                ComponentName(BuildConfig.APPLICATION_ID, "${BuildConfig.APPLICATION_ID}.${value.name}"),
                action,
                PackageManager.DONT_KILL_APP
            )
        }
    }
}