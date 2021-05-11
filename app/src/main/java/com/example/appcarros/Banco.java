package com.example.appcarros;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME = "AppCarros";

    public Banco(Context context){
        super(context, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS carro ( " +
                "       id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                "       modelo TEXT NOT NULL , " +
                "       montadora TEXT NOT NULL , " +
                "       ano INTEGER     ); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
