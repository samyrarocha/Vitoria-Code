package com.example.vcsamyrarocha_app3

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.vcsamyrarocha_app3.R.id.nivel
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    lateinit var button: Button
    lateinit var nivel: TextInputLayout
    lateinit var resultado: TextView

    fun fibonacci(): Sequence<Long> {
        // fibonacci terms
        // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, ...
        //função generateSequence() produz uma sequencia infinita de números naturais.
        //Primeiro arg é o elemento inicial seguido por um lambda
        return generateSequence(Pair<Long, Long>(0, 1),
            { Pair(it.second, it.first + it.second) }).map { it.first }
    }
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nivel = findViewById(R.id.nivel )
        button = findViewById(R.id.button)
        resultado = findViewById(R.id.resultado)

        button.setOnClickListener {
            val nivelInserido: String = nivel.editText?.text.toString()

            if (nivelInserido.isEmpty()) {
                resultado.text = "Insira um nível para a sequência"
            }
            else{
            fibonacci().take(nivelInserido.toInt()).toList().toString().also { resultado.text = it }
            }
        }
    }
}