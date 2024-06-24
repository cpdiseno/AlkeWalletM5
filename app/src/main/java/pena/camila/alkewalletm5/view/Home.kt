package pena.camila.alkewalletm5.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pena.camila.alkewalletm5.R
import pena.camila.alkewalletm5.data.database.AppDatabase
import pena.camila.alkewalletm5.databinding.FragmentHomeBinding
import pena.camila.alkewalletm5.utils.SharedPreferencesManager
import pena.camila.alkewalletm5.view.adapter.TransactionAdapter


class Home : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val transactionAdapter = TransactionAdapter()
    private val transactionDao by lazy {
        AppDatabase.getDatabase(requireContext()).transactionDao()
    }
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // setupHeaderFragment()
        setupRecyclerView()
        loadTransactions()
        sharedPreferencesManager = SharedPreferencesManager(requireContext())

        // Obtener saldo
        val saldoStr = sharedPreferencesManager.getSaldo()
        val saldo = saldoStr?.toFloatOrNull()

        // Se cambia el color del botón según el saldo
        if (saldo != null && saldo > 0) {
            binding.buttonEnviarDinero.backgroundTintList =
                ContextCompat.getColorStateList(requireContext(), R.color.verde) // Color habilitado

        } else {
            binding.buttonEnviarDinero.backgroundTintList = ContextCompat.getColorStateList(
                requireContext(),
                R.color.gristexto
            ) // boton deshabilitado color gris

        }

        binding.buttonIngresarDinero.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_requestMoney,
                Bundle().apply {
                    putString("transaction_type", "topup")
                })
        }

        binding.buttonEnviarDinero.setOnClickListener {
            //Si el saldo es 0 se inhabilita el botòn

            if (saldo != null && saldo > 0) {
                findNavController().navigate(R.id.action_home_to_sendMoney,
                    Bundle().apply {
                        putString("transaction_type", "payment")
                    })

            } else {
                Toast.makeText(
                    requireContext(),
                    "No tienes saldo en tu cuenta para enviar dinero ",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
//        binding.headerContainerHomePage.profileImg.setOnClickListener {
//            findNavController().navigate(R.id.action_home_to_profile)
//        }

    }

    private fun setupRecyclerView() {
        binding.transactionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionAdapter
        }
    }

    private fun loadTransactions() {
        transactionDao.getAllTransactions().observe(viewLifecycleOwner, Observer { transactions ->
            transactions?.let {
                if (it.isEmpty()) {
                    binding.transactionsRecyclerView.visibility = View.GONE
                    binding.noTransactionsView.visibility = View.VISIBLE
                } else {
                    binding.transactionsRecyclerView.visibility = View.VISIBLE
                    binding.noTransactionsView.visibility = View.GONE
                    transactionAdapter.submitList(it)
                }
            }
        })
    }

    private fun setupHeaderFragment() {
        val fragment = HeaderHomePageFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.headerContainerHomePage, fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}