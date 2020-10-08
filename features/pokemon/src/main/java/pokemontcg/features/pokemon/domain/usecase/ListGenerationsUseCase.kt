package pokemontcg.features.pokemon.domain.usecase

import pokemontcg.features.pokemon.data.PokemonRepository
import pokemontcg.features.pokemon.data.entities.GenerationDTO
import pokemontcg.features.pokemon.domain.model.Generation
import pokemontcg.libraries.common.UseCase

internal class ListGenerationsUseCase(private val repo: PokemonRepository) : UseCase<Unit, List<Generation>> {

    override suspend fun execute(param: Unit): List<Generation> {
        val generationEntries = repo.listGenerations()
        val generationsResponse = generationEntries.map {
            repo.getGenerationDetails(it.name)
        }
        return generationsResponse.map { it.mapTo() }
    }
}