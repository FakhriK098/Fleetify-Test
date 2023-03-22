package id.fakhri_khairi.core.presentation.home

import id.fakhri_khairi.data.usecase.contract.GetAllReport
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    val getAllReport: GetAllReport
)