package pokemontcg.features.pokemon.data.entities

import com.google.gson.annotations.SerializedName
import pokemontcg.features.pokemon.domain.model.Generation
import pokemontcg.libraries.common.MapTo

data class GenerationDTO(
    @SerializedName("main_region")
    val region: GeneralEntry,
    @SerializedName("name")
    val name: String,
    @SerializedName("version_groups")
    val versionGroups: List<GeneralEntry>
) : MapTo<Generation> {

    override fun mapTo() = Generation(name = this.name, versions = this.versionGroups.map { version -> version.name })

}