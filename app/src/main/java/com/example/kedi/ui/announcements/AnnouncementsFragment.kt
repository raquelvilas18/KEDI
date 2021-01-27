package com.example.kedi.ui.announcements

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kedi.R
import com.example.kedi.ui.ARG_IMG
import com.example.kedi.ui.ARG_NAME
import com.example.kedi.ui.announcement
import kotlinx.android.synthetic.main.fragment_announcements.*
import kotlinx.android.synthetic.main.row_announcements.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AnnouncementsFragment : Fragment() {


    private lateinit var announcementsViewModel: AnnouncementsViewModel
    private lateinit var listAdapter: AnnouncementsAdapter

    class Announcement(
        name: String,
        price: Int,
        fechaInicio: Date,
        fechaFin: Date,
        ciudad: String,
        img: Int
    ) {
        var name = name
        var price = price
        var fechaInicio = fechaInicio
        var fechaFin = fechaFin
        var ciudad = ciudad
        var img = img
    }

    class AnnouncementViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val announcement_image: ImageView = itemView.image
        val announcement_bg: ConstraintLayout = itemView.bg_card
        val announcement_image_card: CardView = itemView.imageCard
        val announcement_tittle: TextView = itemView.petOwner
        val announcement_date0: TextView = itemView.date0
        val announcement_date1: TextView = itemView.date1
        val announcement_price: TextView = itemView.valoration
        val announcement_city: TextView = itemView.city

        fun bind(anuncio: Announcement, context: Context) {
            //set values to the view
            announcement_tittle.text = anuncio.name
            announcement_city.text = anuncio.ciudad
            announcement_price.text = "${anuncio.price}€"
            val format = SimpleDateFormat("dd MMM")
            announcement_date0.text = format.format(anuncio.fechaInicio).toString().toUpperCase()
            announcement_date1.text = format.format(anuncio.fechaFin).toString().toUpperCase()
            announcement_image.setImageResource(anuncio.img)

            //Add listener
            itemView.setOnClickListener({
                val intent = Intent(context, announcement::class.java)
                val activity = context as Activity
                val shared_item = androidx.core.util.Pair(
                    announcement_image_card as View?,
                    ViewCompat.getTransitionName(announcement_image_card)
                )
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, shared_item
                )
                //Pass parameters to the announcement activity
                intent.putExtra(ARG_NAME, anuncio.name)
                intent.putExtra(ARG_IMG, anuncio.img)
                if (options != null) {
                    startActivity(context, intent, options.toBundle())
                }
            })
        }
    }


    private class AnnouncementsAdapter(context: Context) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private val mContext: Context
        private val announcements: ArrayList<Announcement>

        init {
            mContext = context
            announcements = getAnnouncements()
        }

        private fun getAnnouncements(): ArrayList<Announcement> {
            //Mocked data
            //Todo: get real announcements from img_back
            return arrayListOf<Announcement>(
                Announcement("Limón", 20, Date(), Date(), "Santiago de Compostela", R.drawable.img_gato1),
                Announcement("Ron", 16, Date(), Date(), "Santiago de Compostela", R.drawable.img_perro1),
                Announcement("Cuba", 18, Date(), Date(), "Milladoiro", R.drawable.img_gato2),
                Announcement("Michi", 27, Date(), Date(), "Bertamirans", R.drawable.img_perro2),
                Announcement("Leon", 30, Date(), Date(), "Santiago de Compostela", R.drawable.img_perro3),
                Announcement("Chile", 20, Date(), Date(), "Milladoiro", R.drawable.img_perro4),
                Announcement("Cuba", 18, Date(), Date(), "Milladoiro", R.drawable.img_gato2),
                Announcement("Michi", 27, Date(), Date(), "Bertamirans", R.drawable.img_perro2),
                Announcement("Leon", 30, Date(), Date(), "Santiago de Compostela", R.drawable.img_perro3),
                Announcement("Chile", 20, Date(), Date(), "Milladoiro", R.drawable.img_perro4),
                Announcement("Trusqui", 22, Date(), Date(), "Santiago de Compostela", R.drawable.img_gato1)
            )
        }

        override fun getItemCount(): Int {
            return announcements.size;
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return AnnouncementViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.row_announcements, parent, false)
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when (holder) {
                is AnnouncementViewHolder -> {
                    holder.announcement_bg.animation = AnimationUtils.loadAnimation(
                        mContext,
                        R.anim.item_animation_fall_down
                    )
                    holder.bind(announcements.get(position), mContext)
                }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        announcementsViewModel =
            ViewModelProviders.of(this).get(AnnouncementsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_announcements, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        announcements_list.layoutManager = LinearLayoutManager(context)
        listAdapter = AnnouncementsAdapter(requireContext())
        announcements_list.adapter = listAdapter
    }


}