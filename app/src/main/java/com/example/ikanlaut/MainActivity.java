package com.example.ikanlaut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button Beranda, Proses, Tentang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Tentang = findViewById(R.id.tentang);
        Tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Tentang.class);
                startActivity(i);

            }
        });

        Proses = findViewById(R.id.proses);
        Proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, proses.class);
                startActivity(i);
            }
        });

        Beranda = findViewById(R.id.beranda);
        Beranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Beranda.class);
                startActivity(i);
            }
        });
    }
}