package ru.demchuk.database.flightView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentFlightStartBinding

class FragmentFlightStart : Fragment() {

   private lateinit var binding : FragmentFlightStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonBackMain.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentFlightStart_to_mainFragment)
        }
        binding.buttonRoute.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentFlightStart_to_fragmentRoute)
        }
        binding.buttonPrice.setOnClickListener {
            selectInDataBase("price")
        }
        binding.buttonTime.setOnClickListener {
            selectInDataBase("time")
        }
        binding.buttonCancelled.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentFlightStart_to_fragmentCancelledFlightStart)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun selectInDataBase(message: String) {
        val bundle = Bundle()
        bundle.putString("key", message)
        MAIN.navController.navigate(R.id.action_fragmentFlightStart_to_enterFragmentForFlight, bundle)
    }
}