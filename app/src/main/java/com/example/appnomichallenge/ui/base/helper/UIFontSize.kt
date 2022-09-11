package com.example.appnomichallenge.ui.base.helper

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object UIFontSize {

    private var mContext: Context? = null

    fun init(context: Context) {
        mContext = context
        setFontSizees()
    }

    var FONT_SIZE_13 = 0f
    var FONT_SIZE_11 = 0f
    var FONT_SIZE_15 = 0f
    var FONT_SIZE_18 = 0f

    private fun setFontSizees(){
        FONT_SIZE_13 = fontSize(13)
        FONT_SIZE_15 = fontSize(15)
        FONT_SIZE_18 = fontSize(18)
    }
    private fun fontSize(fontSize: Int):Float {
        val dm = mContext!!.resources.displayMetrics
        val screeWidth = dm.widthPixels / dm.density
        return (fontSize / 414.0 * screeWidth).toFloat()
    }
}