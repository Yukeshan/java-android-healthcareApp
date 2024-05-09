package com.wd_1_24.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername;
    EditText edEmail;
    EditText edPassword;
    EditText edConfirm;
    Button btnReg;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername= findViewById(R.id.editTextRegUsername);
        edEmail= findViewById(R.id.editTextRegEmail);
        edPassword= findViewById(R.id.editTextRegPassword);
        edConfirm= findViewById(R.id.editTextRegConfirmPassword);
        btnReg= findViewById(R.id.buttonRegister);
        tv= findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                if(username.length()==0 || email.length()==0 || password.length()==0 || confirm.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please Fill All details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(password.compareTo(confirm)==0)
                    {
                        if(isvalid(password))
                        {
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(),"Registration successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Password must contain atleast 8 characters, having letter and digit",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Password and Confirm password didn't match",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    public static boolean isvalid(String password)
    {
        int f1=0,f2=0;

        if(password.length()<8)
        {
            return false;
        }
        else
        {
            for(int i=0;i<password.length();i++)
            {
                if(Character.isLetter(password.charAt(i)))
                {
                    f1=1;
                }
            }

            for(int j=0;j<password.length();j++)
            {
                if(Character.isDigit(password.charAt(j)))
                {
                    f2=1;
                }
            }


            if(f1==1 && f2==1) {
                return true;
            }
            return false;

        }

    }
}