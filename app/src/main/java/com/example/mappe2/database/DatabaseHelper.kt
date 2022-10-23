package com.example.mappe2.database
/*
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mappe2.model.KontaktModel


//Samme som dbhandler i undervisning
class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

    //companinion object is a way to initilaize while creating the object of this class
    companion object{
        private val DB_NAME = "Telefonkontakt"
        private val DB_VERSION = 3
        private val TABLE_NAME = "kontakter"
        //specifies the columns
        private val ID = "id"
        private val NAVN = "navn"
        private val TELEFON = "telefon"

    }

    //When we call the DatabaseHelper class it will call this onCreate function
    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY, $NAVN TEXT, $TELEFON TEXT)"
        /*
        Kotlin null safety is a procedure to eliminate the risk of null reference from the code.
        Kotlin compiler throws NullPointerException immediately if it found any null argument is passed without executing
        any other statements. Kotlin's type system is aimed to eliminate NullPointerException form the code.
         p0? er en nullable safe
         */
        p0?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        p0?.execSQL(DROP_TABLE)
        onCreate(p0)
    }

    @SuppressLint("Range")
    fun visAlle(): List<KontaktModel>{
        val kontaktList = ArrayList<KontaktModel>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        //cursor will hold the data from our database
        val cursor = db.rawQuery(selectQuery, null)
        if(cursor != null){
            //fetching data
            //when we call moteToFirst object, it will perform to tasks
            //1. Query Returns the set or the actual dataset
            //2. It will move the cursor to the first result

              if (cursor.moveToFirst()){
                  //Do while loop to first get the data and then check that the
                  // data is available, and then store the data
                  do {
                      val kontakter = KontaktModel()
                      kontakter.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                  }while (cursor.moveToNext())

              }
        }
    }

    /*
    fun finnallekontakter: list<kontakt>{
        val kontaktliste = arraylist<kontakt>()
        val db:sqlitedatabase = writabledatabase
        val selectquery = "select * from $table_name"
        //cursor will hold the data from our database
        val cursor:cursor = db.rawquery(selectquery, null)
        if(cursor.movetofirst()){
            do{
                val kontakt = kontakt()
                kontakt.id = cursor.getint(0)
                kontakt.navn = cursor.getstring(1)
                kontakt.telefon = cursor.getstring(2)
                kontaktliste.add(kontakt)
            } while (cursor.movetonext())
            cursor.close()
        }
        return kontaktliste
     }
     */

    //insert
    fun addKontakt(kontakter : KontaktModel): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAVN, kontakter.navn)
        values.put(TELEFON, kontakter.telefon)
        val _success = db.insert(TABLE_NAME, null, values)
    }
}

*/