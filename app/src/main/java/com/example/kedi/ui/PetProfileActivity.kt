package com.example.kedi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kedi.R
import kotlinx.android.synthetic.main.fragment_profile.*

class PetProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_profile)

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Perfil Mascota"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        petImage?.setOnClickListener {
            val intent = Intent(this, PetProfileActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}