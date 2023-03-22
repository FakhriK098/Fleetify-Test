package id.fakhri_khairi.core.presentation.home

import dagger.hilt.android.lifecycle.HiltViewModel
import id.fakhri_khairi.core.base.BaseViewModel
import id.fakhri_khairi.domain.Report
import id.fakhri_khairi.domain.Result
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

sealed class HomeState {
    object Idle : HomeState()
}

sealed class HomeEvent {
    data class Success(val data: List<Report>) : HomeEvent()
    data class Error(val message: String) : HomeEvent()
    object Loading : HomeEvent()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    coroutineContext: CoroutineContext,
    private val useCase: HomeUseCase
) : BaseViewModel<HomeState, HomeEvent>(
    HomeState.Idle,
    coroutineContext
) {

    fun getAllReport() {
        adaptiveScope.launch {
            sendEvent(HomeEvent.Loading)
            when(val result = useCase.getAllReport.execute()) {
                is Result.Error -> sendEvent(HomeEvent.Error(result.message))
                is Result.Success -> sendEvent(HomeEvent.Success(result.data))
            }
        }
    }
}