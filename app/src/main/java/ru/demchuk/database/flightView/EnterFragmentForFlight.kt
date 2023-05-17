package ru.demchuk.database.flightView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentEnterForFlightBinding
import ru.demchuk.database.vm.ViewModelSelectMore


class EnterFragmentForFlight : Fragment() {


    private lateinit var binding: FragmentEnterForFlightBinding
    var vm = ViewModelSelectMore()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterForFlightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenButtonBack()
        listenButtonForSelect()
    }

    private fun listenButtonBack() {
        binding.buttonBackMain2.setOnClickListener {
            MAIN.navController.navigate(R.id.action_enterFragmentForFlight_to_fragmentListEmployees)
        }
    }

    private fun listenButtonForSelect() {
        vm.firstArgument = 1
        vm.secondArgument = 2
        vm.thirdArgument = 3
        binding.buttonSelect.setOnClickListener {
            if (binding.minText.text.isNotEmpty() && binding.maxText.text.isNotEmpty()) {
                vm.selectInDb(returnStringSelect())
                val observer = Observer<ArrayList<String>> {
                    val bundle = Bundle()
                    bundle.putStringArrayList("key", it)
                    MAIN.navController.navigate(
                        R.id.action_enterFragmentForFlight_to_fragmentListEmployees,
                        bundle
                    )
                }
                vm.liveData.observeForever(observer)
            } else {
                val toast = Toast.makeText(context, "Поле пустое!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    private fun returnStringSelect(): String {
        return when (arguments?.getString("key")) {
            "time" -> """select TIME, START_POINT, END_POINT
                    from SCHEDULE join (select SCHEDULE.id ids, (TIME_ARRIVAL - TIME_DEPARTURE) TIME
                    from SCHEDULE) on SCHEDULE.id = ids 
                    join FLIGHTS on FLIGHTS.id = SCHEDULE.FLIGHT 
                    where TIME >= ${binding.minText.text} and TIME <= ${binding.maxText.text}"""
            "seats" -> {
                """select *
                    from (select START_POINT, END_POINT, (COUNT_SEATS - cm) k
                    from SCHEDULE join (select SCHEDULE.id ida, NVL(c, 0) cm
                    from schedule left join (select SCHEDULE.id ids, count(*) c
                    from SCHEDULE join TICKETS on TICKETS.FLIGHT = SCHEDULE.id
                    group by SCHEDULE.id) on ids = SCHEDULE.id) on SCHEDULE.id = ida
                    join FLIGHTS on FLIGHTS.id = SCHEDULE.FLIGHT
                    where status = 2)
                    where k >= ${binding.minText.text} and k <= ${binding.maxText.text}"""
            }
            "percent" -> {
                """select *
                        from (select START_POINT, END_POINT, (COUNT_SEATS - cm)*100/COUNT_SEATS k
                                from SCHEDULE join (select SCHEDULE.id ida, NVL(c, 0) cm
                                from schedule left join (select SCHEDULE.id ids, count(*) c
                                from SCHEDULE join TICKETS on TICKETS.FLIGHT = SCHEDULE.id
                                group by SCHEDULE.id) on ids = SCHEDULE.id) on SCHEDULE.id = ida
                join FLIGHTS on FLIGHTS.id = SCHEDULE.FLIGHT
                        where status = 2)
                where k > 5"""
            }
            else -> {
                """select MAX, START_POINT, END_POINT from SCHEDULE join
                    (select SCHEDULE.id ids, max(PRICE) max
                    from SCHEDULE join TICKETS on TICKETS.FLIGHT = SCHEDULE.id
                    group by SCHEDULE.id) on SCHEDULE.id = ids join FLIGHTS on FLIGHTS.id = SCHEDULE.FLIGHT
                    where max >= ${binding.minText.text} and max <= ${binding.maxText.text}"""
            }
        }
    }

}