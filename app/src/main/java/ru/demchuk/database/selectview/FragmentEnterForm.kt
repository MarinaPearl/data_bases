package ru.demchuk.database.selectview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import ru.demchuk.database.Employee
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentEnterFormBinding
import ru.demchuk.database.vm.ViewModelSelect

class FragmentEnterForm : Fragment() {

    private lateinit var binding: FragmentEnterFormBinding
    private var vm = ViewModelSelect()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val key = arguments?.getString("key")
        binding.buttonSelect.setOnClickListener {
            if (binding.minText.text.isNotEmpty() && binding.maxText.text.isNotEmpty() && key != null) {
                val stringSelect = if (arguments?.getString("type") == "aircraft") {
                    returnStringSelectAircraft(key)
                } else {
                    returnStringSelect(key)
                }
                vm.selectInDb(stringSelect)
                val observer = Observer<ArrayList<String>> {
                    val bundle = Bundle()
                    bundle.putStringArrayList("key", it)
                    MAIN.navController.navigate(
                        R.id.action_fragmentEnterForm_to_fragmentListEmployees,
                        bundle
                    )
                }
                vm.liveData.observeForever(observer)
            } else {
                val toast = Toast.makeText(context, "Поле пустое!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        binding.buttonBackMain2.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentEnterForm_to_fragmentSelectAllEmployees)
        }
    }

    private fun returnStringSelect(key: String): String {
        return when (key) {
            "start_work" -> {
                "select * from EMPLOYEES where $key >= to_date('${binding.minText.text}', 'dd/mm/yyyy') and $key <= to_date('${binding.maxText.text}', 'dd/mm/yyyy')"
            }
            "all" -> {
                "select * from EMPLOYEES"
            }
            "pilot salary" -> {
                "select * from EMPLOYEES join PILOTS on PILOTS.EMPLOYEE = EMPLOYEES.id where salary >= ${binding.minText.text} and salary <= ${binding.maxText.text}"
            }
            "pilot age" -> {
                "select * from EMPLOYEES join PILOTS on PILOTS.EMPLOYEE = EMPLOYEES.id where age >= ${binding.minText.text} and age <= ${binding.maxText.text}"
            }
            "pilot all" -> {
                "select * from EMPLOYEES join PILOTS on PILOTS.EMPLOYEE = EMPLOYEES.id"
            }
            "med" -> {
                """select * from EMPLOYEES join PILOTS on PILOTS.EMPLOYEE = EMPLOYEES.id join MED_EXAM on MED_EXAM.PILOT = PILOTS.id 
                where DATE_MED >= to_date('01/01/${binding.minText.text}', 'dd/mm/yyyy') and DATE_MED <= to_date('01/01/${binding.maxText.text}', 'dd/mm/yyyy') """
            }
            else -> {
                "select * from EMPLOYEES where $key >= ${binding.minText.text} and $key <= ${binding.maxText.text}"
            }
        }
    }

    private fun returnStringSelectAircraft(key: String) : String{
        vm.firstArguments = 5
        vm.secondArguments = 7
        return when (key) {
            "number lights" -> {"select * from AIRCRAFTS where $key >= ${binding.minText.text} and $key <= ${binding.maxText.text}"}
            "import" -> {"select * from AIRCRAFTS where $key >= to_date('01/01/${binding.minText.text}', 'dd/mm/yyyy') and $key <= to_date('01/01/${binding.maxText.text}', 'dd/mm/yyyy')"}
            else -> {"select * from AIRCRAFTS join INSPECTION_AIRCRAFT on AIRCRAFT = AIRCRAFTS.id where $key >= to_date('01/01/${binding.minText.text}', 'dd/mm/yyyy') and $key <= to_date('01/01/${binding.maxText.text}', 'dd/mm/yyyy')"}
        }
    }
}