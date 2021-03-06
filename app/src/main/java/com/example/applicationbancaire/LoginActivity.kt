package com.example.applicationbancaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.applicationbancaire.Database.BankDataBase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    // Write a message to the database

    lateinit var  db : BankDataBase

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
                //val user = db.findUser("123456", "android")
                //if (user != null){
                //val correcteid = "123456"
                //val correctpassword = "android"
                //if (correcteid == txtidentifiant && correctpassword == passwordtext) {
                    val user = db.findUser(txtidentifiant,passwordtext)
                if (user != null) { //si on trouve l'utilisateur, on se connecte
                    val intent = Intent(this, AccueilActivity::class.java)
                    startActivity(intent)
                }// si l'identifiant et mdp correspondent avec la bdd
                else{
                    error.text = "votre identifiant ou mot de passe est incorrect "
                    error.visibility = View.VISIBLE
                }

            }//si tous les champs sont remplis

        }//fin connexiononlistener

        inscriptionButton.setOnClickListener {
            val intentInscription = Intent(this,SignUpActivity::class.java).also {
                startActivity(it)
            }
        }//fin quand on clique sur le bouton inscription



    }//fin override


}//fin class

