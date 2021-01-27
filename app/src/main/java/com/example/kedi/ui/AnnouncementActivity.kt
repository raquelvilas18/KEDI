package com.example.kedi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kedi.R
import kotlinx.android.synthetic.main.activity_announcement.*

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

        supportActionBar?.hide()
        back_btn2.setOnClickListener {
            this.finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun setAnnouncementData() {
        //imagePet.background = anuncio.img as Drawable;
        val profileName = intent.getStringExtra(ARG_NAME)
        tittle.text = "Acogida de ${profileName}"

        val img = intent.extras?.get(ARG_IMG) as Int
        imagePet.setImageResource(img)
        /*val format = SimpleDateFormat("dd MMM")
        initDate.text = format.format(anuncio.fechaInicio).toString().toUpperCase()
        endDate.text = format.format(anuncio.fechaFin).toString().toUpperCase()
        price.text = "${anuncio.price} €"*/
    }
}

