package ru.demchuk.database.selectview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import ru.demchuk.database.MAIN
import ru.demchuk.database.R
import ru.demchuk.database.databinding.FragmentDeapermentsTextBinding
import ru.demchuk.database.vm.ViewModelSelect


class FragmentDepartmentText : Fragment() {

    private lateinit var binding : FragmentDeapermentsTextBinding
    private var vm = ViewModelSelect()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeapermentsTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSelect.setOnClickListener {
            if (binding.minText.text.isNotEmpty() && binding.maxText.text.isNotEmpty()) {
                val stringSelect = getSelectString()
                vm.selectInDb(stringSelect)
                val observer = Observer<ArrayList<String>> {
                    val bundle = Bundle()
                    bundle.putStringArrayList("key", it)
                    MAIN.navController.navigate(
                        R.id.action_fragmentDepartmentText_to_fragmentListEmployees,
                        bundle
                    )
                }
                vm.liveData.observeForever(observer)
            } else {
                val toast = Toast.makeText(context, "Поле пустое!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        binding.buttonBackMain2.setOnClickListener {
            MAIN.navController.navigate(
                R.id.action_fragmentDepartmentText_to_fragmentSelectDepartments
            )
        }
    }

    private fun getSelectString() : String {
        val key = arguments?.getString("key")
        return "select * from EMPLOYEES where BRIGADE = ${binding.brigade.text} and $key >= ${binding.minText.text} and $key <= ${binding.maxText.text}"
    }

}