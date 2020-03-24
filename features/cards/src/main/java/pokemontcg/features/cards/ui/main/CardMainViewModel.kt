package pokemontcg.features.cards.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pokemontcg.features.cards.model.Card
import pokemontcg.features.cards.usecase.ListCardsUseCase

internal class CardMainViewModel : ViewModel() {

    private val _cardLiveData = MutableLiveData<List<Card>>()
    var cards: LiveData<List<Card>> = _cardLiveData

    init {
        viewModelScope.launch {
            _cardLiveData.value = ListCardsUseCase().execute(null)
        }
    }

    private suspend fun listCards() : List<Card> {
        return listOf()
    }

}