package pokemontcg.features.cards.di

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class CardsDITest : KoinTest {
    @JvmField
    @Rule
    val testRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `check module`() {
        val mockkAplication = mockk<Application>(relaxed = true)

        val koinApplication = startKoin {
            androidContext(mockkAplication)
        }

        loadKoinModules(CardsModule.getModules())

        koinApplication.checkModules()
    }
}