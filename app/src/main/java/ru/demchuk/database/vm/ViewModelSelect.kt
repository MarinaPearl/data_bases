package ru.demchuk.database.vm

import androidx.lifecycle.MutableLiveData
import ru.demchuk.database.Employee
import ru.demchuk.database.connect.ConnectSelect

class ViewModelSelect {
    val liveData = MutableLiveData<ArrayList<String>>()

    fun selectInDb(request : String) {
        val connection = ConnectSelect(this)
        connection.select(request)
    }

    fun sendOnView(listAnswer : ArrayList<String>) {
        liveData.postValue(listAnswer)
    }
}