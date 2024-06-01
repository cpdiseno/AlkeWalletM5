package pena.camila.alkewalletm5.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import pena.camila.alkewalletm5.databinding.FragmentRequestMoneyBinding
import pena.camila.alkewalletm5.databinding.FragmentSendMoneyBinding

class RequestMoney : Fragment() {
    private lateinit var binding: FragmentRequestMoneyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRequestMoneyBinding.inflate(inflater, container, false)

        // Acción para la flecha volver
        binding.volver.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}