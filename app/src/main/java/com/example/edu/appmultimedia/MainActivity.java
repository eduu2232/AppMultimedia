package com.example.edu.appmultimedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //creem els botons

    Button btnAnimacions;
    Button btnCamara;
    Button btnAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //anidem la variable al botó

        btnAnimacions = (Button) findViewById(R.id.btnAnimacions);
        btnCamara = (Button) findViewById(R.id.btnCamara);
        btnAudio = (Button) findViewById(R.id.btnAudio);

        //fem la funcio de click que ens porta a la activity corresponent

        btnAnimacions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,ActivityAnimacions.class);
                startActivity(intent);
            }
        });

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ActivityCamara.class);
                startActivity(intent);
            }
        });

        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ActivityAudio.class);
                startActivity(intent);
            }
        });
    }
}
