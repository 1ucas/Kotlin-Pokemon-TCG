package pokemontcg.features.cards.di

import CardsApi
import org.koin.dsl.bind
import org.koin.dsl.module
import pokemontcg.features.cards.data.CardRepository
import pokemontcg.features.cards.data.network.CardsNetworkRepository
import pokemontcg.libraries.network.ApiClientBuilder

internal val dataModule = module {

    single { ApiClientBuilder.createServiceApi(CardsApi::class.java) }
    factory { CardsNetworkRepository(api = get()) } bind CardRepository::class

}