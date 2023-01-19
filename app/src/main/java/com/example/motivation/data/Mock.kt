package com.devmasterteam.motivation.repository

import com.example.motivation.infra.MotivationConstantes
import kotlin.random.Random

data class Phrase(val description: String, val category: Int)

class Mock {

    private val all = MotivationConstantes.FILTER.ALL
    private val happy = MotivationConstantes.FILTER.HAPPY
    private val sunny = MotivationConstantes.FILTER.SUNNY

    private val listPhrases: List<Phrase> = listOf(
        Phrase("Não sabendo que era impossível, foi lá e fez.", happy),
        Phrase("Você não é derrotado quando perde, você é derrotado quando desiste!", happy),
        Phrase("Quando está mais escuro, vemos mais estrelas!", happy),
        Phrase("Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.", happy),
        Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", happy),
        Phrase("O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?", happy),
        Phrase("A melhor maneira de prever o futuro é inventá-lo.", sunny),
        Phrase("Você perde todas as chances que você não aproveita.", sunny),
        Phrase("Fracasso é o condimento que dá sabor ao sucesso.", sunny),
        Phrase(" Enquanto não estivermos comprometidos, haverá hesitação!", sunny),
        Phrase("Se você não sabe onde quer ir, qualquer caminho serve.", sunny),
        Phrase("Se você acredita, faz toda a diferença.", sunny),
        Phrase("Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", sunny)
    )

    // Obtém frase aleatória de acordo com o filtro
    fun getPhrase(value: Int): String {
        val filtered: List<Phrase>;
        return if (value != all) {
            filtered = listPhrases.filter { (it.category == value) }
            filtered[Random.nextInt(filtered.size)].description
        } else {
            listPhrases[Random.nextInt(listPhrases.size)].description
        }
    }

}