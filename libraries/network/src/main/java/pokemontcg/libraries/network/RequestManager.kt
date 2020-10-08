package pokemontcg.libraries.network

import pokemontcg.libraries.network.exceptions.GeneralNetworkException
import pokemontcg.libraries.network.exceptions.ServerErrorException
import retrofit2.Response

object RequestManager {
    suspend fun <T> requestFromApi(
        request:(suspend () -> Response<T>)
    ): T? {
        try {
            val response = request()
            if(response.isSuccessful) {
                return response.body() // -> Já realiza o Parse da resposta
            }
            else {
                val message = response.message()
                throw when(response.code()) {
                    500 -> ServerErrorException(message)
                    else -> GeneralNetworkException(message)
                }
            }

        } catch (e: Exception) {
            // Log
            throw e
        }
    }
}