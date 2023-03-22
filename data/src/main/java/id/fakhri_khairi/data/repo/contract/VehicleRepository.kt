package id.fakhri_khairi.data.repo.contract

import id.fakhri_khairi.domain.Report
import id.fakhri_khairi.domain.Vehicle
import java.io.File

interface VehicleRepository {
    suspend fun getAllVehicle(): List<Vehicle>

    suspend fun getAllReport(): List<Report>

    suspend fun createReport(vehicleId: String, note: String, photoFile: File): Boolean
}