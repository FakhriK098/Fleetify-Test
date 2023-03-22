package id.fakhri_khairi.data.model.responses

import com.squareup.moshi.Json

data class CreateReportResponse(
    @Json(name = "status")
    val status : Boolean,

    @Json(name = "message")
    val message : String
)
