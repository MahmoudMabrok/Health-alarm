package com.example.healthalarm.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.healthalarm.models.ViewpagerModel

class PhotosListAdapter(
    private var photoslist: List<ViewpagerModel>,
    private val context: Context
) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val photo = ImageView(context)
        Glide.with(context).load(photoslist[position].photo).into(photo)
        container.addView(photo, 0)
        return photo
    }

    override fun getCount(): Int {
        return photoslist.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}