package id.fakhri_khairi.fleetify.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.fakhri_khairi.core.presentation.CoreActivity
import id.fakhri_khairi.fleetify.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, CoreActivity::class.java))
        }, ONE_SECOND)
    }

    companion object {
        private const val ONE_SECOND = 1 * 1000L
    }
}