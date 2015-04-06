package com.asa.listview;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    public SwipeRefreshLayout container;
    ListView listViewMain;
    public String[] provinsi = new String[]{"Aceh", "Sumatera Utara", "Sumatera Barat", "Riau", "Jambi",
            "Sumatera Selatan", "Bengkulu", "Lampung", "Bangka Belitung", "Kepulauan Riau",
            "Jakarta", "Jawa Barat", "Jawa Tengah", "Yogyakarta", "Jawa Timur",
            "Banten", "Bali", "Nusa Tenggara Barat", "Nusa Tenggara Timur", "Kalimantan Barat",
            "Kalimantan Tengah", "Kalimantan Selatan", "Kalimantan Timur", "Sulawesi Utara",
            "Sulawesi Tengah","Sulawesi Selatan","Sulawesi Tenggara","Gorontalo","Sulawesi Barat",
            "Maluku","Maluku Utara","Papua Barat","Papua"};
    public ArrayList<String> list;
    public ArrayAdapter arrayAdapterProvinsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (SwipeRefreshLayout)findViewById(R.id.container);
        container.setColorScheme(android.R.color.holo_green_light,android.R.color.holo_blue_light,android.R.color.holo_orange_light);
        container.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final View v = new View(getApplicationContext());
                v.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        for (int i = 0; i < provinsi.length; ++i) {
                            list.add(provinsi[i]);
                        }
                        arrayAdapterProvinsi.notifyDataSetChanged();
                        container.setRefreshing(false);
                        v.setAlpha(1);
                    }
                });

            }
        });
        list = new ArrayList<String>();
        for(int i =0;i<provinsi.length;++i){
            list.add(provinsi[i]);
        }
        arrayAdapterProvinsi = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listViewMain = (ListView)findViewById(R.id.listViewMain);
        listViewMain.setAdapter(arrayAdapterProvinsi);
        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView,final  View view, final int i, long l) {
                final String item = (String)adapterView.getItemAtPosition(i);
                view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),adapterView.getItemAtPosition(i).toString()+" Dihapus",Toast.LENGTH_SHORT).show();
                        list.remove(item);
                        arrayAdapterProvinsi.notifyDataSetChanged();
                        view.setAlpha(1);
                    }
                });

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            list.clear();
            for(int i =0;i<provinsi.length;++i){
                list.add(provinsi[i]);
            }
            arrayAdapterProvinsi.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
