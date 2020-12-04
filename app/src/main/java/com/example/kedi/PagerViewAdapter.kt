package com.example.kedi

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.kedi.ui.announcements.AnnouncementsFragment;
import com.example.kedi.ui.create.CreateFragment;
import com.example.kedi.ui.petfunding.PetfundingFragment;
import com.example.kedi.ui.profile.ProfileFragment;

internal class PagerViewAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    var list = ArrayList<String>()


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                AnnouncementsFragment()
            }
            1 -> {
                PetfundingFragment()
            }
            2 -> {
                CreateFragment()
            }
            3 -> {
                ProfileFragment()
            }
            else -> AnnouncementsFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }

}

