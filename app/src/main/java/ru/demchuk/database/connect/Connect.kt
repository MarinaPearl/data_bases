package ru.demchuk.database.connect

import android.util.Log
import ru.demchuk.database.Employee
import ru.demchuk.database.vm.ViewModelUpdate
import kotlin.concurrent.thread

class Connect(val vm: ViewModelUpdate) {

    fun connectWithDataBase(employee: Employee) {
        thread {
            try {
                val connection = Connection()
                val statement = connection.connectWithDb()
                statement.executeUpdate("insert into EMPLOYEES (name, last_name, gender, start_work, age, children, salary, brigade) values ('${employee.name}', '${employee.lastName}',  '${employee.gender}', to_date('${employee.date}', 'dd/mm/yyyy'), ${employee.age}, ${employee.children}, ${employee.salary}, ${employee.brigade})")
////                while (s.next()) {
                // myQuery.executeUpdate("insert into EMPLOYEES (name, last_name, gender, start_work, age, children, salary, brigade) values ('Анастасия', 'Колесник',  'w', to_date('13/11/2021', 'dd/mm/yyyy'), 30, 0, 200000, 28)")
//                    println(s.getString(2) + "; "  + s.getString(3) + "; ")
//                    //listAnswerInModel.add(s.getString(2) + "; " + "\n" + s.getString(3) + "; ")
//                    //listAnswerInModel.add(s.getString(3) + "; ")
//                }
                connection.close()
                vm.requestOutDb(employee)
            } catch (error: Exception) {
                Log.d("Error in select", error.toString())
            }
        }
    }
}

fun main() {
//     val vm = ViewModelUpdate()
//   val a = Connect(vm)
//    a.updateDataBase()
}