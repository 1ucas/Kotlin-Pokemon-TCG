package pokemontcg.features.pokemon.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module
import pokemontcg.features.pokemon.data.PokemonRepository
import pokemontcg.features.pokemon.data.network.PokemonApi
import pokemontcg.features.pokemon.data.network.PokemonNetworkRepository
import pokemontcg.features.pokemon.domain.usecase.ListGenerationsUseCase
import pokemontcg.features.pokemon.ui.generations.PokemonGenerationsViewModel
import pokemontcg.libraries.network.ApiClientBuilder

object PokemonModule {
    fun getModules() : List<Module> = listOf(
        dataModule,
        useCaseModule,
        uiModule
    )
}

internal val dataModule = module {
    single { ApiClientBuilder.createServiceApi(PokemonApi::class.java, baseUrl = "https://pokeapi.co/api/v2/") }
    factory { PokemonNetworkRepository(api = get()) } bind PokemonRepository::class
}

internal val useCaseModule = module {
    factory { ListGenerationsUseCase(repo = get()) }
}

internal val uiModule = module {
    viewModel { PokemonGenerationsViewModel(useCase = get()) }
}
