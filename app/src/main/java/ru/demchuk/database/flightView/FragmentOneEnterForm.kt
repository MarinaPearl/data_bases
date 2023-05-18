package ru.demchuk.database.flightView

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentOneEnterFormBinding
import ru.demchuk.database.vm.ViewModelSelectMore


class FragmentOneEnterForm : Fragment() {


    private lateinit var binding : FragmentOneEnterFormBinding
    private var vm = ViewModelSelectMore()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentOneEnterFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSelect.setOnClickListener {
            vm.selectInDb(returnStringSelect())
            Log.d("aaaaaa", returnStringSelect())
            val observer = Observer<ArrayList<String>> {
                val bundle = Bundle()
                bundle.putStringArrayList("key", it)
                MAIN.navController.navigate(
                    R.id.action_fragmentOneEnterForm_to_fragmentListEmployees,
                    bundle
                )
            }
            vm.liveData.observeForever(observer)
        }
        binding.buttonBackMain2.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentOneEnterForm_to_fragmentPassengers)
        }
    }

    private fun returnStringSelect() : String {
        vm.firstArgument = 2
        vm.secondArgument = 3
        vm.thirdArgument = 19
        return  when(arguments?.getString("key")) {
            "pas" -> {
                """select *
                        from PASSENGERS join TICKETS on PASSENGERS.TICKET = TICKETS.id join SCHEDULE on SCHEDULE.id = TICKETS.FLIGHT join FLIGHTS on FLIGHTS.id = SCHEDULE.FLIGHT
                        where DATE_FLIGHT = to_date('${binding.enterText.text}', 'dd/mm/yyyy')"""
            }else -> {
                """select *
                        from PASSENGERS join TICKETS on PASSENGERS.TICKET = TICKETS.id join SCHEDULE on SCHEDULE.id = TICKETS.FLIGHT join FLIGHTS on FLIGHTS.id = SCHEDULE.FLIGHT
                        where DATE_FLIGHT = to_date('${binding.enterText.text}', 'dd/mm/yyyy') and CATEGORY = 2"""
            }
        }
    }
}