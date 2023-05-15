package ru.demchuk.database.vm

import androidx.lifecycle.MutableLiveData
import ru.demchuk.database.Passenger
import ru.demchuk.database.connect.ConnectUpdate

class ViewModelUpdatePassenger {
    val liveData = MutableLiveData<Passenger>()

    fun updateDb(passenger: Passenger) {
        val a = ConnectUpdate(this)
        a.updateDataBase(passenger)
    }

    fun requestOutDb(passenger: Passenger) {
        liveData.postValue(passenger)
    }

}