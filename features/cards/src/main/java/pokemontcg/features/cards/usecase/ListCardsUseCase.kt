package pokemontcg.features.cards.usecase

import pokemontcg.features.cards.model.Card
import pokemontcg.libraries.common.UseCase

internal class ListCardsUseCase : UseCase<Unit?, List<Card>> {

    override fun execute(param: Unit?): List<Card> {
        return listOf()
    }
}