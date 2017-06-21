package com.hd.gallery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
           Button a,c,b,m;
    Toast toast;
    Bitmap cam = null;
    sqlbaseadapter base;
    SharedPreferences data;
    Intent o;
    int x;
    ImageView i;
    EditText t;
    String d;
    private static final int r =100;
    private static final  int CAMERA_REQUEST = 10;
    Uri imageuri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = getSharedPreferences("ram",1);
        x=1;
        x= data.getInt("x",1);
          base = new sqlbaseadapter(this,8);

         t= (EditText) findViewById(R.id.editText);
        b= (Button) findViewById(R.id.button);
        c=  (Button) findViewById(R.id.button2);
        b.setOnClickListener(this);
        m=  (Button) findViewById(R.id.button5);
        m.setOnClickListener(this);
        a=  (Button) findViewById(R.id.button3);
        a.setOnClickListener(this);
        c.setOnClickListener(this);
        i = (ImageView) findViewById(R.id.imageView2);
    }

    @Override
    public void onClick(View v) {

       if(v.getId()==R.id.button)
           opengallery();
        else if (v.getId()==R.id.button2)
           opencamera();
        else if(v.getId()==R.id.button5)
       {
            o = new Intent(this,list1.class);
           startActivity(o);

       }

       else if(v.getId()==R.id.button3)
       {
          d = t.getText().toString();
            Toast.makeText(this,d, Toast.LENGTH_SHORT).show();


           base.insert(d,getBytes(cam));



           }

           SharedPreferences.Editor editor = data.edit();
           editor.putInt("x", x).apply();



       }


    public void opengallery()
    {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,r);
    }
    public void opencamera()
    {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera,CAMERA_REQUEST);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode==r)&& resultCode==RESULT_OK)
        {  imageuri = data.getData();
            try {
                cam = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageuri);
            } catch (IOException e) {
                e.printStackTrace();
            }


            i.setImageBitmap(cam);
            Toast.makeText(this,"Gallerycalled",Toast.LENGTH_LONG).show();

        }


    if ((requestCode==CAMERA_REQUEST)&& resultCode==RESULT_OK)
    {
         cam= (Bitmap) data.getExtras().get("data");
        i.setImageBitmap(cam);
        Toast.makeText(this,"cameracalled",Toast.LENGTH_LONG).show();
    }
    }
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        return stream.toByteArray();
    }


}
