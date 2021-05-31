package com.example.elevador

import android.os.Build
import androidx.annotation.RequiresApi

class Elevador {

    val nMaxPessoas = 5
    val nMaxAndares = 10
    var nAtualPessoas = 0
    var andarAtual = 0

//    fun getApplicationContext(): Context? {
//       Context.VIBRATOR_SERVICE
//    }

    fun andarTexto(): String {

        if (andarAtual == 0) {
            return "Térreo"
        } else {
            return "$andarAtual° Andar"
        }

    }

    fun subirDescer(andarAlvo: Int): String? {
        if (andarAtual == andarAlvo) {
            return "Você já está nesse andar"
        } else if (andarAlvo < 0 || andarAlvo >= nMaxAndares) {
            return "Andar Inválido"
        } else {
            andarAtual = andarAlvo
            return null
        }
    }

    fun entrar(): String? {

        if (nAtualPessoas >= nMaxPessoas) {
            return "Número máximo excedido! Procure outro elevador ou aguarde uma próxima viagem"
        } else {
            nAtualPessoas += 1
            return null
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun sair(): String? {
        if (nAtualPessoas == 0) {
            return "Não há pessoas nesse Elevador"
        }else {
            nAtualPessoas -= 1
            return null
        }
    }
}
