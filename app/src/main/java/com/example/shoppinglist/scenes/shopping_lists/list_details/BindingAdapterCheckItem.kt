package com.example.shoppinglist.scenes.shopping_lists.list_details

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import com.example.shoppinglist.R

@BindingAdapter("isItemChecked")
fun ImageView.setItemCheck(isChecked: Int?) {
    isChecked.let {
        val checkColor = ContextCompat.getColor(context, R.color.purple_200)
        when (isChecked) {
            1 -> ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(checkColor))
            else -> ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(Color.WHITE))
        }
    }
}