package com.br.database.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by silio on 20/08/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE = "BancoDados";
    private static int VERSION = 2;

    public DataBaseHelper(Context c){
        super(c, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Linguagem ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome TEXT NOT NULL,"
                + "codigo TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("CREATE TABLE IF NOT EXISTS Linguagem ("
//                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + "nome TEXT NOT NULL,"
//                + "codigo TEXT NOT NULL);");
    }
}
