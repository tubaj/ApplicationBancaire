package com.example.applicationbancaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.error

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        connexionButton.setOnClickListener {
            val txtidentifiant = connexioninput.text.toString()
            val passwordtext = passwordtext.text.toString()
            if (txtidentifiant.trim().isEmpty() || passwordtext.trim().isEmpty()) {
                Toast.makeText(this, "vous devez remplir tous les champs ", Toast.LENGTH_LONG)
                    .show()
            } else {
                val correcteid = "251436"
                val correctpassword = "android"
                if (correcteid == txtidentifiant && correctpassword == passwordtext) {
                    val intent = Intent(this, AccueilActivity::class.java)
                    startActivity(intent)
                }
            else{
            error.text = "votre identifiant ou mot de passe est incorrect "
            error.visibility = View.VISIBLE
        }


        }
       // val inscriptionButton = findViewById<Button>(R.id.inscriptionButton);
            inscriptionButton.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }}}

