package pena.camila.alkewalletm5.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import pena.camila.alkewalletm5.R
import pena.camila.alkewalletm5.api.ApiClient
import pena.camila.alkewalletm5.data.database.AppDatabase
import pena.camila.alkewalletm5.data.repository.TransactionRepository
import pena.camila.alkewalletm5.databinding.FragmentRequestMoneyBinding
import pena.camila.alkewalletm5.utils.SharedPreferencesManager
import pena.camila.alkewalletm5.utils.TransactionFetcher
import pena.camila.alkewalletm5.viewmodel.LoginViewModel
import pena.camila.alkewalletm5.viewmodel.TransactionViewModel
import pena.camila.alkewalletm5.viewmodel.ViewModelFactory


class RequestMoney : Fragment() {
    private var _binding: FragmentRequestMoneyBinding? = null
    private val binding get() = _binding!!

    private val sharedPreferencesManager by lazy { SharedPreferencesManager(requireContext()) }
    private val appDatabase by lazy {
        AppDatabase.getDatabase(requireContext())
    }

    private val transactionRepository by lazy {
        TransactionRepository(appDatabase.transactionDao(), ApiClient.apiService)
    }

    private val transactionFetcher by lazy {
        TransactionFetcher(transactionRepository)
    }
    private val transactionViewModel: TransactionViewModel by viewModels {
        ViewModelFactory(sharedPreferencesManager, transactionFetcher)
    }
    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory(sharedPreferencesManager, transactionFetcher)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRequestMoneyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHeaderFragment()

        binding.ingresarBtn.setOnClickListener {
            val type = "topup"
            val concept = binding.conceptoIngreso.text.toString()
            val amount = binding.montoIngreso.text.toString().toLong()

            transactionViewModel.depositarOtransferir(type, concept, amount)
        }

        observeViewModels()
    }

    private fun observeViewModels() {
        transactionViewModel.transactionResult.observe(viewLifecycleOwner) { result ->
            if (result) {
                Toast.makeText(context, "Depósito realizado correctamente", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_requestMoney_to_home)
            } else {
                transactionViewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
                    Toast.makeText(context, "Error en el depósito: $errorMessage", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }

    private fun setupHeaderFragment() {
        val fragment = TransactionHeaderFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.user_container, fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


/*
class RequestMoney : Fragment() {
        private var _binding: FragmentRequestMoneyBinding? = null
        private val binding get() = _binding!!

        private val sharedPreferencesManager by lazy { SharedPreferencesManager(requireContext()) }
        private val appDatabase by lazy {
            AppDatabase.getDatabase(requireContext())
        }

        private val transactionRepository by lazy {
            TransactionRepository(appDatabase.transactionDao(), ApiClient.apiService)
        }

        private val transactionFetcher by lazy {
            TransactionFetcher(transactionRepository)
        }
        private val transactionViewModel: TransactionViewModel by viewModels { ViewModelFactory(
                sharedPreferencesManager,
                transactionFetcher
            )
        }
        private val loginViewModel: LoginViewModel by viewModels {
            ViewModelFactory(
                sharedPreferencesManager,
                transactionFetcher
            )
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            _binding = FragmentRequestMoneyBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            setupHeaderFragment()



          binding.ingresarBtn.setOnClickListener {
                val type = "topup"
                val concept = binding.conceptoIngreso.text.toString()
                val amount = binding.montoIngreso.text.toString().toLong()

                transactionViewModel.depositarOtransferir(type, concept, amount)
            }

            observeViewModels()
        }

        private fun observeViewModels() {
            transactionViewModel.transactionResult.observe(viewLifecycleOwner) { result ->
                if (result) {
                    val token = sharedPreferencesManager.getAuthToken()
                    if (token != null) {
                        loginViewModel.getUserAccountsDetails(token)
                        loginViewModel.accountDetailsUpdated.observe(viewLifecycleOwner) { isUpdated ->
                            if (isUpdated) {
                                Toast.makeText(context, "Depósito realizado correctamente", Toast.LENGTH_SHORT).show()
                                findNavController().navigate(R.id.action_requestMoney_to_home)
                            } else {
                                Toast.makeText(context, "Error al actualizar el saldo", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Token no disponible", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    transactionViewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
                        Toast.makeText(context, "Error en el depósito: $errorMessage", Toast.LENGTH_SHORT).show()
                    })
                }
            }
        }

        private fun setupHeaderFragment() {
            val fragment = TransactionHeaderFragment()
            childFragmentManager.beginTransaction()
                .replace(R.id.user_container, fragment)
                .commit()
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }*/
