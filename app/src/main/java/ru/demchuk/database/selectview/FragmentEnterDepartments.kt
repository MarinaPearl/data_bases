package ru.demchuk.database.selectview

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.view.children
import androidx.lifecycle.Observer
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentEnterDepartmentsBinding
import ru.demchuk.database.vm.ViewModelSelect


class FragmentEnterDepartments : Fragment() {

    private lateinit var binding: FragmentEnterDepartmentsBinding
    private var vm = ViewModelSelect()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterDepartmentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = activity?.let {
            ArrayAdapter.createFromResource(
                it, getSpinner(),
                android.R.layout.simple_spinner_item
            )
        }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = adapterViewForSpinner
        listenButton()
    }

    private fun getSpinner(): Int {
        return when (arguments?.getString("key")) {
            "department" -> R.array.department
            "flight" -> R.array.flights
            else -> {
                R.array.brigades
            }
        }
    }

    companion object adapterViewForSpinner : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>, view: View?, pos: Int,
            id: Long
        ) {
            parent.children.forEach {
                val textView = it as TextView
                textView.setTextColor(Color.BLACK)
                textView.textSize = 15F
            }
        }

        override fun onNothingSelected(arg0: AdapterView<*>?) {}
    }

    private fun listenButton() {
        binding.buttonBack.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentEnterDepartments_to_fragmentSelectDepartments)
        }

        binding.buttonSelect.setOnClickListener {
            val stringSelect = getStringSelect()
            vm.selectInDb(stringSelect)
            val observer = Observer<ArrayList<String>> {
                val bundle = Bundle()
                bundle.putStringArrayList("key", it)
                MAIN.navController.navigate(
                    R.id.action_fragmentEnterDepartments_to_fragmentListEmployees,
                    bundle
                )
            }
            vm.liveData.observeForever(observer)

        }
    }

    private fun getStringSelect() : String {
        return when (arguments?.getString("key")) {
            "department" ->  "select * from EMPLOYEES join BRIGADES on EMPLOYEES.BRIGADE = BRIGADES.id where DEPARTMENT = ${binding.spinner.selectedItem}"
            "flight" -> """select * from EMPLOYEES
                    where brigade = (select PILOTS_BRIGADE from SCHEDULE join AIRCRAFTS on SCHEDULE.AIRCRAFT = AIRCRAFTS.ID where SCHEDULE.flight = ${binding.spinner.selectedItem})
                    or brigade = (select  TECHNICIANS_BRIGADE from SCHEDULE join AIRCRAFTS on SCHEDULE.AIRCRAFT = AIRCRAFTS.ID where SCHEDULE.flight = ${binding.spinner.selectedItem})
                    or brigade = (select SERVICE_BRIGADE from SCHEDULE join AIRCRAFTS on SCHEDULE.AIRCRAFT = AIRCRAFTS.ID where SCHEDULE.flight = ${binding.spinner.selectedItem})"""
            else -> {
                "select * from EMPLOYEES where BRIGADE = ${binding.spinner.selectedItem}"
            }
        }
    }

}