package ru.demchuk.database.vm

import androidx.lifecycle.MutableLiveData
import ru.demchuk.database.Employee
import ru.demchuk.database.Passanger
import ru.demchuk.database.connect.Connect
import ru.demchuk.database.connect.ConnectUpdate

class ViewModelUpdatePassanger {
    val liveData = MutableLiveData<Passanger>()

    fun updateDb(passanger: Passanger) {
        val a = ConnectUpdate(this)
        a.updateDataBase(passanger)
    }

    fun requestOutDb(passanger: Passanger) {
        liveData.postValue(passanger)
    }

}