package com.example.eventhaddlerwithpaymentapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.service.media.MediaBrowserService;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent pay = new Intent(this, PaymentActivity.class);

//
        EditText TxtName = this.findViewById(R.id.TxtName);
        EditText TxtPayment = this.findViewById(R.id.TxtPayment);
        EditText lblDetailView = this.findViewById(R.id.lblDetailView);
        EditText txtMobileNumber = this.findViewById(R.id.txtMobileNumber);

        Button PayBtn = findViewById(R.id.PayBtn);
        Button btnClear = this.findViewById(R.id.btnClear);
        Button BtnCall = this.findViewById(R.id.BtnCall);
        Button btnCamara = this.findViewById(R.id.btnCamara);
//        Button btnGallary = this.findViewById(R.id.btnGallary);


        PayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay.setAction(Intent.ACTION_SEND);
                String UserName = TxtName.getText().toString();
                String PAYMENT = TxtPayment.getText().toString();
                String deatils = UserName + "-" + PAYMENT;
                pay.putExtra("deatil", deatils);
                startActivity(pay);

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lblDetailView.setText("");
                TxtName.setText("");
                TxtPayment.setText("");
                txtMobileNumber.setText("");
            }
        });

        BtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String phoneNumber = txtMobileNumber.getText().toString();
                //    intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });


        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askCamaraPermissions();

            }
        });


//        btnGallary.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Gallary button is Clicked.", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
    }

    private void askCamaraPermissions() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERM_CODE );
        } else {
            OpenCamara();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                OpenCamara();
            }else {
                Toast.makeText(this, "Camara Permission is Required to Use camara", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void OpenCamara() {
        Intent camara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camara, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
           Intent intent = new Intent(MainActivity.this,ActivityCamara.class);
           intent.putExtra("my_Image" , image);
           startActivity(intent);
//           SelectImage.setImageBitmap(image);
        }
    }
}