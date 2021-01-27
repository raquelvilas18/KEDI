package com.example.kedi.ui

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.kedi.R
import kotlinx.android.synthetic.main.activity_petfunding.*
import pereira.agnaldo.previewimgcol.ImageCollectionView

const val ARG_GOAL = "arg_pet_goal"
const val ARG_PROGRESS = "arg_pet_progress"
const val ARG_IMG_PET = "arg_pet_img"

class PetfundingActivity : AppCompatActivity() {
    class CardAdapter(var context : Context, var arrayList: ArrayList<Int>): BaseAdapter(){
        override fun getCount(): Int {
            return arrayList.size
        }

        override fun getItem(p0: Int): Any {
            return arrayList.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var view:View = View.inflate(context, R.layout.card_view_people_item, null )
            var img:ImageView = view.findViewById(R.id.card_image)

            img.setImageResource(arrayList.get(p0))
            return view
        }

    }

    private var cardAdapter: CardAdapter ? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_petfunding)
        supportActionBar?.hide()

        //Set the mosaic of images
        createImageCollection()
        setGridOfDonaters()
    }

    private fun setGridOfDonaters(){
        //Set the recycler view of donaters
        var gridView:GridView = findViewById(R.id.donnaters)
        var donatorsList = getDonatorsImages()
        cardAdapter = CardAdapter(applicationContext, donatorsList)
        gridView.adapter = cardAdapter
    }
    private fun getDonatorsImages(): ArrayList<Int> {
        //mocked Data
        return arrayListOf<Int>(R.drawable.perfil4,
            R.drawable.perfil3,
            R.drawable.perfil2,
            R.drawable.perfil1,
            R.drawable.perfil4,
            R.drawable.perfil3,
            R.drawable.perfil2,
            R.drawable.perfil4,
            R.drawable.perfil3,
            R.drawable.perfil3,
            R.drawable.perfil2,
            R.drawable.perfil1,
            R.drawable.perfil4,
            R.drawable.perfil3,
            R.drawable.perfil2,
            R.drawable.perfil4,
            R.drawable.perfil3,
            R.drawable.perfil3,
            R.drawable.perfil2,
            R.drawable.perfil1,
            R.drawable.perfil4,
            R.drawable.perfil3,
            R.drawable.perfil2,
            R.drawable.perfil4,
            R.drawable.perfil3,
            R.drawable.perfil2)
    }

    private fun createImageCollection(){
        val drawables = getMosaicImages()
        //Set the mosaic of images
        var collectionView =  findViewById(R.id.imageCollectionView) as ImageCollectionView;
        for (draw in drawables){
            var icon = BitmapFactory.decodeResource(
                this.getResources(),
                draw
            )
            collectionView.addImage(icon);
        }
    }

    private fun getMosaicImages(): ArrayList<Int> {
        //Mocked data
        return arrayListOf(R.drawable.prot4, R.drawable.prot3, R.drawable.prot2, R.drawable.prot1)
    }

    private fun setPetfundingData(){
        //Get data from params
        val max=intent.extras?.get(ARG_GOAL) as Int
        val progress=intent.extras?.get(ARG_PROGRESS) as Int
        val percentage = (progress.toFloat()/max.toFloat()) *100
        button2.background.setLevel((percentage * 100).toInt())
        button.startRippleAnimation();
        progressText.text = "${progress}/${max}€"
    }

}