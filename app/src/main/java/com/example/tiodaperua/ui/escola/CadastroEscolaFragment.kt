package com.example.tiodaperua.ui.escola

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tiodaperua.dao.EscolaDAO
import com.example.tiodaperua.databinding.FragmentCadastroEscolaBinding
import com.example.tiodaperua.model.Escola
import com.example.tiodaperua.network.EnderecoResponse
import com.example.tiodaperua.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroEscolaFragment : Fragment() {

    private var _binding: FragmentCadastroEscolaBinding? = null
    private val binding get() = _binding!!

    private val args: CadastroEscolaFragmentArgs by navArgs()
    private var escolaId: Int = -1

    private lateinit var escolaDAO: EscolaDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        escolaId = args.escolaId
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCadastroEscolaBinding.inflate(inflater, container, false)
        escolaDAO = EscolaDAO(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCepListener()

        if (escolaId != -1) {
            loadEscolaData()
        }

        binding.btnSalvarEscola.setOnClickListener {
            salvarEscola()
        }
    }

    private fun setupCepListener() {
        binding.editCepEscola.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val cep = s.toString().replace("-", "")
                if (cep.length == 8) {
                    buscarEndereco(cep)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun buscarEndereco(cep: String) {
        RetrofitClient.instance.buscarEndereco(cep).enqueue(object : Callback<EnderecoResponse> {
            override fun onResponse(call: Call<EnderecoResponse>, response: Response<EnderecoResponse>) {
                if (response.isSuccessful) {
                    val endereco = response.body()
                    if (endereco != null && !endereco.erro) {
                        val enderecoCompleto = "${endereco.logradouro}, ${endereco.bairro} - ${endereco.localidade}/${endereco.uf}"
                        binding.editEnderecoEscola.setText(enderecoCompleto)
                        // Move cursor to end for user to add number
                        binding.editEnderecoEscola.setSelection(binding.editEnderecoEscola.text.length)
                        Toast.makeText(requireContext(), "Endereço encontrado!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "CEP não encontrado.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Erro ao buscar CEP.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<EnderecoResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Falha na conexão: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loadEscolaData() {
        val escola = escolaDAO.getById(escolaId)
        if (escola != null) {
            binding.editNomeEscola.setText(escola.nome)
            binding.editEnderecoEscola.setText(escola.endereco)
        }
    }

    private fun salvarEscola() {
        val nome = binding.editNomeEscola.text.toString()
        val endereco = binding.editEnderecoEscola.text.toString()

        if (nome.isEmpty() || endereco.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            return
        }

        val escola = Escola(
            id = if (escolaId != -1) escolaId else 0,
            nome = nome,
            endereco = endereco
        )

        val sucesso = if (escolaId != -1) {
            escolaDAO.atualizar(escola)
        } else {
            escolaDAO.adicionar(escola)
        }

        if (sucesso) {
            val message = if (escolaId != -1) "Escola atualizada com sucesso!" else "Escola salva com sucesso!"
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        } else {
            val message = if (escolaId != -1) "Falha ao atualizar a escola." else "Falha ao salvar a escola."
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
