package ru.demchuk.database

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.demchuk.database.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private lateinit var binding : FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonInsertEmployee.setOnClickListener {
            MAIN.navController.navigate(R.id.action_mainFragment_to_fragmentInsertEmployee3)
        }
        binding.buttonUpdatePassenger.setOnClickListener {
           MAIN.navController.navigate(R.id.action_mainFragment_to_fragmentUpdateOrDropPassenger2)
        }
        listenBottomNavigation()
    }

    private fun listenBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_employees -> {MAIN.navController.navigate(R.id.action_mainFragment_to_fragmentSelectEmployee)}
            }
            true
        }
    }

}