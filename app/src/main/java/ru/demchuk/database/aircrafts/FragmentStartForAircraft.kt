package ru.demchuk.database.aircrafts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentStartForAircraftBinding


class FragmentStartForAircraft : Fragment() {


    private lateinit var binding : FragmentStartForAircraftBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartForAircraftBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonBackMain2.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentStartForAircraft_to_mainFragment)
        }
        binding.buttonPilot.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentStartForAircraft_to_fragmentChoiceCategoryPilots)
        }
        binding.buttonAircraft.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentStartForAircraft_to_fragmentChoiceCategoryAircraft)
        }
    }


}