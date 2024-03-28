package viewmodels

import io.github.aakira.napier.Napier
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class HomeViewModel : ViewModel() {
    private val _actualDate: MutableStateFlow<LocalDate> =
        MutableStateFlow(
            Clock.System.todayIn(TimeZone.currentSystemDefault())
        )

    val actualDate: StateFlow<LocalDate> = _actualDate.asStateFlow()

    private var _timerJob: Job? = null

    fun startTimer() {
        Napier.d("Started timer")
        _timerJob = viewModelScope.launch {
            while (true) {
                delay(5000)
                _actualDate.value = Clock.System.todayIn(TimeZone.currentSystemDefault())
            }
        }
    }

    override fun onCleared() {
        Napier.d("Cleared HomeViewModel")
        super.onCleared()
        _timerJob?.cancel()
    }
}