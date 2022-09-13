package com.example.eventhaddlerwithpaymentapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
public class ActivityCamara extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        ImageView displayImageView =  this.findViewById(R.id.displayImageView);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){

            Bitmap res_image =(Bitmap) bundle.get("my_Image");
            displayImageView.setImageBitmap(res_image);

        }

    }

}
