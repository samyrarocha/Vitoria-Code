
package com.example.agenda

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast

class AddContact : AppCompatActivity() {

    lateinit var nomeAdd: EditText
    lateinit var telefoneAdd: EditText
    lateinit var prefereciaAdd:EditText
    lateinit var pessoal: RadioButton
    lateinit var trabalho: RadioButton
    lateinit var salvar: Button
    var tipoContato = ContactType.PESSOAL

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.pessoalButton ->
                    if (checked) {
                        tipoContato = ContactType.PESSOAL
                        prefereciaAdd.hint = "Referência"
                        prefereciaAdd.inputType = InputType.TYPE_CLASS_TEXT
                    }
                R.id.trabalhoButton ->
                    if (checked) {
                        tipoContato = ContactType.TRABALHO
                        prefereciaAdd.hint = "E-mail"
                        prefereciaAdd.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                    }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        nomeAdd = findViewById(R.id.nomeAdd)
        telefoneAdd = findViewById(R.id.telefoneAdd)
        prefereciaAdd = findViewById(R.id.preferenciaAdd)
        pessoal = findViewById(R.id.pessoalButton)
        trabalho = findViewById(R.id.trabalhoButton)
        salvar = findViewById(R.id.add)

        salvar.setOnClickListener {

            val nome = nomeAdd.text.toString()
            val telefone = telefoneAdd.text.toString()
            val preferencia = prefereciaAdd.text.toString()


            val contato = Contact(nome, telefone, tipoContato, preferencia)

            intent.putExtra("contato", contato)
            setResult(Activity.RESULT_OK, intent)

            Toast.makeText(this, "Salvo!", Toast.LENGTH_LONG).show()
            onBackPressed()

        }

    }


}