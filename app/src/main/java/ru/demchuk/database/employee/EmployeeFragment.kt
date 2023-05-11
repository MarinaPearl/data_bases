package ru.demchuk.database.employee

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
import ru.demchuk.database.Passanger
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentEmployeeBinding
import ru.demchuk.database.vm.ViewModelDrop
import ru.demchuk.database.vm.ViewModelUpdatePassanger


class EmployeeFragment : Fragment() {

    private lateinit var binding: FragmentEmployeeBinding
    private var vm = ViewModelUpdatePassanger()
    private var vm_drop = ViewModelDrop()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEmployeeBinding.inflate(inflater, container, false)
        val adapter = activity?.let {
            ArrayAdapter.createFromResource(
                it, R.array.tickets,
                android.R.layout.simple_spinner_item
            )
        }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTickets.adapter = adapter
        binding.spinnerTickets.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        updatePassanger()
        return binding.root
    }

    private fun updatePassanger() {
        binding.buttonDbUpdate.setOnClickListener {
            if (binding.nameUpdate.length() > 0 && binding.lastNameUpdate.length() > 0 &&
                    binding.rusUpdate.length() > 0 && binding.interUpdate.length() > 0) {
                val newPassanger = Passanger(binding.nameUpdate.text.toString(),
                    binding.lastNameUpdate.text.toString(),
                    binding.spinnerTickets.selectedItem.toString(),
                    binding.rusUpdate.text.toString(),
                            binding.interUpdate.text.toString())
                vm.updateDb(newPassanger)
                val observer = Observer<Passanger> {
                    val toast = Toast.makeText(
                        context,
                        "Изменение произошло!", Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
                vm.liveData.observeForever(observer)

            }else {
                val toast = Toast.makeText(
                    context,
                    "Есть пустое поле", Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }

        binding.buttonDbDrop.setOnClickListener {
            if (binding.nameUpdate.length() > 0 && binding.lastNameUpdate.length() > 0 &&
                binding.rusUpdate.length() > 0 && binding.interUpdate.length() > 0) {
                val newPassanger = Passanger(binding.nameUpdate.text.toString(),
                    binding.lastNameUpdate.text.toString(),
                    binding.spinnerTickets.selectedItem.toString(),
                    binding.rusUpdate.text.toString(),
                    binding.interUpdate.text.toString())
                vm_drop.dropRowsDb(newPassanger)
                val observer = Observer<Passanger> {
                    val toast = Toast.makeText(
                        context,
                        "Удаление произошло!", Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
                vm_drop.liveData.observeForever(observer)

            }else {
                val toast = Toast.makeText(
                    context,
                    "Есть пустое поле", Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }
    }


}