package pokemontcg.features.cards.ui.main

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.cards_activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import pokemontcg.features.cards.R
import pokemontcg.libraries.ui_components.BaseViewModel.State.Default
import pokemontcg.libraries.ui_components.BaseViewModel.State.Loading
import pokemontcg.libraries.ui_components.extensions.createLoadingDialog

class CardMainActivity : AppCompatActivity() {

    private val cardsAdapter = CardsAdapter()

    private val viewModel: CardMainViewModel by viewModel()

    private val loadingAlert: AlertDialog by lazy {
        createLoadingDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cards_activity_main)

        setupView()
        setupObservers()
        viewModel.init()
    }

    private fun setupView() {
        rcvListaCartas.adapter = cardsAdapter
    }

    private fun setupObservers() {
        viewModel.cards.observe(this, Observer {
            cardsAdapter.submitList(it)
        })

        viewModel.state.observe(this, Observer { state ->
            when (state) {
                Default -> loadingAlert.hide()
                Loading -> loadingAlert.show()
            }
        })

        viewModel.showError.observe(this, Observer {
            showMessage(it)
        })
    }

    private fun showMessage(message: String) {
        createDialog(message).show()
    }

    private fun createDialog(message: String): AlertDialog {
        return AlertDialog.Builder(this).setMessage(message).create()
    }

}