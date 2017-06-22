package com.hd.gallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;

public class list1 extends AppCompatActivity {
    SharedPreferences data;
    int x;
    Button b;
    ImageView i;
    int h;
    Bitmap s;
    sqlhelp base;
    Bitmap z;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list1);
        base = new sqlhelp(this,8);
        data= getSharedPreferences("ram",0);
        x=data.getInt("x",0);
        h =0;
        b = (Button) findViewById(R.id.button4);
        i= (ImageView) findViewById(R.id.imageView3);



    }
    public void click(View view)
    {

        retrive(h);
           h++;
        i.setImageBitmap(z);



    }
    public Bitmap getImageBitmap(Context context, String name, String extension){
        name=name+"."+extension;
        try{
            FileInputStream fis = context.openFileInput(name);
            Bitmap b = BitmapFactory.decodeStream(fis);
            fis.close();
            return b;
        }
        catch(Exception e){
        }
        return null;
    }
    public void retrive(int u){    String y;
        sqlhelp sql = new sqlhelp(this,9);
        SQLiteDatabase database = sql.getWritableDatabase();
        String[] column={sqlhelp.uid,sqlhelp.caption,sqlhelp.im};
        Cursor cursor=database.query(sqlhelp.tsblename,column,null,null,null,null,null);
        for(int k=0; k<u; k++) {
            cursor.moveToNext();
        }
        Toast.makeText(sql.context,"itsworking", Toast.LENGTH_LONG).show();
        y = cursor.getString(cursor.getColumnIndex(sqlhelp.caption));
        byte[] imd =  cursor.getBlob(cursor.getColumnIndex(sqlhelp.im));
        z = BitmapFactory.decodeByteArray(imd,0,imd.length);

    }
}
