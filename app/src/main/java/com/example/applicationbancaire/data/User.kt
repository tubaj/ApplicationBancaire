package com.example.applicationbancaire.data

//CONTIENT DES FONCTIONS DE PLUS COMME EQUAL / COPY
data class User(
    var id: Int,
    var nom: String,
    var prenom: String,
    var identifiant: String,
    var mdp: String,
    var iban: String,
    var num_compte: String
)


//val user11 = User(0, "boubou", "marie", "12345677", "12345677", "123456789123456789123456789", "12345678912")
//val user22 = User(1, "baba", "mami", "12345679", "12345678", "923456789123456789123456789", "12345678912")