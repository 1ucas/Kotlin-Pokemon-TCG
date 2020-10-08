package pokemontcg.features.pokemon.domain.usecase

import pokemontcg.features.pokemon.data.PokemonRepository
import pokemontcg.features.pokemon.data.entities.GenerationDTO
import pokemontcg.features.pokemon.domain.model.Generation
import pokemontcg.libraries.common.UseCase

internal class ListGenerationsUseCase(private val repo: PokemonRepository) : UseCase<Unit, List<Generation>> {

    override suspend fun execute(param: Unit): List<Generation> {
        val generationEntries = repo.listGenerations()
        val generationsResponse = mutableListOf<GenerationDTO>()
        for (generation in generationEntries) {
            val detailResponse = repo.getGenerationDetails(generation.name)
            generationsResponse.add(detailResponse)
        }
        return generationsResponse.map { dto -> Generation(name = dto.name, versions = dto.versionGroups.map { version -> version.name }) }
    }
}