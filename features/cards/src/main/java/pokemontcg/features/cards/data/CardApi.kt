import pokemontcg.features.cards.data.ListCardResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface CardsApi {
    @GET("cards")
    suspend fun listCards(): Response<ListCardResponse>
}