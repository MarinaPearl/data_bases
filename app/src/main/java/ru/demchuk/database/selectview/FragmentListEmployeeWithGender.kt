package ru.demchuk.database.selectview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentListEmployeeWithGenderBinding


class FragmentListEmployeeWithGender : Fragment() {


    private lateinit var binding :FragmentListEmployeeWithGenderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListEmployeeWithGenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listEmployee = arguments?.getStringArrayList("key")
        if (listEmployee != null) {
            val adapter =
                context?.let { ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, listEmployee) }
            binding.listView.adapter = adapter
        }
        binding.buttonBackMain2.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentListEmployeeWithGender_to_mainFragment)
        }
    }


}