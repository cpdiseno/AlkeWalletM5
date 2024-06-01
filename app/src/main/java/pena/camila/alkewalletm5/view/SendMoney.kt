package pena.camila.alkewalletm5.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import pena.camila.alkewalletm5.R
import pena.camila.alkewalletm5.databinding.FragmentSendMoneyBinding

class SendMoney : Fragment() {
    private lateinit var binding: FragmentSendMoneyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSendMoneyBinding.inflate(inflater, container, false)

        // Acción para la flecha volver
        binding.flechaBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}