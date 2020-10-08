package pokemontcg.features.pokemon.data.entities

import com.google.gson.annotations.SerializedName

data class GeneralEntry(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)