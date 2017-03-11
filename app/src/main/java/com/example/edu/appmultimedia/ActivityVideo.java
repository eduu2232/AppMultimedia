package com.example.edu.appmultimedia;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import static com.example.edu.appmultimedia.R.id.activity_video;


public class ActivityVideo extends AppCompatActivity {
    Button btnGravant;
    Button btnReproduir;
    Uri enlace;
    VideoView video;

    final static int GRAVAR_VIDEO=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //andiem amb els botons

        btnGravant=(Button) findViewById(R.id.btnGvideo);
        btnReproduir=(Button) findViewById(R.id.btnRvideo);
        video=(VideoView) findViewById(R.id.videoView);

        //creem el metode onClick per enregistrar video

        btnGravant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                startActivityForResult(intent,GRAVAR_VIDEO);
            }
        });

        btnReproduir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //primer mostrem en un toast el video que reproduim;

                Toast.makeText(ActivityVideo.this,"reproduint"+enlace.getPath(),Toast.LENGTH_LONG).show();

                video.setVideoURI(enlace);
                video.start();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode==RESULT_OK){
            if (requestCode==GRAVAR_VIDEO){

                //guardem l'adreça del video
                enlace=data.getData();

                //activem el boto reproduir
                btnReproduir.setEnabled(true);
            }
        }
        else if (resultCode==RESULT_CANCELED){
            //al cancelar perdem el video i ens avisa amb un toast que hem cancelat

            enlace=null;

            Toast.makeText(this,"gravacio cancel·lada!!!",Toast.LENGTH_LONG).show();

        }
    }
}



