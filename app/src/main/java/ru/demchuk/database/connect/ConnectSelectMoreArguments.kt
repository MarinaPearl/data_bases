package ru.demchuk.database.connect

import android.util.Log
import ru.demchuk.database.vm.ViewModelSelectMore
import kotlin.concurrent.thread

class ConnectSelectMoreArguments(val vm: ViewModelSelectMore) {
    private var listAnswer = ArrayList<String>()

    fun select(request: String) {
        thread {
            try {
                val connection = Connection()
                val statement = connection.connectWithDb()
                val selectAnswer = statement.executeQuery(request)
                while (selectAnswer.next()) {
                    listAnswer.add(
                        selectAnswer.getString(vm.firstArgument).substringBefore(" ") + "  " + selectAnswer.getString(
                            vm.secondArgument
                        ) + " " + selectAnswer.getString(vm.thirdArgument)
                    )
                }
                connection.close()
                vm.sendOnView(listAnswer)
            } catch (error: Exception) {
                Log.d("Error in select", error.toString())
            }
        }
    }
}