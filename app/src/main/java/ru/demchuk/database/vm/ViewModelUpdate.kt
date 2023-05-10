package ru.demchuk.database.vm

import androidx.lifecycle.MutableLiveData
import ru.demchuk.database.Employee
import ru.demchuk.database.connect.Connect

class ViewModelUpdate {
    val liveData = MutableLiveData<Employee>()

    fun insertToDb(employee: Employee) {
        val a = Connect(this)
        a.connectWithDataBase(employee)
    }

    fun requestOutDb(employee: Employee) {
        liveData.postValue(employee)
    }
}