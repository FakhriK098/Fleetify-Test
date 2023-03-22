package id.fakhri_khairi.data.usecase.implement

import id.fakhri_khairi.data.repo.contract.VehicleRepository
import id.fakhri_khairi.data.usecase.BaseUseCase
import id.fakhri_khairi.data.usecase.contract.GetAllReport
import id.fakhri_khairi.domain.Report
import id.fakhri_khairi.domain.Result
import javax.inject.Inject

internal class GetAllReportImpl @Inject constructor(
    private val repository: VehicleRepository
) : BaseUseCase<List<Report>>(), GetAllReport {
    override suspend fun execute(): Result<List<Report>> = getSuspendResult {
        repository.getAllReport()
    }
}