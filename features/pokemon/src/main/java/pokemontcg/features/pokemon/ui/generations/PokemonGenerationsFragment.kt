package pokemontcg.features.pokemon.ui.generations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_pokemon_generations.*
import org.koin.android.viewmodel.ext.android.viewModel

import pokemontcg.features.pokemon.R
import pokemontcg.libraries.ui_components.extensions.createLoadingDialog

internal class PokemonGenerationsFragment : Fragment() {

    companion object {
        fun newInstance() = PokemonGenerationsFragment()
    }

    private val loadingAlert: AlertDialog? by lazy {
        createLoadingDialog()
    }

    val viewModel: PokemonGenerationsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_generations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading.observe(this, Observer {
            if(it) {
                loadingAlert?.show()
            } else {
                loadingAlert?.hide()
            }
        })

        viewModel.generations.observe(this, Observer { generation ->
            generation.firstOrNull()?.let {
                tv_pokemon_generations.text = it.name
            }
        })

        viewModel.showError.observe(this, Observer {
            showMessage(it)
        })

        viewModel.fetchGenerations()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun showMessage(message: String) {
        val alert = createDialog(message)
        alert?.show()
    }

    private fun createDialog(message: String): AlertDialog? {
        return this.activity?.let { return AlertDialog.Builder(it, R.style.AlertDialog).setMessage(message).create() }
    }

}
