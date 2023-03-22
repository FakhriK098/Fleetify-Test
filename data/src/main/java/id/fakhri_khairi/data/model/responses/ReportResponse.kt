package id.fakhri_khairi.data.model.responses

import com.squareup.moshi.Json

data class ReportResponse(
    @Json(name = "reportId")
    val reportId: String,

    @Json(name = "vehicleId")
    val vehicleId: String,

    @Json(name = "vehicleLicenseNumber")
    val vehicleLicenseNumber: String,

    @Json(name = "vehicleName")
    val vehicleName: String,

    @Json(name = "note")
    val note: String,

    @Json(name = "photo")
    val photo: String,

    @Json(name = "reportStatus")
    val reportStatus: String,

    @Json(name = "createdAt")
    val createdAt: String,

    @Json(name = "createdBy")
    val createdBy: String
)
