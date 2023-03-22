package id.fakhri_khairi.data.repo.implement

import id.fakhri_khairi.data.BuildConfig
import id.fakhri_khairi.data.mapper.ReportMapper
import id.fakhri_khairi.data.mapper.VehicleMapper
import id.fakhri_khairi.data.model.request.CreateReportRequest
import id.fakhri_khairi.data.repo.BaseRepository
import id.fakhri_khairi.data.repo.contract.VehicleRepository
import id.fakhri_khairi.data.service.Vehicle
import id.fakhri_khairi.domain.Report
import java.io.File
import javax.inject.Inject

internal class VehicleRepositoryImpl @Inject constructor(
    private val vehicleMapper: VehicleMapper,
    private val reportMapper: ReportMapper,
    private val service: Vehicle
) : BaseRepository(), VehicleRepository {
    override suspend fun getAllVehicle(): List<id.fakhri_khairi.domain.Vehicle> {
        return getData {
            val result = service.getAllVehicle()

            result.map { vehicleMapper.convert(it) }
        }
    }

    override suspend fun getAllReport(): List<Report> {
        return getData {
            val userId = BuildConfig.USER_ID
            val result = service.getAllReport(userId)

            result.map { reportMapper.convert(it) }
        }
    }

    override suspend fun createReport(vehicleId: String, note: String, photoFile: File): Boolean {
        return getData {
            val userId = BuildConfig.USER_ID
            val requestCreateReport = CreateReportRequest(vehicleId, note, userId, photoFile)
            val result = service.createReport(requestCreateReport.toRequestBody())

            result.status
        }
    }
}