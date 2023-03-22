package id.fakhri_khairi.core.presentation.form

import id.fakhri_khairi.data.usecase.contract.CreateReport
import id.fakhri_khairi.data.usecase.contract.GetAllVehicle
import javax.inject.Inject

class CreateReportUseCase @Inject constructor(
    val getAllVehicle: GetAllVehicle,
    val createReport: CreateReport
)