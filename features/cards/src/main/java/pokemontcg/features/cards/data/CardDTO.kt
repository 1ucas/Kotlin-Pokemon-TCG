package pokemontcg.features.cards.data

import com.google.gson.annotations.SerializedName

internal class CardDTO(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("imageUrl")
    val imageUrl: String
)