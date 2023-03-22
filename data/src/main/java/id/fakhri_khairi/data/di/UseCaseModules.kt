package id.fakhri_khairi.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.fakhri_khairi.data.repo.contract.VehicleRepository
import id.fakhri_khairi.data.usecase.contract.CreateReport
import id.fakhri_khairi.data.usecase.contract.GetAllReport
import id.fakhri_khairi.data.usecase.contract.GetAllVehicle
import id.fakhri_khairi.data.usecase.implement.CreateReportImpl
import id.fakhri_khairi.data.usecase.implement.GetAllReportImpl
import id.fakhri_khairi.data.usecase.implement.GetAllVehicleImpl

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModules {

    @Provides
    fun provideGetAllVehicle(repository: VehicleRepository): GetAllVehicle {
        return GetAllVehicleImpl(repository)
    }

    @Provides
    fun provideGetAllReport(repository: VehicleRepository): GetAllReport {
        return GetAllReportImpl(repository)
    }

    @Provides
    fun provideCreateReport(repository: VehicleRepository): CreateReport {
        return CreateReportImpl(repository)
    }
}