package com.example.webratingsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class BDSites {

    private final static int VERSION_BDD = 1;
    private SQLiteDatabase bdd;
    private MaBaseSQLite maBaseSQLite;

    public BDSites(Context context){
        //On crée la BDD et sa table :
        maBaseSQLite = new MaBaseSQLite(context,"websites",null,VERSION_BDD);
    }

    public void open(){
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        bdd.close();
    }

    public SQLiteDatabase getBdd(){
        return bdd;
    }

    public long insertSite(Site site){

        ContentValues values = new ContentValues();
        values.put("rating", site.getRating());
        values.put("url", site.getUrl());

        return getBdd().insert("websites",null,values);
    }

    public ArrayList<Site> getAllElements() {

        ArrayList<Site> list = new ArrayList<Site>();

        // Select All Query
        String selectQuery = "SELECT  * FROM websites";

        bdd = maBaseSQLite.getReadableDatabase();

        try {

            Cursor cursor = bdd.rawQuery(selectQuery, null);
            try {

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        String rating = cursor.getString(1);
                        String url = cursor.getString(2);
                        Site site = new Site(rating, url);
                        list.add(site);
                    } while (cursor.moveToNext());
                }

            } finally {
                try { cursor.close(); } catch (Exception ignore) {}
            }

        } finally {
            try { bdd.close(); } catch (Exception ignore) {}
        }

        return list;
    }

    public Site getSiteByTitre(String titre){

        bdd = maBaseSQLite.getReadableDatabase();
        Cursor cursor = bdd.query("websites", new String[] {"rating", "url"},
                "url" + "=?", new String[] { titre },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return cursorToSite(cursor);
    }

    public Site cursorToSite(Cursor cursor){

        if (cursor.getCount() == 0) {
            return null;
        }
        else {
            Site site = new Site(
                    cursor.getString(0),
                    cursor.getString(1));
            return site;
        }
    }
}