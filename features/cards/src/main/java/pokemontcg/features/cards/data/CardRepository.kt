package pokemontcg.features.cards.data

import pokemontcg.features.cards.model.Card

internal interface CardRepository {

    suspend fun listCards() : List<Card>
}