package id.fakhri_khairi.data.model.responses

import com.squareup.moshi.Json

data class VehicleResponse (
    @Json(name = "vehicleId")
    val vehicleId: String,

    @Json(name = "licenseNumber")
    val licenseNumber: String,

    @Json(name = "type")
    val type: String
)