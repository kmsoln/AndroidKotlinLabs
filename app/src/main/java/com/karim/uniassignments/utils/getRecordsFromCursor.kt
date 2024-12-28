package com.karim.uniassignments.utils

import android.database.Cursor
import com.karim.uniassignments.model.fifth.Record

fun getRecordsFromCursor(cursor: Cursor): List<Record> {
    val records = mutableListOf<Record>()
    if (cursor.moveToFirst()) {
        do {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
            records.add(Record(id, name, email))
        } while (cursor.moveToNext())
    }
    cursor.close()
    return records
}
