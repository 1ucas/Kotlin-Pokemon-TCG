package com.manobray.pokemontcg.di

import org.koin.core.module.Module
import pokemontcg.features.cards.di.CardsModule

object AppModule {

    fun getModules() : List<Module> = listOf(
        *CardsModule.getModules()
    )

}