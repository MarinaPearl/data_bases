package ru.demchuk.database.connect

import android.util.Log
import ru.demchuk.database.Passenger
import ru.demchuk.database.vm.ViewModelUpdatePassenger
import kotlin.concurrent.thread

class ConnectUpdate(val vm: ViewModelUpdatePassenger) {
    fun updateDataBase(passenger: Passenger) {
        thread {
            try {
                val connection = Connection()
                val statement = connection.connectWithDb()
                statement.executeUpdate("update PASSENGERS set RUS_PASSPORT = ${passenger.rus},INTER_PASSPORT = ${passenger.inter} where last_name = '${passenger.lastName}' and name = '${passenger.name}' and ticket = ${passenger.ticket}")
                connection.close()
                vm.requestOutDb(passenger)
            } catch (error: Exception) {
                Log.d("Error in select", error.toString())
            }
        }
    }


}