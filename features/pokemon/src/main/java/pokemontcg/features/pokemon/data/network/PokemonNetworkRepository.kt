package pokemontcg.features.pokemon.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pokemontcg.features.pokemon.data.PokemonRepository
import pokemontcg.features.pokemon.data.entities.GeneralEntry
import pokemontcg.features.pokemon.data.entities.GenerationDTO
import pokemontcg.libraries.network.RequestManager
import pokemontcg.libraries.network.exceptions.GeneralNetworkException

internal class PokemonNetworkRepository(private val api: PokemonApi) : PokemonRepository {

    override suspend fun listGenerations(): List<GeneralEntry> {
        return withContext(Dispatchers.IO) {
            val apiResponse = RequestManager.requestFromApi { api.listGenerations() }
            apiResponse?.let { it.results } ?: emptyList()
        }
    }

    override suspend fun getGenerationDetails(generationName: String): GenerationDTO {
        return withContext(Dispatchers.IO) {
            val apiResponse = RequestManager.requestFromApi { api.getGenerationDetails(name = generationName) }
            apiResponse?.let { it } ?: throw GeneralNetworkException(message = "Dados não encontratos.")
        }
    }
}