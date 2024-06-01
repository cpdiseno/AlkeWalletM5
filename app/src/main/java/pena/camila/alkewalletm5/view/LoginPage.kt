package pena.camila.alkewalletm5.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import pena.camila.alkewalletm5.R
import pena.camila.alkewalletm5.databinding.FragmentLoginPageBinding
import pena.camila.alkewalletm5.viewmodel.LoginViewModel

class LoginPage : Fragment() {

    private lateinit var binding: FragmentLoginPageBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Configurar el binding
        binding = FragmentLoginPageBinding.inflate(inflater, container, false)

        // Configurar el ViewModel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // Implementar los SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("AlkeWalet", Context.MODE_PRIVATE)

        // Verificar si el usuario ya guardó el correo
        val correo = sharedPreferences.getString("correo_ingresado", null)
        if (correo != null) {
            binding.txtEmail.setText(correo)
        }

        // Configurar el botón de inicio de sesión
        binding.BotonLogin.setOnClickListener {
            // Obtener la información ingresada por el usuario
            val correoIngresado = binding.txtEmail.text.toString()
            val passwordIngresado = binding.contrasena.getText().toString()

            // Guardar el correo en los SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("correo_ingresado", correoIngresado)
            editor.putBoolean("recuerdame", true)
            editor.apply()

            // Realizar el inicio de sesión
            viewModel.hacerLogin(correoIngresado, passwordIngresado)
        }

        // Configurar el observador del resultado del inicio de sesión
        viewModel.loginResultLiveData.observe(viewLifecycleOwner) { loginOk ->
            if (loginOk) {
                // Navegar a la siguiente pantalla usando la acción definida en el nav_graph
                findNavController().navigate(R.id.action_loginPage_to_home)
            } else {
                Toast.makeText(requireContext(), "Datos inválidos", Toast.LENGTH_LONG).show()
            }
        }

        // Configurar el botón "Crear Nueva Cuenta"
        binding.buttonCrearNuevaCuenta.setOnClickListener {
            // Navegar a la pantalla de registro usando la acción definida en el nav_graph
            findNavController().navigate(R.id.action_loginPage_to_signupPage)
        }

        // Configurar el listener para los insets de la ventana
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        return binding.root
    }
}