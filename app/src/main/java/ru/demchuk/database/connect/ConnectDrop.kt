package ru.demchuk.database.connect

import android.util.Log
import ru.demchuk.database.Passenger
import ru.demchuk.database.vm.ViewModelDrop
import kotlin.concurrent.thread

class ConnectDrop(val vm: ViewModelDrop) {
    fun dropRowsDataBase(passenger: Passenger) {
        thread {
            try {
                val connection = Connection()
                val statement = connection.connectWithDb()
                statement.executeUpdate("delete from PASSENGERS where RUS_PASSPORT = ${passenger.rus} and INTER_PASSPORT = ${passenger.inter} and last_name = '${passenger.lastName}' and name = '${passenger.name}' and ticket = ${passenger.ticket}")
                connection.close()
                vm.requestOutDb(passenger)
            } catch (error: Exception) {
                Log.d("Error in select", error.toString())
            }
        }
    }
}