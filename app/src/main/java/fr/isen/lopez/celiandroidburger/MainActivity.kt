package fr.isen.lopez.celiandroidburger

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import fr.isen.lopez.celiandroidburger.databinding.ActivityConfirmationBinding
import fr.isen.lopez.celiandroidburger.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tvTime : TextView
    private lateinit var btnTimePicker: Button
    private lateinit var nomClient : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        val editor = sharedPref.edit()


        binding.apply {
            buttonValidation.setOnClickListener {

                val nomClient = nomClient.text.toString()
                val prenomClient = prenomClient.text.toString()

                editor.apply {
                    putString("nom_client",nomClient)
                    putString("prenom_client",prenomClient)
                    apply()
                }


            }


        }




        tvTime = findViewById(R.id.tvTime)
        btnTimePicker = findViewById(R.id.btnTimePicker)
        nomClient = findViewById(R.id.nomClient)


        btnTimePicker.setOnClickListener {
            val currentTime = java.util.Calendar.getInstance()
            val startHour = currentTime.get(java.util.Calendar.HOUR_OF_DAY)
            val startMinute = currentTime.get(java.util.Calendar.MINUTE)

            TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
                tvTime.setText("$i:$i2")
            }, startHour,startMinute,false).show()

        }
        binding.buttonValidation.setOnClickListener {
            changeActivityToConfirmation()
        }






    }


    private fun changeActivityToConfirmation() {
        startActivity(Intent(this, ConfirmationActivity::class.java))


    }





}