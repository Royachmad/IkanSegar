package com.example.ikanlaut;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class HasilActivity extends AppCompatActivity {

    ImageView imvHasilAkhirMata, imvHasilAkhirInsang;
    Bitmap fotoMata, fotoInsang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        imvHasilAkhirInsang = findViewById(R.id.imvHasilFotoInsang);
        imvHasilAkhirMata = findViewById(R.id.imvHasilFotoMata);

        fotoMata = BitmapFactory.decodeByteArray(
                getIntent().getByteArrayExtra("Foto Mata"),0,getIntent().getByteArrayExtra("Foto Mata").length);

        fotoInsang = BitmapFactory.decodeByteArray(
                getIntent().getByteArrayExtra("Foto Insang"),0,getIntent().getByteArrayExtra("Foto Insang").length);

        imvHasilAkhirMata.setImageBitmap(fotoMata);
        imvHasilAkhirInsang.setImageBitmap(fotoInsang);
    }
}