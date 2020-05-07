package pokemontcg.features.cards.usecase

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import pokemontcg.features.cards.data.CardRepository
import pokemontcg.features.cards.model.Card
import pokemontcg.libraries.network.exceptions.GeneralNetworkException
import java.lang.reflect.GenericArrayType

class ListCardsUseCaseTest {

    @MockK
    private lateinit var repositorio: CardRepository

    private lateinit var useCase: ListCardsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = ListCardsUseCase(repositorio)
    }

    @Test
    fun `caso de uso executa com sucesso`() {
        runBlocking {
            val resposta = listOf(Card("151", "mew", "url"))

            coEvery {
                repositorio.listCards()
            } returns resposta

            val respostaObtida = useCase.execute(null)

            assertEquals(resposta, respostaObtida)
        }
    }

    @Test(expected = GeneralNetworkException::class)
    fun `caso de uso executa com erro`() {
        runBlocking {
            coEvery {
                repositorio.listCards()
            } throws GeneralNetworkException()

            useCase.execute(null)
        }
    }


}