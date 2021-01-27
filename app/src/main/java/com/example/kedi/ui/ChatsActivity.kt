package com.example.kedi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kedi.R

class ChatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)
        supportActionBar!!.title = "Chats"
        //set back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}