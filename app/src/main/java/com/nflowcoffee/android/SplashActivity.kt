package com.nflowcoffee.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startLoading()
    }

    private fun startLoading() {
        val handler = Handler()
        handler.postDelayed(Runnable {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 500)
    }
}