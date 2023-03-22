package id.fakhri_khairi.data.usecase.implement

import id.fakhri_khairi.data.repo.contract.VehicleRepository
import id.fakhri_khairi.data.usecase.BaseUseCase
import id.fakhri_khairi.data.usecase.contract.CreateReport
import id.fakhri_khairi.domain.Result
import java.io.File
import javax.inject.Inject

internal class CreateReportImpl @Inject constructor(
    private val repository: VehicleRepository
) : BaseUseCase<Boolean>(), CreateReport {
    override suspend fun execute(
        vehicleId: String,
        note: String,
        photoFile: File
    ): Result<Boolean> = getSuspendResult {
        repository.createReport(vehicleId, note, photoFile)
    }
}