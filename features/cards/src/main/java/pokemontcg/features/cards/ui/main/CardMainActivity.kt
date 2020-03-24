package pokemontcg.features.cards.ui.main

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.cards_activity_main.*
import kotlinx.coroutines.launch
import pokemontcg.features.cards.R
import pokemontcg.libraries.ui_components.extensions.createLoadingDialog

class CardMainActivity : AppCompatActivity() {

    private val cardsAdapter = CardsAdapter()

    private val viewModel: CardMainViewModel by lazy {
        ViewModelProvider(this).get(CardMainViewModel::class.java)
    }

    private val loadingAlert:AlertDialog by lazy {
        createLoadingDialog()
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

        viewModel.isLoading.observe(this, Observer {
            if(it){
                loadingAlert.show()
            } else {
                loadingAlert.hide()
            }
        })

        viewModel.showError.observe(this, Observer {
            showMessage(it)
        })
    }

    private fun showMessage(message: String){
        createDialog(message).show()
    }

    private fun createDialog(message: String) : AlertDialog {
        return AlertDialog.Builder(this).setMessage(message).create()
    }

}