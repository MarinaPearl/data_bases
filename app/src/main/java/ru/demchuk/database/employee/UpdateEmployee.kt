package ru.demchuk.database.employee


import android.annotation.SuppressLint
import android.content.Context
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
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentUpdateEmployeeBinding
import ru.demchuk.database.vm.ViewModelUpdate


class UpdateEmployee : Fragment() {

    private lateinit var binding: FragmentUpdateEmployeeBinding
    private var vm = ViewModelUpdate()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateEmployeeBinding.inflate(inflater, container, false)
        val adapter = activity?.let {
            ArrayAdapter.createFromResource(
                it, R.array.worker,
                android.R.layout.simple_spinner_item
            )
        }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerWorker.adapter = adapter
        binding.spinnerWorker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

        val adapter_gender = activity?.let {
            ArrayAdapter.createFromResource(
                it, R.array.gender,
                android.R.layout.simple_spinner_item
            )
        }
        adapter_gender?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGender.adapter = adapter_gender
        binding.spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

        insertEmployee()
        return binding.root
    }


    private fun insertEmployee() {
        binding.buttonDb.setOnClickListener {
            if (binding.ageInsert.length() > 0 && binding.brigadeInsert.length() > 0 && binding.nameInsert.length() > 0
                && binding.lastNameInsert.length() > 0 && binding.salaryInsert.length() > 0
                && binding.countChildrenInsert.length() > 0 && binding.dateInsert.length() > 0
            ) {
                val newEmployee = Employee(
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
                vm.insertToDb(newEmployee)
                val observer = Observer<Employee> {
                    val toast = Toast.makeText(
                        context,
                        "Вставка произошла!", Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
                vm.liveData.observeForever(observer)
            } else {
                val toast = Toast.makeText(
                    context,
                    "Есть пустое поле", Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }
    }

}