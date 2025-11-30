package com.example.tiodaperua.ui.condutores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiodaperua.databinding.ListItemCondutorBinding
import com.example.tiodaperua.model.Condutor

class CondutorAdapter(
    private var condutores: List<Condutor>,
    private val onEditClick: (Condutor) -> Unit,
    private val onDeleteClick: (Condutor) -> Unit
) : RecyclerView.Adapter<CondutorAdapter.CondutorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CondutorViewHolder {
        val binding = ListItemCondutorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CondutorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CondutorViewHolder, position: Int) {
        holder.bind(condutores[position])
    }

    override fun getItemCount() = condutores.size

    fun updateCondutores(newCondutores: List<Condutor>) {
        condutores = newCondutores
        notifyDataSetChanged()
    }

    inner class CondutorViewHolder(private val binding: ListItemCondutorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(condutor: Condutor) {
            binding.textNomeCondutor.text = condutor.nome
            binding.textCnhCondutor.text = condutor.cnh

            binding.btnEditar.setOnClickListener { onEditClick(condutor) }
            binding.btnExcluir.setOnClickListener { onDeleteClick(condutor) }
        }
    }
}
