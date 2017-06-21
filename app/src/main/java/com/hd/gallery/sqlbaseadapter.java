package com.hd.gallery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by Haridev on 21-06-2017.
 */

public class sqlbaseadapter  {
    sqlitehelper sql;
    String s;
    Bitmap b;
    Toast to;
          public sqlbaseadapter(Context context, int version){
               sql= new sqlitehelper(context, version);
          }

    public  class sqlitehelper extends SQLiteOpenHelper {
        private static final String databasename="galldatabase2";
        private static final String tsblename="gallery1";
        private static final  String uid="_id";
        private static final  String caption="caption";
        private static final  String im="image";
        Context context;
        public sqlitehelper(Context context,  int version) {
            super(context, databasename, null, version);

            this.context=context;



        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {    String ct ="CREATE TABLE IF EXITS"+tsblename+"("+uid+"INTEGER PRIMARY KEY AUTOINCREMENT,"+caption+"VARCHAR(255)"+im+"BLOB);";

                db.execSQL(ct);
                Toast.makeText(sql.context,"oncreate database", Toast.LENGTH_LONG).show();
            } catch (SQLException e){
                e.printStackTrace();}



        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + tsblename);
            onCreate(db);

        }
    }


    public long insert(String name, byte[] image){
        SQLiteDatabase database;
              database= sql.getWritableDatabase();
        sql.onCreate(database);


        ContentValues cv = new  ContentValues();
        cv.put(sqlitehelper.caption,    name);
        cv.put(sqlitehelper.im,   image);
       long id= database.insert( sqlitehelper.tsblename, null, cv );
        database.close();
        Log.d(TAG, "insert: ");
        Toast.makeText(sql.context,"gotit", Toast.LENGTH_LONG).show();
        return id;
    }
    public void retrive(int u){
        SQLiteDatabase database = sql.getWritableDatabase();
        String[] column={sqlitehelper.uid,sqlitehelper.caption,sqlitehelper.im};
        Cursor cursor=database.query(sqlitehelper.tsblename,column,null,null,null,null,null);
        for(int k=0; k<u; k++) {
            cursor.moveToNext();
        }
        Toast.makeText(sql.context,"itsworking", Toast.LENGTH_LONG).show();
        s = cursor.getString(cursor.getColumnIndex(sqlitehelper.caption));
        byte[] imd =  cursor.getBlob(cursor.getColumnIndex(sqlitehelper.im));
        b = BitmapFactory.decodeByteArray(imd,0,imd.length);

    }

}
