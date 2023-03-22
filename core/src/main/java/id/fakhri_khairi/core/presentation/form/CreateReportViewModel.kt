package id.fakhri_khairi.core.presentation.form

import dagger.hilt.android.lifecycle.HiltViewModel
import id.fakhri_khairi.core.base.BaseViewModel
import id.fakhri_khairi.domain.Result
import id.fakhri_khairi.domain.Vehicle
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

sealed class CreateReportState {
    object Idle : CreateReportState()
}

sealed class CreateReportEvent {
    data class Success(val data : List<Vehicle>) : CreateReportEvent()
    object SuccessCreateReport : CreateReportEvent()
    data class Error(val message : String) : CreateReportEvent()
    object Loading : CreateReportEvent()
}

@HiltViewModel
class CreateReportViewModel @Inject constructor(
    coroutineContext: CoroutineContext,
    private val useCase: CreateReportUseCase
) : BaseViewModel<CreateReportState, CreateReportEvent>(
    CreateReportState.Idle,
    coroutineContext
) {

    fun getAllVehicle(){
        adaptiveScope.launch {
            sendEvent(CreateReportEvent.Loading)
            when(val result = useCase.getAllVehicle.execute()) {
                is Result.Error -> sendEvent(CreateReportEvent.Error(result.message))
                is Result.Success -> sendEvent(CreateReportEvent.Success(result.data))
            }
        }
    }

    fun createRecord(vehicleId: String, note: String, photoFile: File) {
        adaptiveScope.launch {
            sendEvent(CreateReportEvent.Loading)
            when(val result = useCase.createReport.execute(vehicleId, note, photoFile)) {
                is Result.Error -> sendEvent(CreateReportEvent.Error(result.message))
                is Result.Success -> sendEvent(CreateReportEvent.SuccessCreateReport)
            }
        }
    }
}