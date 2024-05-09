package com.wd_1_24.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceDetailsActivity extends AppCompatActivity {

    private String serviceDetails1[][] =
            {
                    {"Type : Gaming Pro","Processor, RAM : I7 12700H, 32GB","Hard Disk : 1TB NVME","Graphics Card : RTX4060Ti","Price : 540,000"},
                    {"Type : Gaming Lite ","Processor, RAM : I5 12200F, 8GB","Hard Disk : 256GB SSD","Graphics Card : GTX1650","Price : 220,000"},
                    {"Type : Video Editing ","Processor, RAM : I7 12500H, 32GB","Hard Disk : 1TB NVME","Graphics Card : RTX4060","Price : 450,000"},
                    {"Type : Photography ","Processor, RAM : I7 12600H, 32GB","Hard Disk : 1TB NVME","Graphics Card : RTX3060Ti","Price : 430,000"},
                    {"Type : Office Pc ","Processor, RAM : I5 12100F, 8GB","Hard Disk : 256GB SSD","Graphics Card : Vega7","Price : 210,000"}

            };

    private String serviceDetails2[][] =
            {
                    {"Software : Photoshop","Version : CC 2023","License : Subscription","Platform : Windows/Mac","Price : 18,200"},
                    {"Software : Microsoft Office","Version : 365","License : Subscription","Platform : Windows/Mac","Price : 10,400"},
                    {"Software : Operating System","Version : Windows 11","License : Retail","Platform : Windows","Price : 28,200"},
                    {"Software : Antivirus","Version : Security Pro","License : Annual","Platform : Windows/Mac","Price : 4,500"},
                    {"Software : Video Editing Software","Version : Premiere Pro","License : Subscription","Platform : Windows/Mac","Price : 27,500"}

            };

    private String serviceDetails3[][] =
            {
                    {"Service : Cloud Hosting","Type : Infrastructure as a Service (IaaS)","Features : Scalable, Pay-as-you-go","Platform : Cloud","Price : Varies"},
                    {"Service : VPN Service","Type : Virtual Private Network","Features : Secure Connection, Anonymity","Platform : Cross-platform","Price : 6,000/month"},
                    {"Service : Network Security","Type : Firewall Protection","Features : Intrusion Detection, Threat Prevention","Platform : Network","Price : 6,500/year"},
                    {"Service : DNS Management","Type : Domain Name System","Features : DNS Hosting, Record Management","Platform : Web-based","Price : 3,500/month"},
                    {"Service : CDN (Content Delivery Network)","Type : Content Delivery Network","Features : Caching, Load Balancing","Platform : Web","Price : Varies based on usage"}

            };

    private String serviceDetails4[][] =
            {
                    {"Upgrade : RAM","Type : DDR4 16GB","Model Number : 3200Mhz","Compatibility : Laptop/Desktop","Price : 16,000"},
                    {"Upgrade : SSD","Type : NVMe M.2 1TB","Model Number : 2200Mhz","Compatibility : Laptop/Desktop","Price : 24,000"},
                    {"Upgrade : Graphics Card","Type : NVIDIA RTX 3080","Model Number : HC5evo7","Compatibility : Desktop","Price : 290,000"},
                    {"Upgrade : CPU Cooler","Type : Liquid Cooler","Model Number : LC588","Compatibility : Desktop","Price : 9,000"},
                    {"Upgrade : Monitor","Type : 4K Ultra HD 27-inch","Model Number : HP4k270","Compatibility : Universal","Price : 70,000"}

            };

    private String serviceDetails5[][] =
            {
                    {"Service : Laptop Screen Replacement","Type : Hardware Repair","Compatibility : Laptop","Duration : 2 days","Price : 15,000"},
                    {"Service : Virus/Malware Removal","Type : Software Cleanup","Compatibility : Desktop/Laptop","Duration : 1 day","Price : 5,000"},
                    {"Service : Data Recovery","Type : Data Restoration","Compatibility : Various Devices","Duration : 3 days","Price : 4,000"},
                    {"Service : Printer Maintenance","Type : Hardware Maintenance","Compatibility : Printers","Duration : 1-2 days","Price : 5,000"},
                    {"Service : Network Troubleshooting","Type : Network Repair","Compatibility : Networks","Duration : 1 day","Price : 2,000"}

            };


    TextView tv;

    Button btnBack;
    HashMap<String,String> item;
    String serviceDetails[][]={};
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);

        tv = findViewById(R.id.textViewServiceDetailTitle);
        btnBack = findViewById(R.id.buttonBack);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Custom-Built PC")==0)
        {
            serviceDetails=serviceDetails1;
        }
        else
        if(title.compareTo("Software Installation")==0)
        {
            serviceDetails=serviceDetails2;
        }
        else
        if(title.compareTo("Networking Services")==0)
        {
            serviceDetails=serviceDetails3;
        }
        else
        if(title.compareTo("Hardware Upgrade")==0)
        {
            serviceDetails=serviceDetails4;
        }
        else
        if(title.compareTo("Repair or Maintenance")==0)
        {
            serviceDetails=serviceDetails5;
        }



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServiceDetailsActivity.this ,FindServiceActivity.class));

            }
        });

        list = new ArrayList();
        for(int i=0;i<serviceDetails.length;i++)
        {
            item = new HashMap<String,String>();
            item.put("line1",serviceDetails[i][0]);
            item.put("line2",serviceDetails[i][1]);
            item.put("line3",serviceDetails[i][2]);
            item.put("line4",serviceDetails[i][3]);
            item.put("line5",serviceDetails[i][4]+" LKR");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
        new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e} );

        ListView lst = findViewById(R.id.LisiViewServiceDetails);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(ServiceDetailsActivity.this, BookServiceActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",serviceDetails[position][0]);
                it.putExtra("text3",serviceDetails[position][1]);
                it.putExtra("text4",serviceDetails[position][2]);
                it.putExtra("text5",serviceDetails[position][3]);
                it.putExtra("text6",serviceDetails[position][4]);
                startActivity(it);
            }
        });

    }
}