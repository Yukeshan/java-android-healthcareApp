package com.wd_1_24.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyProductDetailActivity extends AppCompatActivity {
    TextView textViewPackageName,textViewCost;
    EditText edDetails;
    Button btnBack,btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_product_detail);

        textViewPackageName = findViewById(R.id.titleProductDetails);
        edDetails = findViewById(R.id.LisiViewProductDetails);
        edDetails.setKeyListener(null);
        textViewCost = findViewById(R.id.textViewProductCost);
        btnBack =  findViewById(R.id.buttonBack);
        btnAddToCart = findViewById(R.id.buttonProductAddCart);

        Intent intent = getIntent();
        textViewPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        textViewCost.setText(intent.getStringExtra("text3"));


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyProductDetailActivity.this,BuyProductActivity.class));
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = textViewPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                if(db.checkCart(username,product)==1)
                {
                    Toast.makeText(getApplicationContext(),"Product already added",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    db.addCart(username, product, price);
                    Toast.makeText(getApplicationContext(),"Record Inserted to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent( BuyProductDetailActivity.this, BuyProductActivity.class));
                }

            }
        });







    }
}