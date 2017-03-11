package com.example.edu.appmultimedia;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ActivityCamara extends AppCompatActivity {

    Button btnTornar;
    Button btnNormal;
    ImageView imatgeCamera;
    Uri IDimatge;
    final int CAMARA=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        //fem les asignacions de variables al boto

        btnTornar = (Button)findViewById(R.id.btnTornar);
        btnNormal = (Button)findViewById(R.id.btnNormal);




        //fem els metodes onClock que gestionen la nostra app

        btnTornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityCamara.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

                File foto= new File(getExternalFilesDir(null)+"fotos.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));

                IDimatge = Uri.fromFile(foto);
                startActivityForResult(intent,CAMARA);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("requestCode","requestCode: "+requestCode);
        Log.e("RESULT_OK","RESULT_OK: "+RESULT_OK);

        switch (requestCode){
            case CAMARA:
                if (resultCode==RESULT_OK){

                    //creem un gestor de contingut
                    ContentResolver contentResolver=getContentResolver();

                    //indiquem el canvi de fitxer
                    contentResolver.notifyChange(IDimatge,null);

                    imatgeCamera= (ImageView) findViewById(R.id.imatgeCamera);
                    Bitmap bitmap;

                    try {
                        //obtenim la imatge en mapa de bits
                        bitmap=android.provider.MediaStore.Images.Media.getBitmap(contentResolver,IDimatge);

                        //modifiquem la altura
                        int alt= (bitmap.getHeight()*1080/bitmap.getWidth());

                        //guardem en un bitmap la foto reduida
                        Bitmap reduida = Bitmap.createScaledBitmap(bitmap,1080,alt,true);

                        imatgeCamera.setImageBitmap(reduida);
                    } catch (Exception e) {

                        Toast toast=Toast.makeText(this,"erro", Toast.LENGTH_LONG);
                        toast.show();


                    }
                }
        }

    }
}
