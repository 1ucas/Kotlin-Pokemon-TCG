package pokemontcg.features.cards.ui.main

import CardsApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pokemontcg.features.cards.model.Card
import pokemontcg.features.cards.usecase.ListCardsUseCase
import pokemontcg.libraries.ui_components.BaseViewModel

internal class CardMainViewModel(private val useCase: ListCardsUseCase) : BaseViewModel() {

    private val _cardLiveData = MutableLiveData<List<Card>>()
    var cards: LiveData<List<Card>> = _cardLiveData

    var isInitialized = false

    fun init() {
        doAsyncWork {
            _cardLiveData.value = useCase.execute(null)
            marcarIniciado()
        }
    }

    fun marcarIniciado() {
        isInitialized = true
    }

}