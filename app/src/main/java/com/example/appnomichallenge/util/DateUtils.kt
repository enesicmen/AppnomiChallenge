package com.example.appnomichallenge.util

import java.text.ParseException
import java.text.SimpleDateFormat

object DateUtils {

    fun getDateFormat(date: String): String?{
        try {
            val OLD_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
            val NEW_FORMAT = "dd.MM.yyyy"
            val newDateString: String
            val sdf = SimpleDateFormat(OLD_FORMAT)
            val newDateFormat = sdf.parse(date)
            sdf.applyPattern(NEW_FORMAT)
            newDateString = sdf.format(newDateFormat)
            return newDateString
        }catch (e: ParseException){
            e.printStackTrace()
        }
        return date
    }
}