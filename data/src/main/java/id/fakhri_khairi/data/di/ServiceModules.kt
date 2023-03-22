package id.fakhri_khairi.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.fakhri_khairi.data.service.Vehicle
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModules {

    @Singleton
    @Provides
    fun provideVehicleService(
        @Named(DataModules.NAMED_RETROFIT) retrofit: Retrofit
    ) : Vehicle {
        return retrofit.create(Vehicle::class.java)
    }
}