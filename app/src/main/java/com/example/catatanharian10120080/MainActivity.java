package com.example.catatanharian10120080;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.example.catatanharian10120080.ui.adapter.Adapter;
import com.example.catatanharian10120080.ui.helper.Helper;
import com.example.catatanharian10120080.ui.model.Data;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//10120080_habib asrib_IF2
public class MainActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> lists = new ArrayList<>();
    Adapter adapter;
    Helper db = new Helper(this);
    Button btnAdd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_info, R.id.navigation_notes, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_button_navigation);
        // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        db = new Helper(getApplicationContext());
        btnAdd = findViewById(R.id.btn);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.list);
        adapter = new Adapter(MainActivity.this, lists);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String id = lists.get(i).getId();
                final String judul = lists.get(i).getJudul();
                final String deskripsi = lists.get(i).getDeskripsi();
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("judul", judul);
                                intent.putExtra("deskripsi", deskripsi);
                                startActivity(intent);
                                break;
                            case 1:
                               db.delete(Integer.parseInt(id));
                               lists.clear();
                                getData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getData();
    }
    private void getData(){
        ArrayList<HashMap<String, String>> rows = db.getAll();
        for (int i = 0; i<rows.size(); i++){
            String id = rows.get(i).get("id");
            String judul = rows.get(i).get("judul");
            String deskripsi = rows.get(i).get("deskripsi");

            Data data = new Data();
            data.setId(id);
            data.setJudul(judul);
            data.setDeskripsi(deskripsi);
            lists.add(data);
        }
        adapter.notifyDataSetChanged();
    }
}

