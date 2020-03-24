package pokemontcg.features.cards.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.cards_activity_main.*
import kotlinx.coroutines.launch
import pokemontcg.features.cards.R

class CardMainActivity : AppCompatActivity() {

    private val cardsAdapter = CardsAdapter()

    private val viewModel: CardMainViewModel by lazy {
        ViewModelProvider(this).get(CardMainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cards_activity_main)

        setupView()
        setupObservers()
    }

    private fun setupView() {
        rcvListaCartas.adapter = cardsAdapter
    }

    private fun setupObservers() {
        viewModel.cards.observe(this, Observer {
            cardsAdapter.submitList(it)
        })
    }

}