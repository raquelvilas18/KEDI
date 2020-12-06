package com.example.kedi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Opinions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opinions)
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Opiniones"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}