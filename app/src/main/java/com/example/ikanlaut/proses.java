package com.example.ikanlaut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class proses extends AppCompatActivity {

    Button btnAmbilFotoInsang, btnAmbilFotoMata, btnProses;
    ImageView imvGambarInsang, imvGambarMata;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    int flag = 0, cekMata = 0, cekInsang = 0;
    Bitmap fotoInsang, fotoMata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proses);

        btnAmbilFotoMata = findViewById(R.id.btnAmbilFotoMata);
        btnAmbilFotoInsang = findViewById(R.id.btnAmbilFotoInsang);
        btnProses = findViewById(R.id.btnProsesFoto);
        imvGambarMata = findViewById(R.id.imvFotoMata);
        imvGambarInsang =findViewById(R.id.imvFotoInsang);

        btnAmbilFotoMata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        btnAmbilFotoInsang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 2;
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cekInsang == 1 && cekMata == 1){
//                 Toast.makeText(getApplicationContext(), "Sudah Semua.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), CloudActivity.class);
                    ByteArrayOutputStream bs1 = new ByteArrayOutputStream();
                    fotoMata.compress(Bitmap.CompressFormat.PNG, 100, bs1);
                    intent.putExtra("Foto Mata", bs1.toByteArray());

                    ByteArrayOutputStream bs2 = new ByteArrayOutputStream();
                    fotoInsang.compress(Bitmap.CompressFormat.PNG, 100, bs2);
                    intent.putExtra("Foto Insang", bs2.toByteArray());

                    startActivity(intent);
                }else if(cekInsang == 0 && cekMata == 0){
                    Toast.makeText(getApplicationContext(), "Foto Insang dan Mata Belum Ada.", Toast.LENGTH_SHORT).show();
                }else if(cekInsang == 0){
                    Toast.makeText(getApplicationContext(), "Foto Insang Belum Ada.", Toast.LENGTH_SHORT).show();
                }else if(cekMata == 0){
                    Toast.makeText(getApplicationContext(), "Foto Mata Belum Ada.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (flag == 1) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                final Bitmap imageBitmap = (Bitmap) extras.get("data");
                imvGambarMata.setImageBitmap(imageBitmap);
                fotoMata = imageBitmap;
                cekMata = 1;
            }
        }else if(flag == 2){
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                final Bitmap imageBitmap = (Bitmap) extras.get("data");
                imvGambarInsang.setImageBitmap(imageBitmap);
                fotoInsang = imageBitmap;
                cekInsang = 1;
            }
        }
        flag = 0;
    }
}