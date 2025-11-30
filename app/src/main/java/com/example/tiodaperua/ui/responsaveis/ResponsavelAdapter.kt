package com.example.tiodaperua.ui.responsaveis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiodaperua.databinding.ListItemResponsavelBinding
import com.example.tiodaperua.model.Responsavel

class ResponsavelAdapter(
    private var responsaveis: List<Responsavel>,
    private val onEditClick: (Responsavel) -> Unit,
    private val onDeleteClick: (Responsavel) -> Unit
) : RecyclerView.Adapter<ResponsavelAdapter.ResponsavelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponsavelViewHolder {
        val binding = ListItemResponsavelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResponsavelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResponsavelViewHolder, position: Int) {
        holder.bind(responsaveis[position])
    }

    override fun getItemCount() = responsaveis.size

    fun updateResponsaveis(newResponsaveis: List<Responsavel>) {
        responsaveis = newResponsaveis
        notifyDataSetChanged()
    }

    inner class ResponsavelViewHolder(private val binding: ListItemResponsavelBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(responsavel: Responsavel) {
            binding.textNomeResponsavel.text = responsavel.nome
            binding.textTelefoneResponsavel.text = responsavel.telefone

            binding.btnEditar.setOnClickListener { onEditClick(responsavel) }
            binding.btnExcluir.setOnClickListener { onDeleteClick(responsavel) }
        }
    }
}
