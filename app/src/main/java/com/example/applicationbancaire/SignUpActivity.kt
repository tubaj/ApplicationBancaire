package com.example.applicationbancaire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import com.example.applicationbancaire.Database.BankDataBase
import com.example.applicationbancaire.data.User
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    lateinit var db: BankDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        BtnInscription.setOnClickListener {
            db = BankDataBase(this)
            val nom_input = edit_nom.text.toString()
            val prenom_input = edit_prenom.text.toString()
            val id_connect_input = id_connect.text.toString()
            val iban_input = edit_iban.text.toString()
            val password_input = edit_password.text.toString()
            val conf_password_input = confirmer_password.text.toString()
            val num_compte = edit_num_compte.text.toString()

            if (nom_input.trim().isEmpty() || prenom_input.trim().isEmpty() || iban_input.trim().isEmpty() ||
                id_connect_input.trim().isEmpty() || conf_password_input.trim().isEmpty() || password_input.trim().isEmpty()
                || num_compte.trim().isEmpty()
            ) {
                Toast.makeText(this, "Vous devez remplir tous les champs", Toast.LENGTH_LONG)
                    .show()
            }//if si tous les champs ne sont pas remplis
            else {
                if (id_connect_input.length != 8) {
                    Toast.makeText(this, "Votre identifiant doit avoir 8 caractères", Toast.LENGTH_LONG)
                        .show()
                }//si identificant pas 8 caract
                else {
                        if(iban_input.length != 27) {
                        Toast.makeText(this, "Votre IBAN est incorrect, doit contenir les 27 caractères", Toast.LENGTH_LONG)
                            .show()
                        }//verifier si iban fait 27 caractères

                        else {
                                if (password_input.length != 8) {
                                    Toast.makeText(
                                        this, "Votre mot de passe doit avoir 8 caractères", Toast.LENGTH_LONG).show()
                                }//verifier si le mdp est de 8 caracteres
                                else {
                                    if (password_input != conf_password_input) {
                                        error2.text = "Vos mots de passe ne se correspondent pas"
                                        error2.visibility = View.VISIBLE

                                    }
                                    else {
                                            if (num_compte.length != 11){
                                                Toast.makeText(
                                                    this, "Votre  numero de compte doit avoir 11 caractères", Toast.LENGTH_LONG).show()
                                            } //fin verif numcompte
                                            else {
                                                val user = User(0, nom_input, prenom_input, id_connect_input, password_input, iban_input, num_compte)
                                                val isInserted = db.addUser(user)

                                                if (isInserted) {
                                                    Toast.makeText(this, "Votre compte a été créer avec succès", Toast.LENGTH_SHORT).show()
                                                    val intent =
                                                        Intent(this, LoginActivity::class.java)
                                                    startActivity(intent)
                                                }
                                            }//else création du user
                                    }//fin else avec verif compte et création user
                                }//password verifié
                         }//verifié password = 8 pour la longueur
                }//iban verifié 27 caract
            }//si tous les champs sont remplis //FIN grand else
        }//fin incriptionbutton
    }//fin override
}//fin class
