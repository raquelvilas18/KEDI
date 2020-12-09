package com.example.kedi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_opinions.*
import kotlinx.android.synthetic.main.row_opinion.view.*
import kotlinx.android.synthetic.main.row_petfunding.view.*

class Opinions : AppCompatActivity() {

    private class Opinion(
        opinion: String,
        punctuation: Int,
        owner: String,
        img: Int
    ){
        var opinion = opinion
        var punctuation = punctuation
        var owner = owner
        var img = img
    }

    private class AdapterOpinion(context: Context): BaseAdapter(){

        private val mContext: Context
        private val opinions = arrayListOf<Opinion>(
            Opinion("Todo genia, cuido perfecto de mi gato y mandó muchisimas fotos para que estuvieramos en contacto", 5, "Martín López" , R.drawable.perfil1),
            Opinion("Todo genial, se encargó perfecto", 4 , "Marta Alvarez", R.drawable.perfil2),
            Opinion("Estuvo paseando a mi perro por una semana, siempre fue puntual y muy amable", 5 , "María Lata" ,  R.drawable.perfil3),
            Opinion("Perfecto", 3 , "Juan Villar", R.drawable.perfil4)
        )

        init {
            mContext = context
        }
        override fun getCount(): Int {
            return opinions.size;
        }

        override fun getItem(p0: Int): Any {
            return opinions.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val row_main =  layoutInflater.inflate(R.layout.row_opinion, p2, false)
            row_main.comment.text = opinions.get(p0).opinion
            row_main.ownerPet.text = opinions.get(p0).owner

            row_main.valoration.text = "${opinions.get(p0).punctuation}/5"
            val im = row_main.findViewById<ImageView>(R.id.image)
            im.setImageResource(opinions.get(p0).img)
            return row_main
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opinions)
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Opiniones"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        listView.adapter = AdapterOpinion(this)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}