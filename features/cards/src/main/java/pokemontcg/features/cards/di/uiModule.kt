package pokemontcg.features.cards.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pokemontcg.features.cards.ui.main.CardMainViewModel

internal val uiModule = module {
    viewModel { CardMainViewModel(useCase = get()) }
}