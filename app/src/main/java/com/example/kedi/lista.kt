package com.example.kedi

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import java.text.SimpleDateFormat
import java.util.*

class lista : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        val lista = findViewById<ListView>(R.id.lista)

        lista.adapter = MyAdapter(this)

        lista.setOnItemClickListener(){parent, view, position, id ->
            Toast.makeText(this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()
        }

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private class Anuncio(name:String, price: Int, fechaInicio: Date, fechaFin: Date, ciudad: String, img:Int){
        var name = name
        var price = price
        var fechaInicio = fechaInicio
        var fechaFin = fechaFin
        var ciudad = ciudad
        var img = img
    }

    private class MyAdapter(context: Context): BaseAdapter(){

        private val mContext: Context
        private val anuncios = arrayListOf<Anuncio>(
            Anuncio("Limón", 20, Date(), Date(), "Santiago de Compostela", R.drawable.gato1),
            Anuncio("Ron", 16, Date(), Date(), "Santiago de Compostela", R.drawable.perro1),
            Anuncio("Cuba", 18, Date(), Date(), "Milladoiro", R.drawable.gato2),
            Anuncio("Michi", 27, Date(), Date(), "Bertamirans", R.drawable.perro2),
            Anuncio("Leon", 30, Date(), Date(), "Santiago de Compostela", R.drawable.perro3),
            Anuncio("Chile", 20, Date(), Date(), "Milladoiro", R.drawable.perro4),
            Anuncio("Cuba", 18, Date(), Date(), "Milladoiro", R.drawable.gato2),
            Anuncio("Michi", 27, Date(), Date(), "Bertamirans", R.drawable.perro2),
            Anuncio("Leon", 30, Date(), Date(), "Santiago de Compostela", R.drawable.perro3),
            Anuncio("Chile", 20, Date(), Date(), "Milladoiro", R.drawable.perro4),
            Anuncio("Trusqui", 22, Date(), Date(), "Santiago de Compostela", R.drawable.gato1)
        )

        init {
            mContext = context
        }
        override fun getCount(): Int {
            return anuncios.size;
        }

        override fun getItem(p0: Int): Any {
            return anuncios.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val row_main =  layoutInflater.inflate(R.layout.row_lista, p2, false)
            val nameTextView = row_main.findViewById<TextView>(R.id.name)
            nameTextView.text = anuncios.get(p0).name
            val cityTextView = row_main.findViewById<TextView>(R.id.city)
            cityTextView.text = anuncios.get(p0).ciudad

            val fecha0 = row_main.findViewById<TextView>(R.id.date0)
            val format = SimpleDateFormat("dd MMM")
            fecha0.text = format.format(anuncios.get(p0).fechaInicio).toString().toUpperCase()
            val fecha1 = row_main.findViewById<TextView>(R.id.date1)
            fecha1.text = format.format(anuncios.get(p0).fechaFin).toString().toUpperCase()

            val price = row_main.findViewById<TextView>(R.id.price)
            price.text = anuncios.get(p0).price.toString() + "€"
            val im = row_main.findViewById<ImageView>(R.id.image)
            im.setBackgroundResource(anuncios.get(p0).img)
            return row_main
        }

    }
}