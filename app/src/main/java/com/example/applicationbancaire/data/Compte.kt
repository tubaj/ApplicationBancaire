package com.example.applicationbancaire.data

import androidx.annotation.IntegerRes

data class Compte(
    var id: Int,
    var solde: Int,
    var banque: String,
    var num_compte: String,
    var iban: String
)

val compte1 = Compte ( 0, 150, "LCL", "12345678912", "123456789123456789123456789")
val compte2=  Compte ( 1, 2000, "BNP", "12345678912", "923456789123456789123456789")