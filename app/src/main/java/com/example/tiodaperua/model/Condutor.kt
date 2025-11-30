package com.example.tiodaperua.model

data class Condutor(
    val id: Int,
    val nome: String,
    val cnh: String
) {
    override fun toString(): String {
        return nome
    }
}
