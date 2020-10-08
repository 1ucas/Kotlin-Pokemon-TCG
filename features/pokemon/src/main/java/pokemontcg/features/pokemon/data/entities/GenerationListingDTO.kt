package pokemontcg.features.pokemon.data.entities

import com.google.gson.annotations.SerializedName

data class GenerationListingDTO(
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val results: List<GeneralEntry>
)