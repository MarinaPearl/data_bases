package ru.demchuk.database.flightView

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentRouteBinding
import ru.demchuk.database.vm.ViewModelSelectMore


class FragmentRoute : Fragment() {

    private lateinit var binding: FragmentRouteBinding
    private var vm = ViewModelSelectMore()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRouteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSpinners()
        listenButton()
        binding.buttonBackMain.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentRoute_to_fragmentFlightStart)
        }
    }

    private fun setSpinners() {
        val adapterStart = activity?.let {
            ArrayAdapter.createFromResource(
                it, R.array.start,
                android.R.layout.simple_spinner_item
            )
        }
        adapterStart?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerStart.adapter = adapterStart
        val adapterEnd = activity?.let {
            ArrayAdapter.createFromResource(
                it, R.array.end,
                android.R.layout.simple_spinner_item
            )
        }
        adapterEnd?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerEnd.adapter = adapterEnd
        binding.spinnerStart.onItemSelectedListener = adapterViewForSpinner
        binding.spinnerEnd.onItemSelectedListener = adapterViewForSpinner
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
        binding.buttonSelect.setOnClickListener {
            Log.d("aaaaaaaaaaaaaa", returnStringSelect())
            vm.selectInDb(returnStringSelect())
            val observer = Observer<ArrayList<String>> {
                val bundle = Bundle()
                bundle.putStringArrayList("key", it)
                MAIN.navController.navigate(
                    R.id.action_fragmentRoute_to_fragmentListEmployees,
                    bundle
                )
            }
            vm.liveData.observeForever(observer)
        }
    }

    private fun returnStringSelect(): String {
        return when(arguments?.getString("type")) {
             "cancelled" -> {"""select *
                from SCHEDULE join FLIGHTS on FLIGHTS.id = SCHEDULE.FLIGHT where START_POINT='${binding.spinnerStart.selectedItem}' and END_POINT='${binding.spinnerEnd.selectedItem}' and status = 2"""}
            else -> {
                """select *
                from SCHEDULE join FLIGHTS on FLIGHTS.id = SCHEDULE.FLIGHT where START_POINT='${binding.spinnerStart.selectedItem}' and END_POINT='${binding.spinnerEnd.selectedItem}'"""
            }
        }
    }

}