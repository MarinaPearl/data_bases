package ru.demchuk.database.selectview

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.view.children
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
        val adapter = activity?.let {
            ArrayAdapter.createFromResource(
                it, R.array.gender_all,
                android.R.layout.simple_spinner_item
            )
        }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGenderSelect.adapter = adapter
        binding.spinnerGenderSelect.onItemSelectedListener = adapterViewForSpinner

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

}