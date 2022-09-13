package com.example.eventhaddlerwithpaymentapp;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentactivity_main);

        EditText lblDetailView = this.findViewById(R.id.lblDetailView);

        String value = getIntent().getStringExtra("deatil");
        lblDetailView.setText(value);
    }
}
