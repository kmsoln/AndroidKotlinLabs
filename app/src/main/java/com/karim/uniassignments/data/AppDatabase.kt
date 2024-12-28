package com.karim.uniassignments.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertRecord(name: String, email: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_EMAIL, email)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun updateRecord(id: Int, name: String, email: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_EMAIL, email)
        }
        return db.update(TABLE_NAME, values, "id = ?", arrayOf(id.toString()))
    }

    fun getAllRecords(): Cursor {
        return readableDatabase.query(TABLE_NAME, null, null, null, null, null, null)
    }

    fun clearTable(): Int {
        val db = writableDatabase
        return db.delete(TABLE_NAME, null, null)
    }

    companion object {
        const val DATABASE_NAME = "AppDatabase.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "mytable"
        const val COLUMN_NAME = "name"
        const val COLUMN_EMAIL = "email"
    }
}
