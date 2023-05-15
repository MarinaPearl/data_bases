package ru.demchuk.database.selectview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import ru.demchuk.database.Employee
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentEnterFormBinding
import ru.demchuk.database.vm.ViewModelSelect

class FragmentEnterForm : Fragment() {

    private lateinit var binding : FragmentEnterFormBinding
    private var vm = ViewModelSelect()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val key = arguments?.getString("key")
        binding.buttonSelect.setOnClickListener {
            if (binding.enterText.text.isNotEmpty()) {
                val stringSelect = "select * from EMPLOYEES where $key = ${binding.enterText.text}"
                vm.selectInDb(stringSelect)
                val observer = Observer<ArrayList<String>> {
                    val bundle = Bundle()
                    bundle.putStringArrayList("key", it)
                    MAIN.navController.navigate(R.id.action_fragmentEnterForm_to_fragmentListEmployees, bundle)
                }
                vm.liveData.observeForever(observer)
            } else {
                val toast = Toast.makeText(context, "Поле пустое!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        binding.buttonBackMain2.setOnClickListener {
            MAIN.navController.navigate(R.id.action_fragmentEnterForm_to_fragmentSelectAllEmployees)
        }
    }


}