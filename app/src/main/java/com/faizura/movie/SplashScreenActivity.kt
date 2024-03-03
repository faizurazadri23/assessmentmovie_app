package com.faizura.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.faizura.movie.databinding.SplashScreenBinding
import com.faizura.movie.ui.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var splashScreenBinding: SplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreenBinding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(splashScreenBinding.root)

        supportActionBar?.hide()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed(
            {
                startActivity(Intent(this, MainActivity::class.java))
            }, SPLASH_TIME_OUT
        )
    }

    companion object {
        private const val SPLASH_TIME_OUT: Long = 3000
    }
}