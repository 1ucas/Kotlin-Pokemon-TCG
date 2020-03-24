package pokemontcg.features.cards.data.network

import CardsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pokemontcg.features.cards.data.CardRepository
import pokemontcg.features.cards.model.Card
import pokemontcg.libraries.network.ApiClientBuilder
import pokemontcg.libraries.network.RequestManager

internal class CardsNetworkRepository : CardRepository {

    override suspend fun listCards(): List<Card> {

        return withContext(Dispatchers.IO) {
            val api = ApiClientBuilder.createServiceApi(CardsApi::class.java)

            val apiResponse = RequestManager.requestFromApi { api.listCards() }

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