package ru.demchuk.database.vm

import androidx.lifecycle.MutableLiveData
import ru.demchuk.database.Passenger
import ru.demchuk.database.connect.ConnectDrop

class ViewModelDrop {
    val liveData = MutableLiveData<Passenger>()

    fun dropRowsDb(passenger: Passenger) {
        val sender = ConnectDrop(this)
        sender.dropRowsDataBase(passenger)
    }

    fun requestOutDb(passenger: Passenger) {
        liveData.postValue(passenger)
    }
}