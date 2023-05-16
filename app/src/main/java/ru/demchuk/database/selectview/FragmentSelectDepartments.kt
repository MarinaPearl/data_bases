package ru.demchuk.database.selectview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentSelectDepartmentsBinding


class FragmentSelectDepartments : Fragment() {

    private lateinit var binding: FragmentSelectDepartmentsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectDepartmentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonBrigade.setOnClickListener {
            openNextFragmentWithSpinner("brigade")
        }
        binding.buttonDepartment.setOnClickListener {
            openNextFragmentWithSpinner("department")
        }
        binding.buttonFlight.setOnClickListener {
            openNextFragmentWithSpinner("flight")
        }
        binding.buttonAge.setOnClickListener {
            openNextFragmentWithEditText("age")
        }
        binding.buttonSalary.setOnClickListener {
            openNextFragmentWithEditText("salary")
        }
        binding.buttonBack.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentSelectDepartments_to_fragmentSelectEmployee)
        }
    }

    private fun openNextFragmentWithSpinner(message: String) {
        val bundle = Bundle()
        bundle.putString("key", message)
        MAIN.navController.navigate(
            R.id.action_fragmentSelectDepartments_to_fragmentEnterDepartments,
            bundle
        )
    }

    private fun openNextFragmentWithEditText(message : String) {
        val bundle = Bundle()
        bundle.putString("key", message)
        MAIN.navController.navigate(
            R.id.action_fragmentSelectDepartments_to_fragmentDepartmentText,
            bundle
        )
    }
}