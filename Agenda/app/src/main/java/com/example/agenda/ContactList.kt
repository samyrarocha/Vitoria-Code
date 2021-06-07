package com.example.agenda

import java.io.Serializable

data class Contact(
    val nome: String,
    val telefone: String,
    val tipoContato: ContactType,
    val preferencia: String
    ): Serializable

enum class ContactType{
    PESSOAL, TRABALHO
}
