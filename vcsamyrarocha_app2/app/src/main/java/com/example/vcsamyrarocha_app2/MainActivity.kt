@file:Suppress("DEPRECATION")

package com.example.vcsamyrarocha_app2

import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.icu.util.LocaleData
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormat
import java.time.Duration
import java.time.LocalDate
import java.time.Year
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.logging.Logger.global

@RequiresApi(Build.VERSION_CODES.N)
class MainActivity : AppCompatActivity() {

    data class User(val nome: String, val presente: String, val diasParaNiver: Long)


    private lateinit var data: String
    lateinit var nome: EditText
    lateinit var presente: EditText
    lateinit var dataNasc: DatePicker
    lateinit var button: Button
    lateinit var resultado: TextView
    lateinit var listaDeUsers: TextView
    var dadosUser = mutableListOf<User>()

    private fun dateInit() {
        val c = Calendar.getInstance()

        dataNasc.init(
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH)
        )
        { _, year, month, dayOfMonth ->
            val mesCorrigido = month + 1
            data = String.format("%4d-%02d-%2d", year, mesCorrigido, dayOfMonth)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nome = findViewById(R.id.edtNome)
        presente = findViewById(R.id.edtPrsnt)
        dataNasc = findViewById(R.id.dataNasc)
        resultado = findViewById(R.id.resultado)
        button = findViewById(R.id.button)

        dateInit()

        fun pegarDataDeHoje(): String {
            val hoje = Calendar.getInstance()
            val dia: Int = hoje.get(Calendar.DAY_OF_MONTH)
            val mes: Int = hoje.get(Calendar.MONTH)
            val ano: Int = hoje.get(Calendar.YEAR)

            return String.format("%4d-%02d-%2d", ano, mes + 1, dia)
        }


        button.setOnClickListener {

            val nomeInserido = nome?.text.toString()
            val prsntInserido = presente?.text.toString()
            val dia = dataNasc.dayOfMonth
            val mes = dataNasc.month + 1
            val ano = dataNasc.year

            val dataHoje = pegarDataDeHoje()

            val dataNiver = LocalDate.parse(data, DateTimeFormatter.ISO_DATE)
            val dataAtual = LocalDate.parse(dataHoje, DateTimeFormatter.ISO_DATE)

            val dataNascAnoAtualInic = String.format("%4d-%02d-%2d",
                dataAtual.year,
                dataNiver.monthValue,
                dataNiver.dayOfMonth)
            val dataNascAnoAtual = LocalDate.parse(dataNascAnoAtualInic, DateTimeFormatter.ISO_DATE)

            var diferenca = dataAtual.until(dataNascAnoAtual, ChronoUnit.DAYS)

            if (diferenca < 0)
                diferenca += 365

            val user = User(nomeInserido, prsntInserido, diferenca)
            dadosUser.add(user)

            var exibirUser: String = ""

            for (u in dadosUser) {
                exibirUser += "Nome: ${u.nome} \n Presente: ${u.presente} \n Dias para aniversário: ${u.diasParaNiver.toString()} \n\n"
            }

            resultado.text = exibirUser

        }
    }

}