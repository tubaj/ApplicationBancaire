package com.example.applicationbancaire.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.applicationbancaire.data.Compte
import com.example.applicationbancaire.data.User

//creation de la bdd à l'appel du constructeur
class BankDataBase(var mContext: Context) : SQLiteOpenHelper( //const de la class SQLliteOpenhelper
    mContext,
    DB_NAME,
    null,
    DB_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        //creation des tables de la bdd
        val createTableUser = """
            create table $USERS_TABLE_NAME ( 
                                  $USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                                  $NOM VARCHAR(20),
                                  $PRENOM VARCHAR(20),
                                  $IDENTIFIANT INTEGER(8) UNIQUE,
                                  $MDP VARCHAR(8),
                                  $IBAN VARCHAR(27)UNIQUE,
                                  $NUM_COMPTE INTEGER(11)UNIQUE )
        """.trimIndent()
        db?.execSQL(createTableUser)

        val createTableHistorique = """
            CREATE TABLE historique ( id INTEGER PRIMARY KEY AUTOINCREMENT,
  									   intitule VARCHAR(20),
                                       euros INTEGER,
                                       categorie VARCHAR(20),
                                       ladate DATE)
        """.trimIndent()
        db?.execSQL(createTableHistorique)

        val createTableCompte = """
            CREATE TABLE  $COMPTE_TABLE_NAME ( 
                                    $COMPTE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
  									$SOLDE INTEGER,
                                    $BANQUE VARCHAR(20),
                                    $NUM_COMPTE VARCHAR(11)UNIQUE,
                                    $IBAN VARCHAR(27) UNIQUE 
            )
        """.trimIndent()
        db?.execSQL(createTableCompte)

    }

    override fun onUpgrade(db: SQLiteDatabase?,oldVersion: Int, newVersion: Int) {
       //upgrader les tables //suprimer les anciennes et recrer les nouvelles

        db?.execSQL("DROP TABLE IF EXISTS $USERS_TABLE_NAME")
        db?.execSQL("DROP TABLE IF EXISTS historique")
        db?.execSQL("DROP TABLE IF EXISTS $COMPTE_TABLE_NAME")
        onCreate(db)
    }
    fun addCompte(compte: Compte): Boolean{
        //insérer un nvx compte dans le bdd
        val db = this.writableDatabase //ouvrir la bdd
        val values = ContentValues()
        values.put(SOLDE, compte.solde)
        values.put(BANQUE, compte.banque)
        values.put(NUM_COMPTE, compte.num_compte )
        values.put(IBAN, compte.iban )

        //faire l'insert
        val result = db.insert(COMPTE_TABLE_NAME,null, values).toInt()
        db.close()//fermer la bdd
        return result != -1 //donc true
    }
    fun addUser(user: User): Boolean {
        //insérer un nvx utilisateur dans le bdd
        val db = this.writableDatabase //ouvrir la bdd
        val values = ContentValues()
        values.put(NOM, user.nom)
        values.put(PRENOM, user.prenom )
        values.put(IDENTIFIANT, user.identifiant )
        values.put(MDP, user.mdp )
        values.put(IBAN, user.iban )
        values.put(NUM_COMPTE, user.num_compte)


        //faire l'insert
        val result = db.insert(USERS_TABLE_NAME,null, values).toInt()
        db.close()//fermer la bdd
        return result != -1 //donc true
    }
    fun findUser(identifiant:String,password:String): User?{//peut être null
        var user: User? = null //peut être null
        val db = this.readableDatabase
        val selectionArgs = arrayOf(identifiant, password)
        val cursor = db.query(
            USERS_TABLE_NAME,
            null,
            "$IDENTIFIANT=? AND $MDP=?",
            selectionArgs,
            null,
            null,
            null,
            null
        )
        //si on trouve un user on le retourne, sinon c'est null qui sera retourné
        if (cursor != null) { //si le cursor n'est pas null, s'il y a un resultat déjà
            if (cursor.moveToFirst()) { //on le deplace sur les differents champs
                val id = cursor.getInt(0)
                val nom = cursor.getString(1)
                val prenom = cursor.getString(2)
                val identifiant = cursor.getString(3)
                val mdp = cursor.getString(4)
                val iban = cursor.getString(5)
                val num_compte = cursor.getString(6)
                val user = User(id, nom, prenom, identifiant, mdp, iban, num_compte)
                return user
            }
        }
        db.close()
        return user
    }//fin findUser

    //contient variable qui sont  des attributs de classe
    companion object {
        private val DB_NAME = "bank_db" // val par defaut
        private val DB_VERSION = 1 //val par defaut // version 1 de la bd
        private val USERS_TABLE_NAME ="users"
        private val USER_ID ="id"
        private val NOM ="nom"
        private val PRENOM ="prenom"
        private val IDENTIFIANT ="identifiant"
        private val MDP ="mdp"
        private val IBAN ="iban"
        private val NUM_COMPTE ="num_compte"

        private val COMPTE_TABLE_NAME = "compte"
        private val COMPTE_ID = "id"
        private val SOLDE = "solde"
        private val BANQUE = "banque"

    }

}