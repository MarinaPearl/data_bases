package ru.demchuk.database.vm

import androidx.lifecycle.MutableLiveData
import ru.demchuk.database.Passanger
import ru.demchuk.database.connect.ConnectDrop
import ru.demchuk.database.connect.ConnectUpdate

class ViewModelDrop {
    val liveData = MutableLiveData<Passanger>()

    fun dropRowsDb(passanger: Passanger) {
        val sender = ConnectDrop(this)
        sender.dropRowsDataBase(passanger)
    }

    fun requestOutDb(passanger: Passanger) {
        liveData.postValue(passanger)
    }
}