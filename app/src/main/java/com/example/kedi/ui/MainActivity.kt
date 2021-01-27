package com.example.kedi.ui

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.widget.ImageViewCompat
import androidx.navigation.ui.AppBarConfiguration
import androidx.viewpager.widget.ViewPager
import com.example.kedi.R
import kotlinx.android.synthetic.main.activity_lista.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager
    private lateinit var mPagerViewAdapter: PagerViewAdapter


    override fun onBackPressed() {
        //Override to close the drawer menu when it is open
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        configureDrawerMenu()
        configureListeners()
        configureViewPager()
    }

    private fun configureListeners(){
        chatIcon.setOnClickListener{
            val intent: Intent = Intent(this, ChatsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configureViewPager(){
        //Configure view pager to work with slide and menu
        mViewPager = findViewById(R.id.mViewPager)
        mPagerViewAdapter = PagerViewAdapter(supportFragmentManager)
        mViewPager.adapter = mPagerViewAdapter
        mViewPager.currentItem = 0

        menu_bottom.setItemSelected(R.id.navigation_announcements)
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
                when (position) {
                    0 -> menu_bottom.setItemSelected(R.id.navigation_announcements)
                    1 -> menu_bottom.setItemSelected(R.id.navigation_petfunding)
                    2 -> menu_bottom.setItemSelected(R.id.navigation_create)
                    3 -> menu_bottom.setItemSelected(R.id.navigation_profile)
                }
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun configureDrawerMenu(){
        menuIcon.setOnClickListener{
            drawer_layout.openDrawer(GravityCompat.START);
        }
    }

    private fun changeIcon(position: Int){
        //Changes the kedi icon color
        when(position){
            0 -> {
                ImageViewCompat.setImageTintList(
                    icon, ColorStateList.valueOf(
                        ContextCompat.getColor(
                            this,
                            R.color.announcements
                        )
                    )
                )
            }
            1 -> {
                ImageViewCompat.setImageTintList(
                    icon, ColorStateList.valueOf(
                        ContextCompat.getColor(
                            this,
                            R.color.petfunding
                        )
                    )
                )
            }
            2 -> {
                ImageViewCompat.setImageTintList(
                    icon, ColorStateList.valueOf(
                        ContextCompat.getColor(
                            this,
                            R.color.create
                        )
                    )
                )
            }
            3 -> {
                ImageViewCompat.setImageTintList(
                    icon, ColorStateList.valueOf(
                        ContextCompat.getColor(
                            this,
                            R.color.profile
                        )
                    )
                )
            }
        }
    }
}