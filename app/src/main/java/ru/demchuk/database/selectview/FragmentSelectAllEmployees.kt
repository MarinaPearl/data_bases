package ru.demchuk.database.selectview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentSelectAllEmployeesBinding


class FragmentSelectAllEmployees : Fragment() {

    private lateinit var binding : FragmentSelectAllEmployeesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectAllEmployeesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonBack.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentSelectAllEmployees_to_fragmentSelectEmployee)
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
            bundle.putString("key", "employee")
            MAIN.navController.navigate(R.id.action_fragmentSelectAllEmployees_to_fragmentForSorterByGender, bundle)
        }
        binding.buttonExperience.setOnClickListener {
            openFragmentWithList("start_work")
        }
    }
    private fun openFragmentWithList(message : String) {
        val bundle = Bundle()
        bundle.putString("key", message)
        MAIN.navController.navigate(R.id.action_fragmentSelectAllEmployees_to_fragmentEnterForm, bundle)
    }

}