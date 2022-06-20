package fr.isen.lopez.celiandroidburger

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import fr.isen.lopez.celiandroidburger.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tvTime : TextView
    private lateinit var btnTimePicker: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        tvTime = findViewById(R.id.tvTime)
        btnTimePicker = findViewById(R.id.btnTimePicker)


        btnTimePicker.setOnClickListener {
            val currentTime = java.util.Calendar.getInstance()
            val startHour = currentTime.get(java.util.Calendar.HOUR_OF_DAY)
            val startMinute = currentTime.get(java.util.Calendar.MINUTE)

            TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
                tvTime.setText("$i:$i2")
            }, startHour,startMinute,false).show()

        }




    }




}