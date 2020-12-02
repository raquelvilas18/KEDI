package com.example.kedi.ui.announcements

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kedi.R
import java.text.SimpleDateFormat
import java.util.*


class AnnouncementsFragment : Fragment() {


    private lateinit var announcementsViewModel: AnnouncementsViewModel

    private class Anuncio(
        name: String,
        price: Int,
        fechaInicio: Date,
        fechaFin: Date,
        ciudad: String,
        img: Int
    ){
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
            Anuncio("Limón", 20, Date(), Date(), "Santiago de Compostela", R.drawable.ph_gato1),
            Anuncio("Ron", 16, Date(), Date(), "Santiago de Compostela", R.drawable.perro1),
            Anuncio("Cuba", 18, Date(), Date(), "Milladoiro", R.drawable.gato2),
            Anuncio("Michi", 27, Date(), Date(), "Bertamirans", R.drawable.perro2),
            Anuncio("Leon", 30, Date(), Date(), "Santiago de Compostela", R.drawable.perro3),
            Anuncio("Chile", 20, Date(), Date(), "Milladoiro", R.drawable.perro4),
            Anuncio("Cuba", 18, Date(), Date(), "Milladoiro", R.drawable.gato2),
            Anuncio("Michi", 27, Date(), Date(), "Bertamirans", R.drawable.perro2),
            Anuncio("Leon", 30, Date(), Date(), "Santiago de Compostela", R.drawable.perro3),
            Anuncio("Chile", 20, Date(), Date(), "Milladoiro", R.drawable.perro4),
            Anuncio("Trusqui", 22, Date(), Date(), "Santiago de Compostela", R.drawable.ph_gato1)
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
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val list = requireView().findViewById<View>(R.id.announcements_list)as ListView
       // list.adapter = MyAdapter(requireContext().applicationContext)

        announcementsViewModel =
            ViewModelProviders.of(this).get(AnnouncementsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_announcements, container, false)
        //val textView: TextView = root.findViewById(R.id.text_dashboard)
        announcementsViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })
        return root
    }

    override fun onActivityCreated(state: Bundle?) {
        super.onActivityCreated(state)
        val listView = requireView().findViewById<View>(R.id.announcements_list) as ListView
        listView.adapter = getActivity()?.let { MyAdapter(it.getApplicationContext()) }
    }


}