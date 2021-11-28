package br.com.iotruck.mobino.commons.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import br.com.iotruck.mobino.feature.login.model.Trucker

class DatabaseHandler(ctx: Context) : SQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY, $NAME TEXT, $EMAIL TEXT, $CPF TEXT, $CNH TEXT, " +
                    "$BIRTHDATE TEXT, $PHONENUMBER TEXT);"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, olVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    companion object {
        private lateinit var INSTANCE: DatabaseHandler
        private lateinit var database: SQLiteDatabase
        private var databaseOpen: Boolean = false

        fun closeDatabase(){
            if (database.isOpen && databaseOpen){
                database.close()
                databaseOpen = false

                Log.i("Database", "Database close")
            }
        }

        fun openDatabase(){
            if (!databaseOpen){
                database = INSTANCE.writableDatabase
                databaseOpen = true

                Log.i("Database" , "Database Open")
            }
        }

        fun initDatabaseInstance(ctx: Context): DatabaseHandler{
            INSTANCE = DatabaseHandler(ctx)
            return INSTANCE
        }

        fun addTrucker(trucker: Trucker): Boolean {
            val values = ContentValues()
            values.put(ID, trucker.id)
            values.put(NAME, trucker.name)
            values.put(EMAIL, trucker.email)
            values.put(CPF, trucker.cpf)
            values.put(CNH, trucker.cnh)
            values.put(BIRTHDATE, trucker.birthDate)
            values.put(PHONENUMBER, trucker.phoneNumber)
            val _success = database.insert(TABLE_NAME, null, values)
            return (("$_success").toInt() != -1)
        }


        @SuppressLint("Range")
        fun getAllTrucker(): MutableList<Trucker> {
            val data: MutableList<Trucker> = ArrayList()
            val cursor = database.rawQuery("SELECT * FROM $TABLE_NAME", null)
            cursor.use { cur ->
                if (cursor.moveToFirst()) {
                    do {
                        val trucker = Trucker.createDefaultTrucker()
                        trucker.id = cursor.getInt(cursor.getColumnIndex(ID))
                        trucker.name = cursor.getString(cursor.getColumnIndex(NAME))
                        trucker.email = cursor.getString(cursor.getColumnIndex(EMAIL))
                        trucker.cpf = cursor.getString(cursor.getColumnIndex(CPF))
                        trucker.cnh = cursor.getString(cursor.getColumnIndex(CNH))
                        trucker.birthDate = cursor.getString(cursor.getColumnIndex(BIRTHDATE))
                        trucker.phoneNumber = cursor.getString(cursor.getColumnIndex(PHONENUMBER))
                        data.add(trucker)
                    }while (cursor.moveToNext())
                }
            }
            return data
        }

        fun deleteTrucker(_id: Int): Boolean {
            val _success = database.delete(TABLE_NAME, ID + "=?", arrayOf(_id.toString())).toLong()
            return ("$_success").toInt() != -1
        }

        private val DB_VERSION = 1
        private val DB_NAME = "Mobino_SQLite"
        private val TABLE_NAME = "Trucker"
        private val ID = "id"
        private val NAME = "Name"
        private val EMAIL = "Email"
        private val CPF = "Cpf"
        private val CNH = "Cnh"
        private val BIRTHDATE = "Birthdate"
        private val PHONENUMBER = "PhoneNumber"

    }

}