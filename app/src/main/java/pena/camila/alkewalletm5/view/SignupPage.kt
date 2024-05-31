package pena.camila.alkewalletm5.view

import RegisterViewModel
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
import pena.camila.alkewalletm5.databinding.FragmentSignupPageBinding


class SignupPage : Fragment() {

    private lateinit var binding: FragmentSignupPageBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Configurar el binding
        binding = FragmentSignupPageBinding.inflate(inflater, container, false)

        // Configurar el ViewModel
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        // Implementar los SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("AlkeWalet", Context.MODE_PRIVATE)

        // Configurar el botón de Crear Cuenta
        binding.btnCrearcuenta.setOnClickListener {
            // Obtener la información ingresada por el usuario
            val nombreIngresado = binding.nombreIngresado.text.toString()
            val apellidoIngresado = binding.apellidoIngresado.text.toString()
            val correoIngresado = binding.emailIngresado.text.toString()
            val contrasenaIngresada = binding.contrasenaIngresada.text.toString()
            val reingresarContrasena = binding.reingresarcontrasena.text.toString()

            // Realizar el inicio de sesión con botón de Crear Cuenta
            viewModel.crearUsuario(
                nombreIngresado,
                apellidoIngresado,
                correoIngresado,
                contrasenaIngresada,
                reingresarContrasena
            )
        }

        // Configurar el observador del resultado del inicio de sesión
        viewModel.signupResultLiveData.observe(viewLifecycleOwner) { signupOk ->
            if (signupOk) {
                // Navegar a la siguiente pantalla usando la acción definida en el nav_graph
                findNavController().navigate(R.id.action_signupPage_to_emptyCase)
            } else {
                Toast.makeText(requireContext(), "Datos inválidos", Toast.LENGTH_LONG).show()
            }
        }

        // Configurar el botón "Ya tienes cuenta"
        binding.yaTienesCuenta.setOnClickListener {
            // Navegar a la pantalla de inicio de sesión usando la acción definida en el nav_graph
            findNavController().navigate(R.id.action_signupPage_to_loginPage)
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