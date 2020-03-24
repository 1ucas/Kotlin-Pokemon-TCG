package pokemontcg.features.cards.ui.main

import CardsApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.koin.java.KoinJavaComponent.inject
import pokemontcg.features.cards.data.network.CardsNetworkRepository
import pokemontcg.features.cards.model.Card
import pokemontcg.features.cards.usecase.ListCardsUseCase
import pokemontcg.libraries.network.ApiClientBuilder
import pokemontcg.libraries.ui_components.BaseViewModel

internal class CardMainViewModel : BaseViewModel() {

    private val _cardLiveData = MutableLiveData<List<Card>>()
    var cards: LiveData<List<Card>> = _cardLiveData

    private val useCase : ListCardsUseCase by inject(clazz = ListCardsUseCase::class.java)

    init {
        doAsyncWork {
            _cardLiveData.value = useCase.execute(null)
        }
    }

}