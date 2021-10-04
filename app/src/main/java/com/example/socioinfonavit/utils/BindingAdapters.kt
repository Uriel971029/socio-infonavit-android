package com.example.socioinfonavit.utils

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("visible")
fun View.bindVisivility(visible : Boolean?){
    visibility = if(visible == true) View.VISIBLE else View.GONE
}

@BindingAdapter("url")
fun ImageView.bindLogo(url : String?){
    Glide.with(context).load(url).into(this)
}

@BindingAdapter("backgroundString")
fun View.bindBackground(hexaColor : String?){

    val color: Int = try {
        Color.parseColor(hexaColor)
    } catch (e: Exception) {
        Color.parseColor("#$background")
    }
    setBackgroundColor(color)
}
