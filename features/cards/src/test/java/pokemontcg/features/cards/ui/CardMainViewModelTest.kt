package pokemontcg.features.cards.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.manobray.testutils.CoroutineTestRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pokemontcg.features.cards.model.Card
import pokemontcg.features.cards.ui.main.CardMainViewModel
import pokemontcg.features.cards.usecase.ListCardsUseCase
import pokemontcg.libraries.network.exceptions.ServerErrorException
import pokemontcg.libraries.ui_components.BaseViewModel.State

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

    private lateinit var stateObserver: Observer<State>

    private lateinit var errorObserver: Observer<String>

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        viewModel = CardMainViewModel(useCase)

        stateObserver = spyk(Observer { })
        errorObserver = spyk(Observer { })

        viewModel.state.observeForever(stateObserver)
        viewModel.showError.observeForever(errorObserver)

        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `inicializa viewModel com sucesso`() {
        // Given
        val slots = mutableListOf<State>()
        val listaCartas = listOf(Card("150", "Mewtwo", "mewtwourl"))
        coEvery { useCase.execute(null) } returns listaCartas

        // When
        viewModel.init()

        // Then
        verify(exactly = 0) { errorObserver.onChanged(any()) }
        verify(exactly = 2) { stateObserver.onChanged(capture(slots)) }

        assertEquals(State.Loading, slots[0])
        assertEquals(State.Default, slots[1])
        assertEquals(listaCartas, viewModel.cards.value)
        assertEquals(true, viewModel.isInitialized)
    }

    @Test
    fun `inicializa viewModel com erro`() {
        // Given
        val slots = mutableListOf<State>()
        coEvery { useCase.execute(null) } throws ServerErrorException(null)

        // When
        viewModel.init()

        // Then
        verify { errorObserver.onChanged(any()) }
        verify(exactly = 2) { stateObserver.onChanged(capture(slots)) }

        assertEquals(State.Loading, slots[0])
        assertEquals(State.Default, slots[1])
        assertEquals(null, viewModel.cards.value)
        assertEquals(false, viewModel.isInitialized)
    }
}