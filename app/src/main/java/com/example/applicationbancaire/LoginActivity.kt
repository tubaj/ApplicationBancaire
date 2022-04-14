package com.example.applicationbancaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.applicationbancaire.Database.BankDataBase
import com.example.applicationbancaire.data.User
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.error

class LoginActivity : AppCompatActivity() {
    // Write a message to the database

    lateinit var  db : BankDataBase
    val user1 = User(0, "boubou", "marie", "12345678", "123456", "123456789123456789123456789", "12345678912")
    val user2 = User(0, "baba", "mami", "123789456", "123456", "923456789123456789123456789", "12345678912")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        db = BankDataBase(this) //initialise le constructeur de la bdd

        connexionButton.setOnClickListener {
            val txtidentifiant = connexioninput.text.toString()
            val passwordtext = passwordtext.text.toString()
            if (txtidentifiant.trim().isEmpty() || passwordtext.trim().isEmpty()) {
                Toast.makeText(this, "vous devez remplir tous les champs ", Toast.LENGTH_LONG)
                    .show()
            } //fin if si tous les champs ne sont pas remplis
            else {
                /*val user = db.findUser(txtidentifiant, passwordtext)
                if (user != null){*/
                val correcteid = "123456"
                val correctpassword = "android"
                if (correcteid == txtidentifiant && correctpassword == passwordtext) {
                    val intent = Intent(this, AccueilActivity::class.java)
                    startActivity(intent)
                }// si l'identifiant et mdp correspondent avec la bdd
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

