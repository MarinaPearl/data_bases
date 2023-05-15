package ru.demchuk.database.changes


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import ru.demchuk.database.Employee
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentInsertEmployeeBinding
import ru.demchuk.database.vm.ViewModelUpdate


class FragmentInsertEmployee : Fragment() {

    private lateinit var binding: FragmentInsertEmployeeBinding
    private var vm = ViewModelUpdate()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertEmployeeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = activity?.let {
            ArrayAdapter.createFromResource(
                it, R.array.worker,
                android.R.layout.simple_spinner_item
            )
        }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerWorker.adapter = adapter
        binding.spinnerWorker.onItemSelectedListener = adapterViewForSpinner

        val adapterGender = activity?.let {
            ArrayAdapter.createFromResource(
                it, R.array.gender,
                android.R.layout.simple_spinner_item
            )
        }
        adapterGender?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGender.adapter = adapterGender
        binding.spinnerGender.onItemSelectedListener = adapterViewForSpinner
        insertEmployee()
        listenButtonBackToMain()
    }

    private fun insertEmployee() {
        binding.buttonDb.setOnClickListener {
            if (binding.ageInsert.length() > 0 && binding.brigadeInsert.length() > 0 && binding.nameInsert.length() > 0
                && binding.lastNameInsert.length() > 0 && binding.salaryInsert.length() > 0
                && binding.countChildrenInsert.length() > 0 && binding.dateInsert.length() > 0) {
                vm.insertToDb(getNewEmployee())
                val observer = Observer<Employee> {
                    val toast = Toast.makeText(context, "Вставка произошла!", Toast.LENGTH_SHORT)
                    toast.show()
                }
                vm.liveData.observeForever(observer)
            } else {
                val toast = Toast.makeText(context, "Есть пустое поле", Toast.LENGTH_SHORT)
                toast.show()
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

    private fun getNewEmployee(): Employee {
        return Employee(
            binding.spinnerWorker.selectedItem.toString(),
            binding.nameInsert.text.toString(),
            binding.lastNameInsert.text.toString(),
            binding.spinnerGender.selectedItem.toString(),
            binding.dateInsert.text.toString(),
            binding.ageInsert.text.toString(),
            binding.countChildrenInsert.text.toString(),
            binding.salaryInsert.text.toString(),
            binding.brigadeInsert.text.toString()
        )
    }

    private fun listenButtonBackToMain() {
        binding.buttonBackMain2.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentInsertEmployee_to_mainFragment)
        }
    }
}