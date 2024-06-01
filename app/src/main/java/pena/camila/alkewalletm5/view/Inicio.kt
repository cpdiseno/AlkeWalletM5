package pena.camila.alkewalletm5.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import pena.camila.alkewalletm5.R
import pena.camila.alkewalletm5.databinding.FragmentInicioBinding

class Inicio : Fragment() {

    private lateinit var binding: FragmentInicioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInicioBinding.inflate(inflater, container, false)

        // Acción para el botón "botonCrearCuenta"
        binding.botonCrearCuenta.setOnClickListener {
            it.findNavController().navigate(R.id.action_inicio_to_signupPage)
        }

        // Acción para el botón "yaTienesCuenta"
        binding.yaTienesCuenta.setOnClickListener {
            it.findNavController().navigate(R.id.action_inicio_to_loginPage)
        }

        return binding.root
    }
}



/*
internal class Inicio : Fragment() {
    private var binding: FragmentInicioBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using view binding
        binding = FragmentInicioBinding.inflate(inflater, container, false)

        // Return the root view
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get references to the views using binding
        val crearcuenta: Button = binding!!.botonCrearCuenta
        val yatienescuenta: TextView = binding!!.yaTienesCuenta

        // Set a click listener for the "Crear cuenta" button
        crearcuenta.setOnClickListener(View.OnClickListener {
            // Handle the click event here
        })
    }

    companion object {
        fun newInstance(param1: String?, param2: String?): Inicio {
            val fragment = Inicio()
            val args = Bundle()
            return fragment
        }
    }
}*/
