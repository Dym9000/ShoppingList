package com.example.shoppinglist.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    fun formatDate(date: Date): String{
        val newDateFormat = SimpleDateFormat("dd  MMM  yyyy", Locale.getDefault() )
        return newDateFormat.format(date)
    }

}