package com.wd_1_24.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class BookServiceActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_service);

        tv = findViewById(R.id.textViewBookTitle);
        ed1=findViewById(R.id.editTextData1);
        ed2=findViewById(R.id.editTextData2);
        ed3=findViewById(R.id.editTextData3);
        ed4=findViewById(R.id.editTextData4);
        ed5=findViewById(R.id.editTextData5);

        btnBack =findViewById(R.id.buttonBookBack);


        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String data1 = it.getStringExtra("text2");
        String data2 = it.getStringExtra("text3");
        String data3 = it.getStringExtra("text4");
        String data4 = it.getStringExtra( "text5");
        String data5 = it.getStringExtra( "text6");
        tv.setText(title);
        ed1.setText(data1);
        ed2.setText(data2);
        ed3.setText(data3);
        ed4.setText(data4);
        ed5.setText(data5+" LKR");



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookServiceActivity.this,FindServiceActivity.class));
            }
        });




    }



}