package com.example.edu.appmultimedia;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class ActivityAnimacions extends AppCompatActivity {

    // creem els botons i l'mage view
    Button btnInterpolacio;
    Button btnTornar;
    Button btnFotogrames;
    ImageView IVeyes;
    ImageView IVninot;
    Animation animacioEyes;
    AnimationDrawable animacioNinot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animacions);

        //els anidem als de la pantalla

        btnInterpolacio=(Button) findViewById(R.id.btnInterpolacio);
        btnFotogrames=(Button) findViewById(R.id.btnFotogrames);
        btnTornar=(Button) findViewById(R.id.btnTornar);
        IVeyes= (ImageView) findViewById(R.id.IVeyes);
        IVninot= (ImageView) findViewById(R.id.IVninot);




        //Creem el onclick Animation animacio Eyes del botó interpolació per a fer l'animació al clicar
        btnInterpolacio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animacioEyes = AnimationUtils.loadAnimation(ActivityAnimacions.this, R.anim.venir);
                IVeyes.startAnimation(animacioEyes);
            }
        });

        //Creem el onclick Animation animacio Eyes del botó interpolació per a fer l'animació al clicar

        btnFotogrames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animacioNinot=(AnimationDrawable)IVninot.getDrawable();
                animacioNinot.start();

            }
        });





        // mitjançant onClick, lliguem tornar al MainActivity

        btnTornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityAnimacions.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
