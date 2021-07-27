package com.example.shoppinglist.persistence.shopping_list.util

import androidx.room.TypeConverter
import java.util.*

class CalendarConverter {

    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(timeInMilisec: Long): Calendar {
        return Calendar.getInstance().apply { timeInMillis = timeInMilisec }
    }

}