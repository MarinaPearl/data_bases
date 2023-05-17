package ru.demchuk.database.vm

import androidx.lifecycle.MutableLiveData
import ru.demchuk.database.connect.ConnectSelectMoreArguments

class ViewModelSelectMore {
    val liveData = MutableLiveData<ArrayList<String>>()
    var firstArgument = 4
    var secondArgument = 11
    var thirdArgument = 13

    fun selectInDb(request : String) {
        val connection = ConnectSelectMoreArguments(this)
        connection.select(request)
    }

    fun sendOnView(listAnswer : ArrayList<String>) {
        liveData.postValue(listAnswer)
    }
}