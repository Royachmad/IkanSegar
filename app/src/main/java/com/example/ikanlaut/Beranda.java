package com.example.ikanlaut;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class Beranda extends AppCompatActivity {
ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        int images[] = {R.drawable.g1, R.drawable.g2, R.drawable.g3, R.drawable.g4};
        viewFlipper = findViewById(R.id.v_flipper);

//        for (int i = 0; i <images.length; i++){
//            flipperImages(images[i]);
//        }

        for (int image: images){
            flipperImages(image);
        }
    }


    public void flipperImages(int images){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(images);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
}