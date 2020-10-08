package com.manobray.pokemontcg.di

import org.koin.core.module.Module
import pokemontcg.features.cards.di.CardsModule
import pokemontcg.features.pokemon.di.PokemonModule

object AppModule {

    fun getModules() : List<Module> = mutableListOf<Module>().apply {
        addAll(CardsModule.getModules())
        addAll(PokemonModule.getModules())
    }

}