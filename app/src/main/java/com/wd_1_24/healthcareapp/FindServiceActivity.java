package com.wd_1_24.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_service);

        CardView back = findViewById(R.id.cardBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindServiceActivity.this,HomeActivity.class));
            }
        });

        CardView customPc = findViewById(R.id.cardCustomPc);
        customPc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindServiceActivity.this,ServiceDetailsActivity.class);
                it.putExtra("title","Custom-Built PC");
                startActivity(it);
            }
        });

        CardView softwareInstall = findViewById(R.id.cardSoftwareInstall);
        softwareInstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindServiceActivity.this,ServiceDetailsActivity.class);
                it.putExtra("title","Software Installation");
                startActivity(it);
            }
        });

        CardView networkService = findViewById(R.id.cardNetworkService);
        networkService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindServiceActivity.this,ServiceDetailsActivity.class);
                it.putExtra("title","Networking Services");
                startActivity(it);
            }
        });

        CardView hardwareUpdate = findViewById(R.id.cardHardwareUpdate);
        hardwareUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindServiceActivity.this,ServiceDetailsActivity.class);
                it.putExtra("title","Hardware Upgrade");
                startActivity(it);
            }
        });

        CardView repairOrMaintenance = findViewById(R.id.cardRepairOrMaintenance);
        repairOrMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindServiceActivity.this,ServiceDetailsActivity.class);
                it.putExtra("title","Repair or Maintenance");
                startActivity(it);
            }
        });

    }
}