package pokemontcg.features.cards.data

import com.google.gson.annotations.SerializedName
import pokemontcg.features.cards.model.Card
import pokemontcg.libraries.common.MapTo

internal class CardDTO(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("imageUrl")
    val imageUrl: String
) : MapTo<Card> {
    override fun mapTo(): Card {
        return Card(id, name, imageUrl)
    }
}