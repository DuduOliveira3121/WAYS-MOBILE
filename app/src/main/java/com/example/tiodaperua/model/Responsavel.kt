package com.example.tiodaperua.model

data class Responsavel(
    val id: Int,
    val nome: String,
    val telefone: String
) {
    override fun toString(): String {
        return nome
    }
}
