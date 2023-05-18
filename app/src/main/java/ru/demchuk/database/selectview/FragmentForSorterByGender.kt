package ru.demchuk.database.selectview

import android.graphics.Color
import android.os.Bundle
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
import ru.demchuk.database.databinding.FragmentForSorterByGenderBinding
import ru.demchuk.database.vm.ViewModelSelect


class FragmentForSorterByGender : Fragment() {

    private lateinit var binding: FragmentForSorterByGenderBinding
    private var vm = ViewModelSelect()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForSorterByGenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterGender = activity?.let {
            ArrayAdapter.createFromResource(
                it, R.array.gender,
                android.R.layout.simple_spinner_item
            )
        }
        adapterGender?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGender.adapter = adapterGender
        binding.spinnerGender.onItemSelectedListener = adapterViewForSpinner
        listenButtonForNextFragment()
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

    private fun listenButtonForNextFragment() {
        val key = arguments?.getString("key")
        binding.buttonSelect.setOnClickListener {
            val stringSelect = getStringSelect(key!!)
            vm.selectInDb(stringSelect)
            val observer = Observer<ArrayList<String>> {
                val bundle = Bundle()
                bundle.putStringArrayList("key", it)
                MAIN.navController.navigate(
                    R.id.action_fragmentForSorterByGender_to_fragmentListEmployeeWithGender,
                    bundle
                )
            }
            vm.liveData.observeForever(observer)
        }
    }

    private fun getStringSelect(key: String): String {
        return when (key) {
            "employee" -> {
                "select * from EMPLOYEES where GENDER = '${binding.spinnerGender.selectedItem}'"
            }
            "pilot" -> {
                "select * from EMPLOYEES join PILOTS on PILOTS.EMPLOYEE = EMPLOYEES.id where GENDER = '${binding.spinnerGender.selectedItem}'"
            }
            "pas" -> {"""select *
                    from PASSENGERS where gender = '${binding.spinnerGender.selectedItem}'"""}
            else -> {
                "select * from EMPLOYEES join ADMINISTRATION on EMPLOYEE = EMPLOYEES.id where GENDER = '${binding.spinnerGender.selectedItem}'"
            }
        }
    }
}