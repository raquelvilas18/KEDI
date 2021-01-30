package com.example.kedi.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.kedi.R
import com.example.kedi.ui.OpinionsActivity
import com.example.kedi.ui.PetProfileActivity
import kotlinx.android.synthetic.main.fragment_profile.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val MIN_SCALE = 0.85f
private const val MIN_ALPHA = 0.5f


class ProfileFragment : Fragment() {

    private var clicked_more_button = false
    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.rotate_open_animation
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.rotate_close_animation
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.from_bottom_animation
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.to_bottom_animation
        )
    }

    class ZoomOutPageTransformer : ViewPager2.PageTransformer {
        //class used to show the profile images and change with zoom effect (used when the profile
        //photo is clicked)

        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                val pageHeight = height
                when {
                    position < -1 -> { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        alpha = 0f
                    }
                    position <= 1 -> { // [-1,1]
                        // Modify the default slide transition to shrink the page as well
                        val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                        val vertMargin = pageHeight * (1 - scaleFactor) / 2
                        val horzMargin = pageWidth * (1 - scaleFactor) / 2
                        translationX = if (position < 0) {
                            horzMargin - vertMargin / 2
                        } else {
                            horzMargin + vertMargin / 2
                        }

                        // Scale the page down (between MIN_SCALE and 1)
                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        // Fade the page relative to its size.
                        alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        alpha = 0f
                    }
                }
            }
        }
    }


    class ViewPagerAdapter(
        private val sliderItems: List<Int>
    ) :
        RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

        inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val itemImage: ImageView = itemView.findViewById(R.id.imageSlide)
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewPagerAdapter.Pager2ViewHolder {
            return Pager2ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_image_slider,
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {
            holder.itemImage.setImageResource(sliderItems[position])
        }

        override fun getItemCount(): Int {
            return sliderItems.size
        }

    }

    lateinit var viewPager2: ViewPager2

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onActivityCreated(state: Bundle?) {
        super.onActivityCreated(state)

        configureListeners()
        configureFloatingButtons()

        configureSlider()

        configureImageGallery()
    }

    private fun configureSlider(){
        var slideModel = getProfileSliderImages()
        slider.setImageList(slideModel, ScaleTypes.CENTER_CROP)
    }

    private fun getProfileSliderImages(): ArrayList<SlideModel> {
        //todo: call real backend
        return arrayListOf<SlideModel>( SlideModel(R.drawable.img_slider1, ""),
            SlideModel(R.drawable.img_slider2,""),
            SlideModel(R.drawable.img_slider3, ""),
            SlideModel(R.drawable.img_slider4,""))
    }

    private fun configureListeners() {
        opinions?.setOnClickListener {
            val intent = Intent(this.context, OpinionsActivity::class.java)
            startActivity(intent)
        }

        //Go to pet profile by clicking on the pet image
        petImage?.setOnClickListener {
            val intent = Intent(this.context, PetProfileActivity::class.java)
            startActivity(intent)
        }
        //Show the image gallery by clicking on the porofile photo
        imageProfile?.setOnClickListener {
            imageGallery.visibility = View.VISIBLE
            imageGallery.setAlpha(0.0f);

            // Start the animation
            imageGallery.animate()
                .alpha(1.0f)
                .setDuration(400)
                .setListener(null);
        }
    }

    private fun configureFloatingButtons(){
        more_button.setOnClickListener{
            onMoreButtonClicked()
        }
        edit_button.setOnClickListener{
            //todo: create edit option
        }
        configure_button.setOnClickListener{
            //todo: create edit option
        }
    }

    private fun onMoreButtonClicked(){
        setVisibility(clicked_more_button)
        setAnimation(clicked_more_button)
        clicked_more_button = !clicked_more_button

    }
    private fun setVisibility(clicked: Boolean){
        if(!clicked){
            edit_button.visibility = View.VISIBLE
            configure_button.visibility = View.VISIBLE
        }else{
            edit_button.visibility = View.INVISIBLE
            configure_button.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean){
        if(!clicked){
            edit_button.startAnimation(fromBottom)
            configure_button.startAnimation(fromBottom)
            more_button.startAnimation(rotateOpen)
        }else{
            edit_button.startAnimation(toBottom)
            configure_button.startAnimation(toBottom)
            more_button.startAnimation(rotateClose)
        }
    }

    private fun getProfileImages(): ArrayList<Int> {
        //todo: get real data from img_back
        //mocked data
        return arrayListOf<Int>(
            R.drawable.img_persona,
            R.drawable.img_perfil4,
            R.drawable.img_perfil1,
            R.drawable.img_perfil2,
            R.drawable.img_perfil3
        )
    }

    private fun configureImageGallery() {
        //Configure Image Gallery (viewPager)
        viewPager2 = viewPagerImageSlider
        var sliderItems = getProfileImages()

        viewPager2.adapter = ViewPagerAdapter(sliderItems)
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 10
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        viewPager2.setOnClickListener {
            // Start the animation
            imageGallery.animate()
                .alpha(0.0f)
                .setDuration(400)
            imageGallery.visibility = View.INVISIBLE
        }

        //Custom transition between images
        var transf = ZoomOutPageTransformer()
        viewPager2.setPageTransformer(transf)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}