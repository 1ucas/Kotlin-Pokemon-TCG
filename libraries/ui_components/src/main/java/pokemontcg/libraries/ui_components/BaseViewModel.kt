package pokemontcg.libraries.ui_components

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

open abstract class BaseViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _showError = SingleLiveEvent<String>()
    val showError: LiveData<String> =_showError

    fun doAsyncWork(work: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                work()
            } catch (e: Exception) {
                _showError.call(e.message)
            }
            _isLoading.value = false
        }
    }

}