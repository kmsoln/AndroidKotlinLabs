package com.karim.uniassignments.presentation.viewmodel

import android.content.Context
import android.database.Cursor
import androidx.lifecycle.ViewModel
import com.karim.uniassignments.data.AppDatabase

class MainViewModel(context: Context) : ViewModel() {
    private val database = AppDatabase(context)

    fun addRecord(name: String, email: String): Long {
        return database.insertRecord(name, email)
    }

    fun getRecords(): Cursor {
        return database.getAllRecords()
    }

    fun updateRecord(id: Int, name: String, email: String): Int {
        return database.updateRecord(id, name, email)
    }

    fun clearRecords(): Int {
        return database.clearTable()
    }
}
