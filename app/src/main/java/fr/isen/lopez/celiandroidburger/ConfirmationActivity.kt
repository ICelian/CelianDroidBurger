package fr.isen.lopez.celiandroidburger

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val sharedPref = this.getSharedPreferences(getString(R.string.sharedpref), Context.MODE_PRIVATE)

        val getname = sharedPref.getString("saved_name", "default_name")
        val getfirstname = sharedPref.getString("saved_name", "default_name")
        val getadresse = intent.getStringExtra("referAddress")
        val getnumero = intent.getStringExtra("referPhone")
        val getburger = intent.getStringExtra("referSelectedBurger")
        val getheure = intent.getStringExtra("referTimeDelivery")

        val nom = findViewById<TextView>(R.id.nomClientConfirmation)
        nom.text = getname
        val prenom = findViewById<TextView>(R.id.prenomConfirmation)
        prenom.text = getfirstname
        val addresse = findViewById<TextView>(R.id.addressConfirmation)
        addresse.text = getadresse
        val numero = findViewById<TextView>(R.id.telephoneConfirmation)
        numero.text = getnumero
        val burger = findViewById<TextView>(R.id.burgerConfirmation)
        burger.text = getburger
        val heure = findViewById<TextView>(R.id.heureConfirmation)
        heure.text = getheure


    }



}