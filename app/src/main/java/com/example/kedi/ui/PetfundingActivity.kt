package com.example.kedi.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.kedi.R
import pereira.agnaldo.previewimgcol.ImageCollectionView


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

        //Set the mosaic of images
        var collectionView =  findViewById(R.id.imageCollectionView) as ImageCollectionView;
        val icon = BitmapFactory.decodeResource(
            this.getResources(),
            R.drawable.prot1
        )
        val icon2 = BitmapFactory.decodeResource(
            this.getResources(),
            R.drawable.prot2
        )
        val icon3 = BitmapFactory.decodeResource(
            this.getResources(),
            R.drawable.prot3
        )
        val icon4 = BitmapFactory.decodeResource(
            this.getResources(),
            R.drawable.prot4
        )
        val icon5 = BitmapFactory.decodeResource(
            this.getResources(),
            R.drawable.prot4
        )
        collectionView.addImage(icon);
        collectionView.addImage(icon2);
        collectionView.addImage(icon3);
        collectionView.addImage(icon4);
        collectionView.addImage(icon5);


        //Set the recucler view of donaters
        var gridView:GridView = findViewById(R.id.donnaters)
        var list = arrayListOf<Int>(R.drawable.perfil4,
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
        cardAdapter = CardAdapter(applicationContext, list)
        gridView.adapter = cardAdapter


    }

}