package ru.demchuk.database.connect

import android.util.Log
import ru.demchuk.database.Passanger
import ru.demchuk.database.vm.ViewModelUpdatePassanger
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import kotlin.concurrent.thread

class ConnectUpdate(val vm : ViewModelUpdatePassanger) {
    fun updateDataBase(passanger: Passanger) {
        Class.forName("oracle.jdbc.driver.OracleDriver")
        val dbURL = "jdbc:oracle:thin:@84.237.50.81:1521:XE"
        thread {
            try {
                // Locale.setDefault(Locale.ENGLISH);
                val myConnection: Connection =
                    DriverManager.getConnection(dbURL, "m.demchuk20207", "faya8sue")
                val myQuery: Statement = myConnection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
                )
                myQuery.executeUpdate("update PASSENGERS set RUS_PASSPORT = ${passanger.rus},INTER_PASSPORT = ${passanger.inter} where last_name = '${passanger.lastName}' and name = '${passanger.name}' and ticket = ${passanger.ticket}")
                myConnection.close()

                vm.requestOutDb(passanger)
            } catch (error: Exception) {
                Log.d("Error in select", error.toString())
            }
        }
    }


}