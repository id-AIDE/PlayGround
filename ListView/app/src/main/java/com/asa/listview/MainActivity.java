/*
 * Copyright (c) 2015 APRIJAL PASARIBU.
 */

package com.asa.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    public String[] provinsi = new String[]{"Aceh", "Sumatera Utara", "Sumatera Barat", "Riau", "Jambi",
            "Sumatera Selatan", "Bengkulu", "Lampung", "Bangka Belitung", "Kepulauan Riau",
            "Jakarta", "Jawa Barat", "Jawa Tengah", "Yogyakarta", "Jawa Timur",
            "Banten", "Bali", "Nusa Tenggara Barat", "Nusa Tenggara Timur", "Kalimantan Barat",
            "Kalimantan Tengah", "Kalimantan Selatan", "Kalimantan Timur", "Sulawesi Utara",
            "Sulawesi Tengah","Sulawesi Selatan","Sulawesi Tenggara","Gorontalo","Sulawesi Barat",
            "Maluku","Maluku Utara","Papua Barat","Papua"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnClick = (Button)findViewById(R.id.btnClick);
        final ListView listView = (ListView)findViewById(R.id.listView);
        List<String> listPrivinsi = new ArrayList<String>();
        for(int x = 0; x < provinsi.length; x++){
            listPrivinsi.add(provinsi[x]);
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listPrivinsi);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(adapter);
            }
        });

    }
}
