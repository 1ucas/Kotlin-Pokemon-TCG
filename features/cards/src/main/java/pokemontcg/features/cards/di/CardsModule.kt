package pokemontcg.features.cards.di

import org.koin.core.module.Module

object CardsModule {

    fun getModules() : List<Module> = listOf(
        dataModule,
        useCaseModule,
        uiModule
    )
}