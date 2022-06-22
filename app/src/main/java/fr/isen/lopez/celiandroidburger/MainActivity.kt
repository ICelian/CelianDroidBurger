package fr.isen.lopez.celiandroidburger

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import fr.isen.lopez.celiandroidburger.databinding.ActivityConfirmationBinding
import fr.isen.lopez.celiandroidburger.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tvTime: TextView
    private lateinit var btnTimePicker: Button
    private lateinit var nomClient: EditText
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        spinner= findViewById(R.id.burger_spinner)
        val burgers = arrayOf("Burger du chef","Cheese burger","Burger Montagnard","Burger Italien","Burger Végétarien")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, burgers)

        spinner.adapter = adapter
        var selectedItem =""

        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                selectedItem = burgers[p2]
                Toast.makeText(this@MainActivity,"Votre choix : $selectedItem",Toast.LENGTH_LONG).show()


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        })








        val editor = sharedPref.edit()


        binding.apply {
            buttonValidation.setOnClickListener {

                val nomClient = nomClient.text.toString()
                val prenomClient = prenomClient.text.toString()


                editor.apply {
                    putString("nom_client", nomClient)
                    putString("prenom_client", prenomClient)
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

            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
                tvTime.setText("$i:$i2")
            }, startHour, startMinute, false).show()


        }

          fun changeActivityToConfirmation() {
            startActivity(Intent(this, ConfirmationActivity::class.java))
        }

        val nom = findViewById<EditText>(R.id.nomClient)
        val prenom = findViewById<EditText>(R.id.prenomClient)
        val adresse = findViewById<EditText>(R.id.adressClient)
        val numero = findViewById<EditText>(R.id.telephoneClient)
        val heure = findViewById<TextView>(R.id.tvTime)
        val burger = findViewById<Spinner>(R.id.burger_spinner)

        val commander = findViewById<Button>(R.id.buttonValidation)
        binding.buttonValidation.setOnClickListener {
            commander.setOnClickListener { view: View ->

                val txtnom = nom.text.toString()
                val txtprenom = prenom.text.toString()
                val txtadresse = adresse.text.toString()
                val txtnumero = numero.text.toString()
                val txtheure = heure.text.toString()



                if (txtnom.trim().isEmpty() || txtprenom.trim().isEmpty() || txtadresse.trim()
                        .isEmpty() || txtnumero.trim().isEmpty() || txtheure.trim().isEmpty()
                ) {
                    Toast.makeText(
                        this,
                        "les champs ne peuvent pas être vides !",
                        Toast.LENGTH_LONG
                    )
                        .show()
                } else {
                    var ConfirmationActivity =
                        Intent(this, ConfirmationActivity::class.java).apply {
                            putExtra("referName", txtnom)
                            putExtra("referAddress", txtadresse)
                            putExtra("referPhone", txtnumero)
                            putExtra("referTimeDelivery", txtheure)
                            putExtra("referSelectedBurger", selectedItem)

                        }
                    val sharedPref = this?.getSharedPreferences(getString(R.string.sharedpref), Context.MODE_PRIVATE)
                    //val sharedPref = getPreferences(Context.MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putString("saved_name", txtnom)
                        putString("saved_address", txtadresse)
                        putString("saved_phone", txtnumero)
                        apply()
                    }

                    startActivity(ConfirmationActivity)

                }

            }

        }

    }

}










