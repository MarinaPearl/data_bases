package ru.demchuk.database.selectview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentAllAdministrationBinding


class FragmentAllAdministration : Fragment() {

    private lateinit var binding : FragmentAllAdministrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllAdministrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonBack.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentAllAdministration_to_fragmentSelectEmployee)
        }
        listenButtonForNextFragment()
    }

    private fun listenButtonForNextFragment() {
        binding.buttonChildren.setOnClickListener {
            openFragmentWithList("children")
        }
        binding.buttonAge.setOnClickListener {
            openFragmentWithList("age")
        }
        binding.buttonSalary.setOnClickListener {
            openFragmentWithList("salary")
        }
        binding.buttonAll.setOnClickListener {
            openFragmentWithList("all")
        }
        binding.buttonGender.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("key", "administration")
            MAIN.navController.navigate(R.id.action_fragmentAllAdministration_to_fragmentForSorterByGender, bundle)
        }
        binding.buttonExperience.setOnClickListener {
            openFragmentWithList("start_work")
        }
    }
    private fun openFragmentWithList(message : String) {
        val bundle = Bundle()
        bundle.putString("key", message)
        MAIN.navController.navigate(R.id.action_fragmentAllAdministration_to_fragmentEnterFormForAdministration, bundle)
    }
}