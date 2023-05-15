package ru.demchuk.database.selectview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentListEmployeesBinding

class FragmentListEmployees : Fragment() {


    private lateinit var binding : FragmentListEmployeesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListEmployeesBinding.inflate(inflater, container, false)
        return binding.root
    }


}