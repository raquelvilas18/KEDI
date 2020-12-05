package com.example.kedi

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kedi.ui.announcements.AnnouncementsFragment
import kotlinx.android.synthetic.main.activity_announcement.*
import java.text.SimpleDateFormat

const val ARG_IMG = "arg_img"
const val ARG_NAME = "arg_name"
const val ARG_INIT = "arg_init"
const val ARG_END = "arg_end"



class announcement : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_announcement)

        //val obj_anuncio: AnnouncementsFragment.Anuncio = intent.getSerializableExtra(ARG_NAME) as AnnouncementsFragment.Anuncio
        setAnnouncementData();

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Anuncio"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun setAnnouncementData(){
        //imagePet.background = anuncio.img as Drawable;
        val profileName=intent.getStringExtra(ARG_NAME)
        tittle.text = "Acogida de ${profileName}"

        val img=intent.extras?.get(ARG_IMG) as Int
        imagePet.setBackgroundResource(img)
        /*val format = SimpleDateFormat("dd MMM")
        initDate.text = format.format(anuncio.fechaInicio).toString().toUpperCase()
        endDate.text = format.format(anuncio.fechaFin).toString().toUpperCase()
        price.text = "${anuncio.price} €"*/
    }
}

