package com.example.kedi.ui.petfunding

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kedi.R
import com.example.kedi.ui.ARG_GOAL
import com.example.kedi.ui.ARG_PROGRESS
import com.example.kedi.ui.PetfundingActivity
import kotlinx.android.synthetic.main.row_petfunding.view.*

class PetfundingFragment : Fragment() {

    private class PetfundingItem(
        title: String,
        total: Int,
        progress: Int,
        owner: String,
        urgent: Boolean,
        img: Int
    ) {
        var title = title
        var total = total
        var progress = progress
        var img = img
    }

    private class AdapterPetfunding(context: Context) : BaseAdapter() {

        private val mContext: Context
        private val petfundingItems : ArrayList<PetfundingItem>

        init {
            mContext = context
            petfundingItems = getPetfundingItems()
        }

        private fun getPetfundingItems(): ArrayList<PetfundingItem> {
            //Mocked data
            //Todo: get real petfunding items from img_back
            return arrayListOf<PetfundingItem>(
                PetfundingItem("Operación Limón", 200, 180, "progape", false, R.drawable.img_prot3),
                PetfundingItem("Restaurar Galpón", 190, 90, "Refuxio Bando", false, R.drawable.img_prot5),
                PetfundingItem("Pintar Muro", 500, 10, "Progape", false, R.drawable.img_prot1),
                PetfundingItem(
                    "Medicinas Michi",
                    58,
                    20,
                    "Protectora de Lugo",
                    false,
                    R.drawable.img_prot4
                ),
                PetfundingItem("Pintar Muro", 500, 10, "Progape", false, R.drawable.img_perro4),
                PetfundingItem(
                    "Medicinas Michi",
                    58,
                    20,
                    "Protectora de Lugo",
                    false,
                    R.drawable.progape
                )
            )
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
            //Set data of the list element
            val layoutInflater = LayoutInflater.from(mContext)
            val row_main = layoutInflater.inflate(R.layout.row_petfunding, p2, false)
            var pct =(petfundingItems.get(p0).progress.toFloat() /
                        petfundingItems.get(p0).total.toFloat()) * 100
            row_main.ownerPet.text = petfundingItems.get(p0).title
            //Set level of the heart progress graphic
            row_main.button.background.setLevel((pct * 100).toInt())
            row_main.progressText.text =
                "${petfundingItems.get(p0).progress}/${petfundingItems.get(p0).total}€"
            val im = row_main.findViewById<ImageView>(R.id.image)
            im.setImageResource(petfundingItems.get(p0).img)
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
        })
        return root
    }

    override fun onActivityCreated(state: Bundle?) {
        super.onActivityCreated(state)
        val listView = requireView().findViewById<View>(R.id.petfunding_list) as GridView
        listView.adapter =
            getActivity()?.let { PetfundingFragment.AdapterPetfunding(it.getApplicationContext()) }

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this.context, PetfundingActivity::class.java)
            val activity = context as Activity
            val shared_item = androidx.core.util.Pair(
                view.button as View?,
                ViewCompat.getTransitionName(view.button)
            )
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, shared_item
            )

            val item = listView.adapter.getItem(position) as PetfundingItem
            //add parameters to the intent
            intent.putExtra(ARG_GOAL, item.total)
            intent.putExtra(ARG_PROGRESS, item.progress)//int
            startActivity(intent, options.toBundle())
        }
    }

}