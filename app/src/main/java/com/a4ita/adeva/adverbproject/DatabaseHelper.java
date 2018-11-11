package com.a4ita.adeva.adverbproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "person_table";
    private static final String col1 = "ID";
    private static final String col2 = "name";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                col2 +" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, item);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //returns all data from the database
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //returns only the ID that matches the name passed in 'name'
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + col1 + " FROM " + TABLE_NAME +
                " WHERE " + col2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /** Updates the name field
     * newName
     * id
     * oldName
     * */
    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + col2 +
                " = '" + newName + "' WHERE " + col1 + " = '" + id + "'" +
                " AND " + col2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    //delete from db 'id' and 'name'
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + col1 + " = '" + id + "'" +
                " AND " + col2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }
}
