package ru.demchuk.database.aircrafts

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentChoiceCategoryAircraftBinding


class FragmentChoiceCategoryAircraft : Fragment() {

    private lateinit var binding: FragmentChoiceCategoryAircraftBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoiceCategoryAircraftBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonCount.setOnClickListener {
            openNextFragmentWithEditText("number lights")
        }
        binding.buttonTex.setOnClickListener {
            openNextFragmentWithEditText("tex")
        }
        binding.buttonTime.setOnClickListener {
            openNextFragmentWithEditText("import")
        }
        binding.buttonAll.setOnClickListener {
            openNextFragmentWithEditText("all")
        }
        binding.buttonBackMain2.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentChoiceCategoryAircraft_to_fragmentStartForAircraft)
        }

    }

    private fun openNextFragmentWithEditText(message : String) {
        val bundle = Bundle()
        bundle.putString("key", message)
        bundle.putString("type", "aircraft")
        MAIN.navController.navigate(R.id.action_fragmentChoiceCategoryAircraft_to_fragmentEnterForm, bundle)
    }


}