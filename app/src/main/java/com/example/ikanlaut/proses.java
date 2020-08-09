package com.example.ikanlaut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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

    Button btnAmbilFotoMata, btnProses;
    ImageView imvGambarMata;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    int flag = 0, cekMata = 0;
    Bitmap fotoMata;
    final int PIC_CROP = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proses);

        btnAmbilFotoMata = findViewById(R.id.btnAmbilFotoMata);
        btnProses = findViewById(R.id.btnProsesFoto);
        imvGambarMata = findViewById(R.id.imvFotoMata);

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

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cekMata == 1){
                    Intent intent = new Intent(getApplicationContext(), CloudActivity.class);
                    ByteArrayOutputStream bs1 = new ByteArrayOutputStream();
                    fotoMata.compress(Bitmap.CompressFormat.PNG, 100, bs1);
                    intent.putExtra("Foto Mata", bs1.toByteArray());

                    startActivity(intent);
                }else if(cekMata == 0){
                    Toast.makeText(getApplicationContext(), "Foto Mata Belum Ada.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            final Bitmap imageBitmap = (Bitmap) extras.get("data");
            Uri imageuri = getImageUri(getApplicationContext(),imageBitmap);
            performCrop(imageuri);
        }else if(requestCode == PIC_CROP){
            Bundle extras = data.getExtras();
            Bitmap thePic = extras.getParcelable("data");
            if(flag == 1){
                imvGambarMata.setImageBitmap(thePic);
                fotoMata = thePic;
                cekMata = 1;
                flag = 0;
            }
        };
    }

    private void performCrop(Uri uri) {
        try {
            //call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //indicate image type and Uri
            cropIntent.setDataAndType(uri, "image/*");
            //set crop properties
            cropIntent.putExtra("crop", "true");
            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            //indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            //retrieve data on return
            cropIntent.putExtra("return-data", true);
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);
        }
        catch(ActivityNotFoundException anfe){
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}