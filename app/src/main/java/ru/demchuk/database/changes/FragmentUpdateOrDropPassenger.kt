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
import ru.demchuk.database.MAIN
import ru.demchuk.database.Passenger
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentUpdateDropPassengerBinding
import ru.demchuk.database.vm.ViewModelDrop
import ru.demchuk.database.vm.ViewModelUpdatePassenger


class FragmentUpdateOrDropPassenger : Fragment() {

    private lateinit var binding: FragmentUpdateDropPassengerBinding
    private var vm = ViewModelUpdatePassenger()
    private var vmDrop = ViewModelDrop()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateDropPassengerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = activity?.let {
            ArrayAdapter.createFromResource(
                it, R.array.tickets,
                android.R.layout.simple_spinner_item
            )
        }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTickets.adapter = adapter
        binding.spinnerTickets.onItemSelectedListener = adapterViewForSpinner
        updatePassenger()
        listenButtonBackToMain()
    }

    private fun updatePassenger() {
        binding.buttonDbUpdate.setOnClickListener {
            if (binding.nameUpdate.length() > 0 && binding.lastNameUpdate.length() > 0 &&
                binding.rusUpdate.length() > 0 && binding.interUpdate.length() > 0
            ) {
                vm.updateDb(getUpdatePassenger())
                val observer = Observer<Passenger> {
                    getToast("Изменение произошло!")
                }
                vm.liveData.observeForever(observer)
            } else {
                getToast("Есть пустое поле")
            }
        }

        binding.buttonDbDrop.setOnClickListener {
            if (binding.nameUpdate.length() > 0 && binding.lastNameUpdate.length() > 0 &&
                binding.rusUpdate.length() > 0 && binding.interUpdate.length() > 0
            ) {
                vmDrop.dropRowsDb(getUpdatePassenger())
                val observer = Observer<Passenger> {
                    getToast("Удаление произошло!")
                }
                vmDrop.liveData.observeForever(observer)

            } else {
                getToast("Есть пустое поле")
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

    private fun getUpdatePassenger(): Passenger {
        return Passenger(
            binding.nameUpdate.text.toString(),
            binding.lastNameUpdate.text.toString(),
            binding.spinnerTickets.selectedItem.toString(),
            binding.rusUpdate.text.toString(),
            binding.interUpdate.text.toString()
        )
    }

    private fun getToast(message: String) {
        val toast = Toast.makeText(
            context,
            message, Toast.LENGTH_SHORT
        )
        toast.show()
    }

    private fun listenButtonBackToMain() {
        binding.buttonBackMain.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentUpdateOrDropPassenger_to_mainFragment)
        }
    }

}