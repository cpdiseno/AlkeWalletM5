package pena.camila.alkewalletm5.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pena.camila.alkewalletm5.databinding.FragmentHeaderProfileBinding


class HeaderProfileFragment : Fragment() {

    private var _binding: FragmentHeaderProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeaderProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
}