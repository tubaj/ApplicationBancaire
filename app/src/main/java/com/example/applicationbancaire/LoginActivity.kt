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
    // Write a message to the database



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        connexionButton.setOnClickListener {
            val txtidentifiant = connexioninput.text.toString()
            val passwordtext = passwordtext.text.toString()
            if (txtidentifiant.trim().isEmpty() || passwordtext.trim().isEmpty()) {
                Toast.makeText(this, "vous devez remplir tous les champs ", Toast.LENGTH_LONG)
                    .show()
            } //fin if si tous les champs ne sont pas remplis
            else {
                val correcteid = "123456"
                val correctpassword = "android"
                if (correcteid == txtidentifiant && correctpassword == passwordtext) {
                    val intent = Intent(this, AccueilActivity::class.java)
                    startActivity(intent)
                }// si l'id et mdp correspondent avec la bdd
                else{
                error.text = "votre identifiant ou mot de passe est incorrect "
                error.visibility = View.VISIBLE
                }

            }//si tous les champs sont remplis

            inscriptionButton.setOnClickListener {
                val intentInscription = Intent(this,SignUpActivity::class.java).also {
                    startActivity(it)
                }
            }//fin quand on clique sur le bouton inscription
        }//fin connexiononlistener

    }//fin override

}//fin class

