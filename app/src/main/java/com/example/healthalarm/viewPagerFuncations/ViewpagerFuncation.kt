package com.example.healthalarm.viewPagerFuncations

import android.os.Handler
import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.PageTransformer
import java.util.*
import kotlin.math.abs

class ViewpagerFuncation {
    private val DELAY_MS: Long = 1000
    private val PERIOD_MS: Long = 4000
    private var currentPage = 0
    var timer: Timer? = null

    fun setviewpager(viewPager: ViewPager) {
        viewPager.setPageTransformer(true, ZoomOutPageTransformer())
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == 7) {
                currentPage = 0
            }
            viewPager.setCurrentItem(currentPage++, true)
        }
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, DELAY_MS, PERIOD_MS)
    }

    private class ZoomOutPageTransformer : PageTransformer {
        override fun transformPage(page: View, position: Float) {
            val pageWidth = page.width
            val pageHeight = page.height

            when {
                position < -1 -> {
                    page.alpha = 0f
                }
                position <= 1 -> {
                    val scaleFactor = Math.max(MIN_SCALE, 1 - abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2
                    if (position < 0) {
                        page.translationX = horzMargin - vertMargin / 2
                    } else {
                        page.translationX = -horzMargin + vertMargin / 2
                    }
                    page.scaleX = scaleFactor
                    page.scaleY = scaleFactor
                    page.alpha = MIN_ALPHA +
                            (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA)
                }
                else -> {
                    page.alpha = 0f
                }
            }
        }
    }

    companion object {
        private const val MIN_SCALE = 0.85f
        private const val MIN_ALPHA = 0.5f
    }
}