package pokemontcg.features.pokemon.data

import pokemontcg.features.pokemon.data.entities.GeneralEntry
import pokemontcg.features.pokemon.data.entities.GenerationDTO

internal interface PokemonRepository {
    suspend fun listGenerations() : List<GeneralEntry>
    suspend fun getGenerationDetails(generationName: String) : GenerationDTO
}