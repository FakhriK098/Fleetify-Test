package id.fakhri_khairi.core.misc

import java.text.SimpleDateFormat
import java.util.*

fun String?.toFormattedDate(inputDateFormat: String, outputDateFormat: String): String {
    return try {
        val inputFormat = SimpleDateFormat(inputDateFormat)
        inputFormat.timeZone = TimeZone.getDefault()
        val outputFormat = SimpleDateFormat(outputDateFormat)
        outputFormat.timeZone = TimeZone.getDefault()
        val formattedDate = inputFormat.parse(this ?: "")
        outputFormat.format(formattedDate!!)
    } catch (ex: Exception) {
        ex.printStackTrace()
        ""
    }
}