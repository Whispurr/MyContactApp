package com.example.maow6390.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
DatabaseHelper myDb;
    EditText editName;
    Button btnAddData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
    }
}
