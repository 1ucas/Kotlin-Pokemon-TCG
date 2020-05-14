package pokemontcg.libraries.ui_components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open abstract class BaseViewModel : ViewModel() {

    val showError = SingleLiveEvent<String>()
    val state = SingleLiveEvent<State>()

    enum class State {
        Default,
        Loading
    }

    fun doAsyncWork(work: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                state.call(State.Loading)
                work()
            } catch (e: Exception) {
                showError.call(e.message)
            }
            state.call(State.Default)
        }
    }

}