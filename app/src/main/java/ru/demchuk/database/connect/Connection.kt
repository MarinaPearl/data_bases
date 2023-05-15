package ru.demchuk.database.connect

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class Connection {

    private lateinit var myConnection : Connection
    private lateinit var myQuery : Statement

    fun connectWithDb()  : Statement {
        Class.forName("oracle.jdbc.driver.OracleDriver")
        val dbURL = "jdbc:oracle:thin:@84.237.50.81:1521:XE"
        myConnection = DriverManager.getConnection(dbURL, "m.demchuk20207", "faya8sue")
        myQuery = myConnection.createStatement(
            ResultSet.TYPE_SCROLL_SENSITIVE,
            ResultSet.CONCUR_UPDATABLE
        )
        return myQuery
    }

    fun close() {
        myQuery.close()
        myConnection.close()
    }
}