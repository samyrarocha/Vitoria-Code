package com.example.elevador

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.elevador.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout
import kotlin.math.abs
import kotlin.time.Duration
import kotlin.time.ExperimentalTime


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    fun startAnimationCounter(andarAtual: Int, andarAlvo: Int) {
        lateinit var animator: ValueAnimator
        if (andarAlvo < andarAtual) {
            animator = ValueAnimator.ofInt(andarAtual - 1, andarAlvo)
            animator.setDuration(((abs(andarAlvo - andarAtual) * 1000).toLong()))
            animator.addUpdateListener {
                atualAndarText.text = (it.animatedValue as Int).toString()+"° Andar"
                if (atualAndarText.text == "0° Andar") {
                    atualAndarText.text = "Térreo"
                }
            }

        }else {
            animator = ValueAnimator.ofInt(andarAtual + 1, andarAlvo)
            animator.setDuration(((abs(andarAlvo - andarAtual) * 1000).toLong()))
            animator.addUpdateListener {
                atualAndarText.text = (it.animatedValue as Int).toString() + "° Andar"
            }
        }
        animator.start()
    }


    lateinit var andar: Button
    lateinit var entrar: Button
    lateinit var sair: Button
    lateinit var setAndar: TextInputLayout
    lateinit var atualAndarText: TextView
    lateinit var qtdPessoas: TextView

    var elevador = Elevador()

    @SuppressLint("SetTextI18n")
    private lateinit var binding: ActivityMainBinding

    @ExperimentalTime
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(binding.getRoot())


        andar = findViewById(R.id.andar)
        entrar = findViewById(R.id.entrar)
        sair = findViewById(R.id.sair)
        setAndar = findViewById(R.id.setAndar)
        atualAndarText = findViewById(R.id.atualAndar)
        qtdPessoas = findViewById(R.id.qtdPessoas)

        (elevador.nAtualPessoas.toString() + "/" + elevador.nMaxPessoas.toString()).also {
            qtdPessoas.text = it
        }

        atualAndarText.text = elevador.andarTexto()

        andar.setOnClickListener {
            setAndar.editText?.text.let {
                val andarAlvo = it.toString().toInt()
                val andarAtual = elevador.andarAtual
                val resultado = elevador.subirDescer(andarAlvo)
                if (resultado != null) {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "Vai mermao", Toast.LENGTH_LONG).show()
                    }
                } else {
                    startAnimationCounter(andarAtual, andarAlvo)
                }
            }

        }

        entrar.setOnClickListener {
            val resultado = elevador.entrar()
            if (resultado != null) {
                Toast.makeText(this, resultado, Toast.LENGTH_LONG + 4).show()
            }
            qtdPessoas.text = elevador.nAtualPessoas.toString()+"/"+elevador.nMaxPessoas

        }
        sair.setOnClickListener {
            val resultado = elevador.sair()
            if (resultado != null) {
                Toast.makeText(this, resultado, Toast.LENGTH_LONG + 4).show()
            }
            qtdPessoas.text = elevador.nAtualPessoas.toString()+"/"+elevador.nMaxPessoas
        }
    }
}