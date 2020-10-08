package com.manobray.pokemontcg

import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import pokemontcg.features.cards.di.CardsModule
import pokemontcg.features.pokemon.di.PokemonModule

internal class AppModuleTest : KoinTest {

    @Test
    fun `check card modules`() {
        koinApplication {
            modules(CardsModule.getModules())
        }.checkModules()
    }

    @Test
    fun `check pokemon modules`() {
        koinApplication {
            modules(CardsModule.getModules())
        }.checkModules()
    }

}