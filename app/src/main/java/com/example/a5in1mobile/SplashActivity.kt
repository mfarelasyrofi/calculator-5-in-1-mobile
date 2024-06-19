package com.example.a5in1mobile

import android.content.Intent
import android.os.Handler;
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Mengatur timer untuk menunda tampilan splash screen
        Handler().postDelayed(Runnable {
            // Aksi yang dilakukan setelah menampilkan splash screen
            val intent = Intent(
                this@SplashActivity,
                MainActivity::class.java
            )
            startActivity(intent)
            finish() // Menutup activity splash screen agar tidak kembali lagi
        }, 3000) // Waktu dalam milidetik (misalnya 3000 = 3 detik)
    }
}
