package com.example.edu.appmultimedia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import java.io.IOException;

public class ActivityAudio extends AppCompatActivity {

    ToggleButton btnGravar;
    Button btnReproduir;
    Button btnTornar;
    boolean gravant=false;
    boolean reproduint=false;
    private static String fitxer1=null;
    private static String fitxer2;
    private MediaRecorder recorder;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        //demanem permisos
        int retorrno=0;
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.RECORD_AUDIO,},retorrno);
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE,},retorrno);

        //anidem la variable
        btnGravar = (ToggleButton)findViewById(R.id.btnGravar);
        btnReproduir= (Button)findViewById(R.id.btnReproduir);
        btnTornar = (Button) findViewById(R.id.btnTornar);


        //generem el fitxer on enregistrem
        fitxer1= Environment.getExternalStorageDirectory()+"/gravacio1.3gp";
        fitxer2=getExternalFilesDir(null)+"gravacio2.3gp";

        //fem les funcionalitats del botó gravar

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gravant){

                    aturarGravacio();
                }
                else{
                    comencaGravacio();
                }
                gravant = !gravant;
            }
        });
        
        btnReproduir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reproduint){

                    aturarReproduccio();
                }
                else{
                    comencaReproduccio();
                }
                reproduint = !reproduint;
            }
            
        });

        btnTornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityAudio.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void comencaReproduccio() {
        player=new MediaPlayer();

        try {
            player.setDataSource(fitxer2);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void aturarReproduccio() {
        player.release();
        player=null;

    }

    private void aturarGravacio() {
        recorder.stop();
        recorder.release();
        recorder=null;
    }

    private void comencaGravacio() {
        recorder=new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fitxer2);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recorder.start();
    }
}
