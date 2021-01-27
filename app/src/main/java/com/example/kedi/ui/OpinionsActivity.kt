package com.example.kedi.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.kedi.R
import kotlinx.android.synthetic.main.activity_opinions.*
import kotlinx.android.synthetic.main.row_opinion.view.*

class OpinionsActivity : AppCompatActivity() {

    private class Opinion(
        opinion: String,
        punctuation: Int,
        owner: String,
        img: Int
    ) {
        var opinion = opinion
        var punctuation = punctuation
        var owner = owner
        var img = img
    }


    private class AdapterOpinion(context: Context) : BaseAdapter() {

        private val mContext: Context
        private val opinions: ArrayList<Opinion>

        init {
            mContext = context
            opinions = getOpinions()
        }

        private fun getOpinions(): ArrayList<Opinion> {
            //Mocked data
            //Todo: get real opinions from img_back
            return arrayListOf<Opinion>(
                Opinion(
                    "Todo genia, cuido perfecto de mi gato y mandó muchisimas fotos para que estuvieramos en contacto",
                    5,
                    "Martín López",
                    R.drawable.img_perfil1
                ),
                Opinion("Todo genial, se encargó perfecto", 4, "Marta Alvarez", R.drawable.img_perfil2),
                Opinion(
                    "Estuvo paseando a mi perro por una semana, siempre fue puntual y muy amable",
                    5,
                    "María Lata",
                    R.drawable.img_perfil3
                ),
                Opinion("Perfecto", 3, "Juan Villar", R.drawable.img_perfil4)
            )
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
            val layout_inflater = LayoutInflater.from(mContext)
            val row_main = layout_inflater.inflate(R.layout.row_opinion, p2, false)
            row_main.comment.text = opinions.get(p0).opinion
            row_main.valoration.text = "${opinions.get(p0).punctuation}/5"
            val im = row_main.findViewById<ImageView>(R.id.image)
            im.setImageResource(opinions.get(p0).img)
            return row_main
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opinions)

        configureActivity()
    }

    private fun configureActivity(){
        supportActionBar!!.title = "Opiniones"
        //set img_back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        listView.adapter = AdapterOpinion(this)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}