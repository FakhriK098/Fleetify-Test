package id.fakhri_khairi.data.usecase.contract

import id.fakhri_khairi.domain.Report
import id.fakhri_khairi.domain.Vehicle
import java.io.File

interface GetAllVehicle {
    suspend fun execute() : id.fakhri_khairi.domain.Result<List<Vehicle>>
}

interface GetAllReport {
    suspend fun execute() : id.fakhri_khairi.domain.Result<List<Report>>
}

interface CreateReport {
    suspend fun execute(vehicleId: String, note: String, photoFile: File) : id.fakhri_khairi.domain.Result<Boolean>
}