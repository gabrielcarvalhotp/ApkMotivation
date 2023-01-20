package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.devmasterteam.motivation.repository.Mock
import com.example.motivation.infra.MotivationConstantes
import com.example.motivation.R
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var bind: ActivityMainBinding
    private var categoryId = MotivationConstantes.FILTER.ALL

// Criada, Inicializada, Resmida, Utilizada, Pausada, Parada, Destruida
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        //Retirando a barra
        supportActionBar?.hide()

        //Evento de Click
        bind.buttonNewPhrase.setOnClickListener { handleNewPhrase() }
        bind.imageAll.setOnClickListener(this)
        bind.imageHappy.setOnClickListener(this)
        bind.imageSunny.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)) {
            handleFilter(view.id)
        }
    }

    override fun onResume() {
        super.onResume()
        handleFilter(R.id.image_all)
        handleSave()
        handleNewPhrase()
    }

    private fun handleNewPhrase() {
        val language = Locale.getDefault().language
        bind.textPhrase.text = Mock().getPhrase(categoryId, language)
    }

    private fun handleFilter(id: Int) {
        setColor(bind.imageAll, bind.imageHappy, bind.imageSunny, R.color.black)

        when (id) {
            R.id.image_all -> {
                //bind.imageAll.setColorFilter(white)
                setColor(bind.imageAll, R.color.white)
                categoryId = MotivationConstantes.FILTER.ALL
            }
            R.id.image_sunny -> {
                setColor(bind.imageSunny, R.color.white)
                categoryId = MotivationConstantes.FILTER.SUNNY
            }
            R.id.image_happy -> {
                setColor(bind.imageHappy, R.color.white)
                categoryId = MotivationConstantes.FILTER.HAPPY
            }
        }
    }

    private fun setColor(view: ImageView, color: Int) {
        view.setColorFilter(ContextCompat.getColor(this, color))
    }

    private fun setColor(v1: ImageView, v2: ImageView, v3: ImageView, color: Int) {
        v1.setColorFilter(ContextCompat.getColor(this, color))
        v2.setColorFilter(ContextCompat.getColor(this, color))
        v3.setColorFilter(ContextCompat.getColor(this, color))
    }

    private fun handleSave() {
        val userName =
            SecurityPreferences(this).getSecurityString(MotivationConstantes.KEY.USER_NAME)

        bind.helloUser.text = "${getString(R.string.hello)}, $userName!"
    }
}