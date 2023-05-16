package ru.demchuk.database.selectview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentEnterFormForAdministrationBinding
import ru.demchuk.database.vm.ViewModelSelect


class FragmentEnterFormForAdministration : Fragment() {

    private lateinit var binding: FragmentEnterFormForAdministrationBinding
    private var vm = ViewModelSelect()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterFormForAdministrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val key = arguments?.getString("key")
        binding.buttonSelect.setOnClickListener {
            if (binding.minText.text.isNotEmpty() && binding.maxText.text.isNotEmpty() && key != null) {
                val stringSelect = returnStringSelect(key)
                vm.selectInDb(stringSelect)
                val observer = Observer<ArrayList<String>> {
                    val bundle = Bundle()
                    bundle.putStringArrayList("key", it)
                    MAIN.navController.navigate(
                        R.id.action_fragmentEnterFormForAdministration_to_fragmentListEmployees2,
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
            MAIN.navController.navigate(R.id.action_fragmentEnterFormForAdministration_to_fragmentAllAdministration)
        }
    }

    private fun returnStringSelect(key: String): String {
        return when (key) {
            "start_work" -> {
                "select * from EMPLOYEES join ADMINISTRATION on EMPLOYEE = EMPLOYEES.id where $key >= to_date('${binding.minText.text}', 'dd/mm/yyyy') and $key <= to_date('${binding.maxText.text}', 'dd/mm/yyyy')"
            }
            "all" -> {
                "select * from EMPLOYEES join ADMINISTRATION on EMPLOYEE = EMPLOYEES.id"
            }
            else -> {
                "select * from EMPLOYEES join ADMINISTRATION on EMPLOYEE = EMPLOYEES.id where $key >= ${binding.minText.text} and $key <= ${binding.maxText.text}"
            }
        }
    }
}