package pokemontcg.features.cards.usecase

import pokemontcg.features.cards.data.CardRepository
import pokemontcg.features.cards.model.Card
import pokemontcg.libraries.common.UseCase

internal class ListCardsUseCase(private val repo: CardRepository) : UseCase<Unit?, List<Card>> {

    override suspend fun execute(param: Unit?): List<Card> {
        return repo.listCards()
    }
}