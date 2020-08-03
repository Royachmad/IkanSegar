package com.example.ikanlaut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class CloudActivity extends AppCompatActivity {

    ImageView imvhasilCloud;
    Button btnHasil;
    Bitmap fotoMata, fotoInsang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud);

        imvhasilCloud = findViewById(R.id.HasilCloud);
        btnHasil = findViewById(R.id.btnIntentHasil);

        fotoMata = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("Foto Mata"),0,getIntent().getByteArrayExtra("Foto Mata").length);

        fotoInsang = BitmapFactory.decodeByteArray(
                getIntent().getByteArrayExtra("Foto Insang"),0,getIntent().getByteArrayExtra("Foto Insang").length);


        btnHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HasilActivity.class);
                ByteArrayOutputStream bs1 = new ByteArrayOutputStream();
                fotoMata.compress(Bitmap.CompressFormat.PNG, 100, bs1);
                intent.putExtra("Foto Mata", bs1.toByteArray());

                ByteArrayOutputStream bs2 = new ByteArrayOutputStream();
                fotoInsang.compress(Bitmap.CompressFormat.PNG, 100, bs2);
                intent.putExtra("Foto Insang", bs2.toByteArray());

                startActivity(intent);
            }
        });
    }
}