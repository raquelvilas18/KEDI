package com.example.kedi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kedi.R

class ChatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Chats"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}