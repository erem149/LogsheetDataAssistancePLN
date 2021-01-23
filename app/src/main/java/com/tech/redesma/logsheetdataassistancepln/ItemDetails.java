package com.tech.redesma.logsheetdataassistancepln;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ItemDetails extends AppCompatActivity {


    TextView textViewpressure, textViewtemperature, textViewhumidity,textViewitemId, textViewdate;
    Button buttonUpdateItem, buttonDeleteItem;
    String date, itemId, pressure, temperature, humidity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.item_details);

        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        itemId = intent.getStringExtra("itemId");
        pressure = intent.getStringExtra("pressure");
        temperature = intent.getStringExtra("temperature");
        humidity = intent.getStringExtra("humidity");

        textViewdate = (TextView)findViewById(R.id.tv_date);
        textViewitemId = (TextView)findViewById(R.id.tv_id);
        textViewpressure = (TextView) findViewById(R.id.tv_item_name);
        textViewtemperature = (TextView) findViewById(R.id.tv_brand);
        textViewhumidity = (TextView) findViewById(R.id.tv_price);

        textViewdate.setText(date);
        textViewitemId.setText(itemId);
        textViewpressure.setText(pressure);
        textViewtemperature.setText(temperature);
        textViewhumidity.setText(humidity);

        getSupportActionBar().setTitle("Rincian Data");

    }
}