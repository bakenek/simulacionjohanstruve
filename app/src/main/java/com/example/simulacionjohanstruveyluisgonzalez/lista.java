package com.example.simulacionjohanstruveyluisgonzalez;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class lista extends AppCompatActivity {

    private ListView mlistView;

    private ArrayList <String> libros = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listaa);

        mlistView = findViewById(R.id.listView_libros);
        libros = new ArrayList<>();

        libros.add("aaaa 5");




        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,libros);

        mlistView.setAdapter(adapter);

    }



}
