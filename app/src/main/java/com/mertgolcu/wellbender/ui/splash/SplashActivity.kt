package com.mertgolcu.wellbender.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mert Gölcü on 19.12.2022.
 */

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
