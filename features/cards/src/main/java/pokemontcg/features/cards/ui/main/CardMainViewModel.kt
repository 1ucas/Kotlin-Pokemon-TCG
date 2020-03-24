package pokemontcg.features.cards.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pokemontcg.features.cards.model.Card
import pokemontcg.features.cards.usecase.ListCardsUseCase
import pokemontcg.libraries.ui_components.BaseViewModel

internal class CardMainViewModel : BaseViewModel() {

    private val _cardLiveData = MutableLiveData<List<Card>>()
    var cards: LiveData<List<Card>> = _cardLiveData

    init {
        doAsyncWork {
            _cardLiveData.value = ListCardsUseCase().execute(null)
        }
    }

}