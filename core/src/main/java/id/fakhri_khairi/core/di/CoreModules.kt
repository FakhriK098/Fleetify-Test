package id.fakhri_khairi.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.newFixedThreadPoolContext
import pl.aprilapps.easyphotopicker.ChooserType
import pl.aprilapps.easyphotopicker.EasyImage
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@ObsoleteCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object CoreModules {

    @Singleton
    @Provides
    fun provideCoroutinesContext(): CoroutineContext {
        val numberOfCores = Runtime.getRuntime().availableProcessors()
        return newFixedThreadPoolContext(numberOfCores, THREAD_NAME) + SupervisorJob()
    }
    @Singleton
    @Provides
    fun provideEasyImage(@ApplicationContext context: Context): EasyImage {
        return EasyImage.Builder(context)
            .setChooserType(ChooserType.CAMERA_AND_GALLERY)
            .build()
    }

    private const val THREAD_NAME = "adaptive_thread"
}