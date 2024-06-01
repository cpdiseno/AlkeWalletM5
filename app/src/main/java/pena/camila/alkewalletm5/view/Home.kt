package pena.camila.alkewalletm5.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import pena.camila.alkewalletm5.R

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Configurar el botón "button_enviar_dinero"
        view.findViewById<View>(R.id.button_enviar_dinero).setOnClickListener {
            findNavController().navigate(R.id.action_home_to_sendMoney)
        }

        // Configurar el botón "btn_ingresar_dinero"
        view.findViewById<View>(R.id.btn_ingresar_dinero).setOnClickListener {
            findNavController().navigate(R.id.action_home_to_requestMoney)
        }

        return view
    }

 }
