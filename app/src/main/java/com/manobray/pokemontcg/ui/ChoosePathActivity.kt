package com.manobray.pokemontcg.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manobray.pokemontcg.R
import kotlinx.android.synthetic.main.activity_choose_path.*

class ChoosePathActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_path)

        bt_tcg_act_choose_path.setOnClickListener {
            Intent(this@ChoosePathActivity, Class.forName("pokemontcg.features.cards.ui.main.CardMainActivity")).also {
                startActivity(it)
            }
        }

        bt_pokemon_act_choose_path.setOnClickListener {
            Intent(this@ChoosePathActivity, Class.forName("pokemontcg.features.pokemon.ui.main.PokemonMainActivity")).also {
                startActivity(it)
            }
        }
    }
}
