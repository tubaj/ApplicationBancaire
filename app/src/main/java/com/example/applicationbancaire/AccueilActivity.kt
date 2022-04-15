package com.example.applicationbancaire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.applicationbancaire.Database.BankDataBase
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_accueil.*
import kotlinx.android.synthetic.main.fragment_accueil.*

class AccueilActivity : AppCompatActivity() {

    private lateinit var currentFragment: Fragment
    lateinit var  db : BankDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accueil)

        supportFragmentManager.beginTransaction().replace(R.id.nav_container,AccueilFragment()).commit()
        val bottomNav : BottomNavigationView? = bottom_navigation
        //mise en place d'un nav listener vers le bottom nav

        if (bottomNav != null) {
            bottomNav.setOnItemSelectedListener(navListener)
        }


    }//fin override
     //creation d'un navigation listener
     val navListener = BottomNavigationView.OnNavigationItemSelectedListener{ item ->
        //creation d'un fragment
         when(item.itemId){
            R.id.accueil ->{
                currentFragment =  AccueilFragment()
            }
            R.id.virement -> {
                currentFragment = VirementFragment()
            }
            R.id.depense ->  {
               currentFragment = DepenseFragment()
           }
            R.id.contact -> {
               currentFragment = ContactFragment()
           }
         }
         supportFragmentManager.beginTransaction().replace(R.id.nav_container,currentFragment).commit()
          true


     }
    fun faireVirement(iban_exp: String, iban_desti: String, montant: Int) {
        val solde_iban_exp =  db.getSoldeIban(iban_exp) //obtenir le solde du client
        val soustraction = solde_iban_exp - montant
        db.setSoldeIban(soustraction, iban_exp) //maj du solde du client

        //envoie du virement
        val solde_iban_dest = db.getSoldeIban(iban_desti) //obtenir le solde du desti
        val addition = solde_iban_dest + montant
        db.setSoldeIban(addition, iban_desti)

    }

}//fin class