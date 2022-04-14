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
