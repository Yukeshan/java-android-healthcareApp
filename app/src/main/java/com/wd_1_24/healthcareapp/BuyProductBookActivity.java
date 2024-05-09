package com.wd_1_24.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyProductBookActivity extends AppCompatActivity {

    EditText edName,edAddress,edPincode,edContact;
    Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_product_book);

        edName=findViewById(R.id.editTextOrderFullname);
        edAddress=findViewById(R.id.editTextOrderAddress);
        edPincode=findViewById(R.id.editTextOrderPincode);
        edContact=findViewById(R.id.editTextOrderContact);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        SharedPreferences sharedPreferences = getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        Database db = new Database(getApplicationContext(),"healthcare",null,1);
        db.addOrder(username,edName.getText().toString(),edAddress.getText().toString(),edContact.getText().toString(),
                edPincode.getText().toString(),Float.parseFloat(price[1]));
        db.removeCart(username,edName.getText().toString());
        Toast.makeText(getApplicationContext(),"Order Placed Successfully",Toast.LENGTH_SHORT).show();

        startActivity(new Intent(BuyProductBookActivity.this,HomeActivity.class));

    }
}