package id.fakhri_khairi.domain

data class Report(
    val reportId: String,
    val vehicleId: String,
    val vehicleLicenseNumber: String,
    val vehicleName: String,
    val note: String,
    val photo: String,
    val reportStatus: String,
    val createdAt: String,
    val createdBy: String
)
