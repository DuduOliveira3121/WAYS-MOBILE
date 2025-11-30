package com.example.tiodaperua.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.tiodaperua.database.DatabaseHelper
import com.example.tiodaperua.model.Responsavel

class ResponsavelDAO(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun getById(id: Int): Responsavel? {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT id, nome, telefone FROM ${DatabaseHelper.TBL_RESPONSAVEIS} WHERE id = ?", arrayOf(id.toString()))
        var responsavel: Responsavel? = null
        if (cursor.moveToFirst()) {
            responsavel = Responsavel(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                nome = cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                telefone = cursor.getString(cursor.getColumnIndexOrThrow("telefone"))
            )
        }
        cursor.close()
        db.close()
        return responsavel
    }

    fun listar(): List<Responsavel> {
        val lista = mutableListOf<Responsavel>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT id, nome, telefone FROM ${DatabaseHelper.TBL_RESPONSAVEIS}", null)
        if (cursor.moveToFirst()) {
            do {
                lista.add(
                    Responsavel(
                        id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        nome = cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                        telefone = cursor.getString(cursor.getColumnIndexOrThrow("telefone"))
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return lista
    }

    fun adicionar(responsavel: Responsavel): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nome", responsavel.nome)
            put("telefone", responsavel.telefone)
        }
        val id = db.insert(DatabaseHelper.TBL_RESPONSAVEIS, null, values)
        db.close()
        return id != -1L
    }

    fun atualizar(responsavel: Responsavel): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nome", responsavel.nome)
            put("telefone", responsavel.telefone)
        }
        val rows = db.update(DatabaseHelper.TBL_RESPONSAVEIS, values, "id = ?", arrayOf(responsavel.id.toString()))
        db.close()
        return rows > 0
    }

    fun excluir(id: Int): Boolean {
        val db = dbHelper.writableDatabase
        val rows = db.delete(DatabaseHelper.TBL_RESPONSAVEIS, "id = ?", arrayOf(id.toString()))
        db.close()
        return rows > 0
    }
}
