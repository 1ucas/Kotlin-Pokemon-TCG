package com.manobray.pokemontcg

import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import pokemontcg.features.cards.di.CardsModule

internal class AppModuleTest : KoinTest {

    @Test
    fun `check modules`() {
        koinApplication {
            modules(*CardsModule.getModules())
        }.checkModules()
    }

}