package pokemontcg.features.pokemon.data.entities

import com.google.gson.annotations.SerializedName

data class GenerationDTO(
    @SerializedName("main_region")
    val region: GeneralEntry,
    @SerializedName("name")
    val name: String,
    @SerializedName("version_groups")
    val versionGroups: List<GeneralEntry>
)