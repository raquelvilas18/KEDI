package com.example.kedi

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.ViewPager
import com.fxn.ariana.ArianaBackgroundListener
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_lista.*
class lista : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager
    private lateinit var mPagerViewAdapter: PagerViewAdapter

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var annoucementsBtn:ImageButton
    private lateinit var petfundingBtn:ImageButton
    private lateinit var createBtn:ImageButton
    private lateinit var profileBtn:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }


        mViewPager = findViewById(R.id.mViewPager)
        mPagerViewAdapter = PagerViewAdapter(supportFragmentManager)
        mViewPager.adapter = mPagerViewAdapter
        mViewPager.currentItem = 0
        menu_bottom.setItemSelected(R.id.navigation_announcements)
        chatIcon.setOnClickListener{
            val intent: Intent = Intent(this, Navigation::class.java)
            startActivity(intent)
        }

        menu_bottom.setOnItemSelectedListener { id ->
            when(id){
                R.id.navigation_announcements -> mViewPager.currentItem = 0
                R.id.navigation_petfunding -> mViewPager.currentItem = 1
                R.id.navigation_create -> mViewPager.currentItem = 2
                R.id.navigation_profile -> mViewPager.currentItem = 3

            }
        }

        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                changeIcon(position)
                when(position){
                    0 ->menu_bottom.setItemSelected(R.id.navigation_announcements)
                    1 ->menu_bottom.setItemSelected(R.id.navigation_petfunding)
                    2 ->menu_bottom.setItemSelected(R.id.navigation_create)
                    3 ->menu_bottom.setItemSelected(R.id.navigation_profile)

                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun changeIcon(position: Int){
        when(position){
            0->{
                //container.setBackgroundColor(ContextCompat.getColor(this, R.color.announcements_bg))
                ImageViewCompat.setImageTintList(icon, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.announcements)));
                //ImageViewCompat.setImageTintList(menuIcon, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.announcements)));
                //ImageViewCompat.setImageTintList(chatIcon, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.announcements)));
            }
            1->{
                //container.setBackgroundColor(ContextCompat.getColor(this, R.color.petfunding_bg))
                ImageViewCompat.setImageTintList(icon, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.petfunding)));
                //ImageViewCompat.setImageTintList(menuIcon, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.petfunding)));
                //ImageViewCompat.setImageTintList(chatIcon, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.petfunding)));
            }
            2->{
                //container.setBackgroundColor(ContextCompat.getColor(this, R.color.create_bg))
                ImageViewCompat.setImageTintList(icon, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.create)));
                //ImageViewCompat.setImageTintList(menuIcon, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.create)));
                //ImageViewCompat.setImageTintList(chatIcon, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.create)));
            }
            3->{
                //container.setBackgroundColor(ContextCompat.getColor(this, R.color.profile_bg))
                ImageViewCompat.setImageTintList(icon, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.profile)));
                //ImageViewCompat.setImageTintList(menuIcon, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.profile)));
                //ImageViewCompat.setImageTintList(chatIcon, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.profile)));
            }
        }
    }
}