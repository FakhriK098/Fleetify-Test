package id.fakhri_khairi.data.usecase.implement

import id.fakhri_khairi.data.repo.contract.VehicleRepository
import id.fakhri_khairi.data.usecase.BaseUseCase
import id.fakhri_khairi.data.usecase.contract.GetAllVehicle
import id.fakhri_khairi.domain.Result
import id.fakhri_khairi.domain.Vehicle
import javax.inject.Inject

internal class GetAllVehicleImpl @Inject constructor(
    private val repository: VehicleRepository
) : BaseUseCase<List<Vehicle>>(), GetAllVehicle {
    override suspend fun execute(): Result<List<Vehicle>> = getSuspendResult {
        repository.getAllVehicle()
    }
}