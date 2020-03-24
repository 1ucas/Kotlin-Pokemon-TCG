package pokemontcg.features.cards.ui.main

import CardsApi
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.cards_activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pokemontcg.features.cards.R
import pokemontcg.features.cards.model.Card
import pokemontcg.libraries.network.ApiClientBuilder
import pokemontcg.libraries.network.RequestManager

class CardMainActivity : AppCompatActivity() {

    private val cardsAdapter = CardsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cards_activity_main)

        setupView()
        listCards()
    }

    private fun setupView() {
        rcvListaCartas.adapter = cardsAdapter
    }

    private fun listCards() {
        val api = ApiClientBuilder.createServiceApi(CardsApi::class.java)

        lifecycleScope.launch(Dispatchers.IO) {
            val apiResponse = RequestManager.requestFromApi { api.listCards() }

            val cards = apiResponse?.cards?.map {
                Card(
                    id = it.id,
                    name = it.name,
                    imageURL = it.imageUrl
                )
            }

            withContext(Dispatchers.Main) {
                cardsAdapter.submitList(cards)
            }
        }
    }

}