package com.example.applicationbancaire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        Inscription.setOnClickListener {
            val nom_input = nom.text.toString()
            val prenom_input = prenom.text.toString()
            val iban_input = iban.text.toString()
            val id_connect_input = id_connect.text.toString()
            val password_input = password.text.toString()
            val conf_password_input = confirmer_password.text.toString()

            if (nom_input.trim().isEmpty() || prenom_input.trim().isEmpty() || iban_input.trim()
                    .isEmpty() ||
                id_connect_input.trim().isEmpty() || conf_password_input.trim()
                    .isEmpty() || password_input.trim().isEmpty()
            ) {
                Toast.makeText(this, "Vous devez remplir tous les champs", Toast.LENGTH_LONG)
                    .show()
            }//if si tous les champs ne sont pas remplis
            else {
                if (iban_input.length != 27) {
                    Toast.makeText(this, "Votre IBAN est incorrect", Toast.LENGTH_LONG)
                        .show()
                }//verifier si iban fait 27 caractères
                if (password_input.length != 8) {
                    Toast.makeText(
                        this, "Votre mot de passe doit avoir 8 caractères", Toast.LENGTH_LONG
                    )
                        .show()
                }//verifier si le mdp est de 8 caracteres
                else {
                    /*val nom = "bouleau"
                            val prenom = "android"
                            val iban = "123456789123456789123456789"
                            val id = "123456"
                            val mdp = "12345678"
                            val mdp2 = "12345678"*/
                    if (password_input != conf_password_input) {
                        error2.text = "Vos mots de passe ne se correspondent pas"
                        error2.visibility = View.VISIBLE

                    } else {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }
            }//si tous les champs sont remplis
        }//fin incriptionbutton
    }//fin override
}//fin class
