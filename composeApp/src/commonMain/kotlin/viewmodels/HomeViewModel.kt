package viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import moe.tlaster.precompose.viewmodel.ViewModel

class HomeViewModel : ViewModel() {
    private val _actualDate: MutableStateFlow<LocalDate> =
        MutableStateFlow(
            Clock.System.todayIn(TimeZone.currentSystemDefault())
        )

    val actualDate: StateFlow<LocalDate> = _actualDate.asStateFlow()
}