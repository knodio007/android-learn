package com.knodio.androidlearn

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.knodio.androidlearn.databinding.ActivityOcractivityBinding


class OCRActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOcractivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOcractivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val ocrButton = binding.ocrButton
        val ocrImage = binding.ocrImage
        val orcText = binding.orcText


        val register = registerForActivityResult(ActivityResultContracts.GetContent()) {
            ocrImage.setImageURI(it)
            orcText.text = it.path
        }

        ocrButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf( Manifest.permission.WRITE_EXTERNAL_STORAGE ), 1);
            }
            register.launch("image/*")
        }
    }
}