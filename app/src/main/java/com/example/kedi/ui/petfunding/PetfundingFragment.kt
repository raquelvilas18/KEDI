package com.example.kedi.ui.petfunding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kedi.R
import com.example.kedi.ui.announcements.AnnouncementsFragment
import kotlinx.android.synthetic.main.row_petfunding.view.*
import java.text.SimpleDateFormat
import java.util.*

class PetfundingFragment : Fragment() {

    private class PetfundingItem(
        title: String,
        total: Int,
        progress: Int,
        owner: String,
        urgent: Boolean,
        img: Int
    ){
        var title = title
        var total = total
        var progress = progress
        var owner = owner
        var urgent = urgent
        var img = img
    }

    private class AdapterPetfunding(context: Context): BaseAdapter(){

        private val mContext: Context
        private val petfundingItems = arrayListOf<PetfundingItem>(
            PetfundingItem("Operación Limón", 200, 80 , "progape", false, R.drawable.ph_gato1),
            PetfundingItem("Restaurar Galpón", 190 , 90 , "Refuxio Bando", false, R.drawable.perro1),
            PetfundingItem("Pintar Muro", 500 , 10 , "Progape", false, R.drawable.gato2),
            PetfundingItem("Medicinas Michi", 58 , 20, "Protectora de Lugo", false, R.drawable.perro2),
            PetfundingItem("Veterinario Leon", 100, 90 , "Refuxio de Bando", false, R.drawable.perro3)
        )

        init {
            mContext = context
        }
        override fun getCount(): Int {
            return petfundingItems.size;
        }

        override fun getItem(p0: Int): Any {
            return petfundingItems.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val row_main =  layoutInflater.inflate(R.layout.row_petfunding, p2, false)
            row_main.title.text = petfundingItems.get(p0).title
            row_main.owner.text = petfundingItems.get(p0).owner

            row_main.progressBar.progress = petfundingItems.get(p0).progress
            row_main.progressBar.max = petfundingItems.get(p0).total

            val im = row_main.findViewById<ImageView>(R.id.image)
            im.setBackgroundResource(petfundingItems.get(p0).img)
            return row_main
        }
    }

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_petfunding, container, false)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })
        return root
    }

    override fun onActivityCreated(state: Bundle?) {
        super.onActivityCreated(state)
        val listView = requireView().findViewById<View>(R.id.petfunding_list) as ListView
        listView.adapter = getActivity()?.let { PetfundingFragment.AdapterPetfunding(it.getApplicationContext()) }
    }
}