package ru.demchuk.database.flightView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentTicketBinding


class FragmentTicket : Fragment() {

    private lateinit var binding : FragmentTicketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTicketBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonBackMain.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentTicket_to_mainFragment)
        }
        binding.buttonPas.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentTicket_to_fragmentPassengers)
        }
    }
}