package pokemontcg.features.cards.usecase

import CardsApi
import kotlinx.coroutines.*
import pokemontcg.features.cards.model.Card
import pokemontcg.libraries.common.UseCase
import pokemontcg.libraries.network.ApiClientBuilder
import pokemontcg.libraries.network.RequestManager.requestFromApi

internal class ListCardsUseCase() : UseCase<Unit?, List<Card>> {

    override suspend fun execute(param: Unit?): List<Card> {
        return withContext(Dispatchers.IO) {
            val api = ApiClientBuilder.createServiceApi(CardsApi::class.java)

            val apiResponse = requestFromApi { api.listCards() }

            val cards = apiResponse?.cards?.map {
                Card(
                    id = it.id,
                    name = it.name,
                    imageURL = it.imageUrl
                )
            }
            cards ?: emptyList()
        }
    }
}