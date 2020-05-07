package pokemontcg.features.cards.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.manobray.testutils.CoroutineTestRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.internal.wait
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pokemontcg.features.cards.model.Card
import pokemontcg.features.cards.ui.main.CardMainViewModel
import pokemontcg.features.cards.usecase.ListCardsUseCase
import pokemontcg.libraries.network.exceptions.ServerErrorException

class CardMainViewModelTest {

    @Rule
    @JvmField
    val coroutineTestRule = CoroutineTestRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var useCase: ListCardsUseCase

    private lateinit var viewModel: CardMainViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = CardMainViewModel(useCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `inicializa viewModel com sucesso`() {

        val listaCartas = listOf(Card("150", "Mewtwo", "mewtwourl"))

        coEvery { useCase.execute(null) } returns listaCartas

        viewModel.init()

        assertEquals(listaCartas, viewModel.cards.value)
        assertEquals(true, viewModel.isInitialized)
    }

    @Test
    fun `inicializa viewModel com erro`() {

        coEvery { useCase.execute(null) } throws ServerErrorException(null)

        viewModel.init()

        assertEquals(null, viewModel.cards.value)
        assertEquals(false, viewModel.isInitialized)
    }
}