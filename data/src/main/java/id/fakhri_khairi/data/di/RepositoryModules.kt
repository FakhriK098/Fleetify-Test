package id.fakhri_khairi.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.fakhri_khairi.data.mapper.ReportMapper
import id.fakhri_khairi.data.mapper.VehicleMapper
import id.fakhri_khairi.data.repo.contract.VehicleRepository
import id.fakhri_khairi.data.repo.implement.VehicleRepositoryImpl
import id.fakhri_khairi.data.service.Vehicle

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModules {

    @Provides
    fun provideVehicleRepository(
        vehicleMapper: VehicleMapper,
        reportMapper: ReportMapper,
        service: Vehicle
    ) : VehicleRepository {
        return VehicleRepositoryImpl(
            vehicleMapper = vehicleMapper,
            reportMapper = reportMapper,
            service = service
        )
    }
}