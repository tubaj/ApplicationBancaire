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

val user1 = User(0, "boubou", "marie", "12345678", "123456", "123456789123456789123456789", "12345678912")
val user2 = User(1, "baba", "mami", "123789456", "123456", "923456789123456789123456789", "12345678912")