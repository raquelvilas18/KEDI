package com.example.kedi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kedi.R
import kotlinx.android.synthetic.main.activity_pet_profile.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.petImage

class PetProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_profile)

        configureListeners()
    }
    private fun configureListeners(){
        petImage?.setOnClickListener {
            val intent = Intent(this, PetProfileActivity::class.java)
            startActivity(intent)
        }

        back_btn?.setOnClickListener{
            this.finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}