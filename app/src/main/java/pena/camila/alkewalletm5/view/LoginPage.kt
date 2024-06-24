package pena.camila.alkewalletm5.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import pena.camila.alkewalletm5.R
import pena.camila.alkewalletm5.api.ApiClient
import pena.camila.alkewalletm5.data.database.AppDatabase
import pena.camila.alkewalletm5.data.repository.TransactionRepository
import pena.camila.alkewalletm5.databinding.FragmentLoginPageBinding
import pena.camila.alkewalletm5.utils.SharedPreferencesManager
import pena.camila.alkewalletm5.utils.TransactionFetcher
import pena.camila.alkewalletm5.viewmodel.LoginViewModel
import pena.camila.alkewalletm5.viewmodel.ViewModelFactory

class LoginPage : Fragment() {
    private var _binding: FragmentLoginPageBinding? = null
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

    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory(sharedPreferencesManager, transactionFetcher)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginPageBinding.inflate(inflater, container, false)
        setEmailInput()
        return binding.root
    }

    /**
     * Set email input desde sharedpreferences
     */
    private fun setEmailInput() {
        binding.txtEmail.setText(sharedPreferencesManager.getUser()?.email)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.BotonLogin.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            val password = binding.txtContrasena.text.toString()
            loginViewModel.login(email, password)
        }

        loginViewModel.loginResult.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                findNavController().navigate(R.id.action_loginPage_to_home)
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.login_failed),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}