package ru.demchuk.database.flightView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentPassengersBinding

class FragmentPassengers : Fragment() {

    private lateinit var binding : FragmentPassengersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPassengersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonBackMain.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentPassengers_to_fragmentTicket)
        }
        binding.buttonPas.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", "pas")
            MAIN.navController.navigate(R.id.action_fragmentPassengers_to_fragmentRoute, bundle)
        }
        binding.buttonGender.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("key", "pas")
            MAIN.navController.navigate(R.id.action_fragmentPassengers_to_fragmentForSorterByGender, bundle)
        }
        binding.buttonDay.setOnClickListener {
            openNextFragment("pas")
        }
        binding.buttonDay2.setOnClickListener {
            openNextFragment("inter")
        }
    }

    private fun openNextFragment(message : String) {
        val bundle = Bundle()
        bundle.putString("key", message)
        MAIN.navController.navigate(R.id.action_fragmentPassengers_to_fragmentOneEnterForm, bundle)
    }

}