package com.wd_1_24.healthcareapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String quary1 = "create table users(username text,email text, password text)";
        db.execSQL(quary1);
        String quary2 = "create table cart(username text,product text, price text)";
        db.execSQL(quary2);
        String quary3 = "create table orderplace(username text,product text,address text,contact text,pincode text,price real)";
        db.execSQL(quary3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();

    }

    public int login(String username, String password) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        return result;
    }

    public void addCart(String username, String product, float price) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null, cv);
        db.close();
    }

    public int checkCart(String username, String product) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username = ? and product = ?", str);
        if (c.moveToFirst())
        {
            result = 1;
        }

        db.close();
        return result;

    }

    public void removeCart(String username, String product) {
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart", "username=? and product=?", str);
        db.close();
    }


    public ArrayList getOrderData(String username) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("select * from orderplace where username = ?", str);
        if (c.moveToFirst()) {
            do {
                arr.add(c.getString(1) + "$" + c.getString(2) + "$" + c.getString(3) + "$"
                        + c.getString(4) + "$" + c.getString(5) + "$" + c.getString(6).toString()
                        + "$" + c.getString(7).toString()+ "$" + c.getString(8).toString());

            } while (c.moveToNext());

        }
        db.close();
        return arr;


    }

    public void addOrder(String username, String product,String address,String contact, String pincode, float price) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("product", product);
        contentValues.put("address", address);
        contentValues.put("contact", contact);
        contentValues.put("pincode", pincode);
        contentValues.put("price", price);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace", null, contentValues);
        db.close();

    }

    public ArrayList getCartData(String username)
    {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("select * from cart where username = ?", str);
        if (c.moveToFirst())
        {
            do
            {
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product + "$" + price);
            }while (c.moveToNext());


        }
        db.close();
        return arr;

    }
}



