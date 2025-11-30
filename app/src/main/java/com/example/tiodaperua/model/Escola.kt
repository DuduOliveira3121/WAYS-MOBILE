package com.example.tiodaperua.model

data class Escola(
    val id: Int,
    val nome: String,
    val endereco: String
) {
    override fun toString(): String {
        return nome
    }
}
