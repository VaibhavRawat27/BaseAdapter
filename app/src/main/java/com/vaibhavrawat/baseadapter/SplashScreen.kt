package com.vaibhavrawat.baseadapter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        },2000)
    }
}