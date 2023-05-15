package ru.demchuk.database.connect

import android.util.Log
import ru.demchuk.database.vm.ViewModelSelect
import kotlin.concurrent.thread

class ConnectSelect(val vm : ViewModelSelect) {

    private var listAnswer = ArrayList<String>()

    fun select(request : String) {
        thread {
            try {
                val connection = Connection()
                val statement = connection.connectWithDb()
                val selectAnswer = statement.executeQuery(request)
                while (selectAnswer.next()) {
                    listAnswer.add(selectAnswer.getString(2) + "  " + selectAnswer.getString(3))
                }
                connection.close()
                vm.sendOnView(listAnswer)
            } catch (error: Exception) {
                Log.d("Error in select", error.toString())
            }
        }
    }
}