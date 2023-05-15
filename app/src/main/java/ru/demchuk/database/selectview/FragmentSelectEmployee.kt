package ru.demchuk.database.selectview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentSelectEmpmloyeeBinding

class FragmentSelectEmployee : Fragment() {

   private lateinit var binding : FragmentSelectEmpmloyeeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectEmpmloyeeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonBackMain.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentSelectEmployee_to_mainFragment)
        }
        binding.buttonSelectAll.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentSelectEmployee_to_fragmentSelectAllEmployees)
        }
    }


}