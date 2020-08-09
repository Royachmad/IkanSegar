package com.example.ikanlaut;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class HasilActivity extends AppCompatActivity {

    ImageView imvHasilAkhirMata;
    Bitmap fotoMata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        imvHasilAkhirMata = findViewById(R.id.imvHasilFotoMata);

        fotoMata = BitmapFactory.decodeByteArray(
                getIntent().getByteArrayExtra("Foto Mata"),0,getIntent().getByteArrayExtra("Foto Mata").length);

        imvHasilAkhirMata.setImageBitmap(fotoMata);
    }
}