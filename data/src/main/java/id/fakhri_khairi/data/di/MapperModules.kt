package id.fakhri_khairi.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.fakhri_khairi.data.mapper.ReportMapper
import id.fakhri_khairi.data.mapper.VehicleMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModules {

    @Singleton
    @Provides
    fun provideVehicleMapper(): VehicleMapper {
        return VehicleMapper()
    }

    @Singleton
    @Provides
    fun provideReportMapper(): ReportMapper {
        return ReportMapper()
    }
}