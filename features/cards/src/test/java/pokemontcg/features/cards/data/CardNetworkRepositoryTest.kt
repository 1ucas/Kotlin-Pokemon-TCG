package pokemontcg.features.cards.data

import CardsApi
import android.util.Log
import com.manobray.testutils.enqueueResponse
import com.manobray.testutils.loadJsonFromResources
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import pokemontcg.features.cards.data.network.CardsNetworkRepository
import pokemontcg.libraries.common.mapTo
import pokemontcg.libraries.network.ApiClientBuilder
import pokemontcg.libraries.network.exceptions.GeneralNetworkException
import pokemontcg.libraries.network.exceptions.ServerErrorException
import pokemontcg.libraries.network.fromJson
import pokemontcg.libraries.network.gsonDefault

class CardNetworkRepositoryTest {

    private val mockServer = MockWebServer()

    private lateinit var api: CardsApi

    private lateinit var repositorio: CardRepository

    @Before
    fun setup() {
        mockServer.start()

        val url = mockServer.url("").toString()

        api = ApiClientBuilder.createServiceApi(CardsApi::class.java, url)

        repositorio = CardsNetworkRepository(api)

        // não é necessário em um cenário real. Porque não deveria ter referência do Android em biblioca de network
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
    }

    @Test
    fun `listar cartas com sucesso`() = runBlocking {

        val jsonCarregado = loadJsonFromResources("listaCartas.json")
        mockServer.enqueueResponse(200, jsonCarregado)

        val resposta = repositorio.listCards()

        val respostaEsperada = gsonDefault.fromJson<ListCardResponse>(jsonCarregado).cards.mapTo()

        assertEquals(resposta, respostaEsperada)

    }

    @Test(expected = ServerErrorException::class)
    fun `erro ao listar cartas`() {
        runBlocking {
            mockServer.enqueueResponse(500, "")

            repositorio.listCards()
        }
    }


    @After
    fun close() {
        mockServer.shutdown()
    }

}