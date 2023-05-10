package ru.demchuk.database

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.demchuk.database.R
import ru.demchuk.database.databinding.ActivityChangeBinding

class ActivityChange : AppCompatActivity() {

    private lateinit var binding: ActivityChangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change)

    }
}