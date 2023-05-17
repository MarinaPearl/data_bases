package ru.demchuk.database.flightView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentCancelledFlightStartBinding


class FragmentCancelledFlightStart : Fragment() {

    private lateinit var binding : FragmentCancelledFlightStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCancelledFlightStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenButtonForSelect()
    }

    private fun listenButtonForSelect() {
        binding.buttonBackMain.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentCancelledFlightStart_to_fragmentFlightStart)
        }
        binding.buttonRoute.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", "cancelled")
            MAIN.navController.navigate(R.id.action_fragmentCancelledFlightStart_to_fragmentRoute, bundle)
        }
        binding.buttonProc.setOnClickListener {
            selectInDataBase("percent")
        }
        binding.buttonSeat.setOnClickListener {
            selectInDataBase("seats")
        }
    }

    private fun selectInDataBase(message: String) {
        val bundle = Bundle()
        bundle.putString("key", message)
        MAIN.navController.navigate(R.id.action_fragmentCancelledFlightStart_to_enterFragmentForFlight, bundle)
    }
}