package com.example.appnomichallenge.ui.common.ext

import android.text.Html
import android.widget.TextView

fun TextView.setHtmlText(html: String?){
    text = Html.fromHtml(Html.fromHtml(html).toString())
}