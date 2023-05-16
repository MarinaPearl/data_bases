package ru.demchuk.database.aircrafts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentChoiceCategoryPilotsBinding


class FragmentChoiceCategoryPilots : Fragment() {

    private lateinit var binding : FragmentChoiceCategoryPilotsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoiceCategoryPilotsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonAge.setOnClickListener {
            openNextFragmentWithEditText("pilot age")
        }
        binding.buttonAll.setOnClickListener {
            openNextFragmentWithEditText("pilot all")
        }
        binding.buttonSalary.setOnClickListener {
            openNextFragmentWithEditText("pilot salary")
        }
        binding.buttonMed.setOnClickListener {
            openNextFragmentWithEditText("med")
        }
        binding.buttonGender.setOnClickListener {
            openNextFragmentWithSpinner("pilot")
        }
        binding.buttonBackMain2.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentChoiceCategoryPilots_to_fragmentStartForAircraft)
        }
    }

    private fun openNextFragmentWithEditText(message : String) {
        val bundle = Bundle()
        bundle.putString("key", message)
        MAIN.navController.navigate(R.id.action_fragmentChoiceCategoryPilots_to_fragmentEnterForm, bundle)
    }

    private fun openNextFragmentWithSpinner(message : String) {
        val bundle = Bundle()
        bundle.putString("key", message)
        MAIN.navController.navigate(R.id.action_fragmentChoiceCategoryPilots_to_fragmentForSorterByGender, bundle)
    }
}