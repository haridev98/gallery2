package com.hd.gallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileInputStream;

public class list1 extends AppCompatActivity {
    SharedPreferences data;
    int x;
    Button b;
    ImageView i;
    int h;
    Bitmap s;
    sqlbaseadapter base;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list1);
        base = new sqlbaseadapter(this,8);
        data= getSharedPreferences("ram",0);
        x=data.getInt("x",0);
        h =0;
        b = (Button) findViewById(R.id.button4);
        i= (ImageView) findViewById(R.id.imageView3);



    }
    public void click(View view)
    {

        base.retrive(h);
           h++;
        i.setImageBitmap(base.b);



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
}
