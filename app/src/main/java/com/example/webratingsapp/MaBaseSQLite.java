package com.example.webratingsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MaBaseSQLite extends SQLiteOpenHelper {

    private static final String TABLE_WEBSITES = "websites";
    private static final String KEY_ID = "id";
    private static final String KEY_RATING = "rating";
    private static final String KEY_URL = "url";

    public MaBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LIVRES_TABLE = "CREATE TABLE " + TABLE_WEBSITES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_RATING + " INTEGER,"
                + KEY_URL + " TEXT" + ")";
        db.execSQL(CREATE_LIVRES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEBSITES);

        onCreate(db);
    }
}