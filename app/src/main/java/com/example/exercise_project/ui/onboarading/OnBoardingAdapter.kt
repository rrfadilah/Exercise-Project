package com.example.exercise_project.ui.onboarading

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.exercise_project.R

class OnBoardingAdapter(private var list: List<Int>, private var ctx: Context) : PagerAdapter() {

    private lateinit var imgs: List<Int>
    private lateinit var layoutInflater: LayoutInflater
    lateinit var context: Context
    override fun getCount(): Int {
        return list.size

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        layoutInflater = LayoutInflater.from(ctx)
        val view = layoutInflater.inflate(R.layout.on_boarding_image_item, container, false)
        val img = view.findViewById<ImageView>(R.id.simpleimg)
        img.setImageResource(list[position])
        container.addView(view, 0)
        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}