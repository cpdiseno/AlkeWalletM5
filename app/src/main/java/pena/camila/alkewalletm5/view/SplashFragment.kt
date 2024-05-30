package pena.camila.alkewalletm5.view

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import pena.camila.alkewalletm5.R;
import pena.camila.alkewalletm5.databinding.FragmentSplashBinding;

class SplashFragment : Fragment() {

    private lateinit var binding:FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container:ViewGroup?,
        savedIntanceState:Bundle?
    ):View? {
// Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        binding.logo.setOnClickListener {
            it.findNavController().navigate(R.id.action_SplashFragment_to_inicio)
        }
        return binding.root
    }
}


/*  private FragmentSplashBinding binding

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        view.findViewById(R.id.logo);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Inicio
                NavController navController = Navigation.findNavController(requireActivity(), R.id.logo);
                navController.navigate(R.id.action_SplashFragment_to_inicio3);
            }
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}*/


