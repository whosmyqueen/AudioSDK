package com.whosmyqueen.audiosdk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.UriUtils
import com.whosmyqueen.audiosdk.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnPlay.setOnClickListener {

            val file = UriUtils.uri2File(UriUtils.res2Uri("raw/sound_tip_finger"))
            AudioEngine.playSound(file.absolutePath)
        }
    }
}