package com.example.maow6390.myapplication;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
DatabaseHelper myDb;
    EditText editName;
    EditText editAge;
    EditText editAddress;
    Button btnAddData;
    String[] fields;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fields = new String[4];
        fields[0] = "ID :";
        fields[1] = "NAME :";
        fields[2] = "AGE :";
        fields[3] = "ADDRESS :";
        myDb = new DatabaseHelper(this);
        //add the layout bars
        editName = (EditText) findViewById(R.id.editText_Name);
        editAge = (EditText) findViewById(R.id.editText_Age);
        editAddress = (EditText) findViewById(R.id.editText_address);
    }
    public void addData(View v) {
        boolean isInserted = (myDb.insertData(editName.getText().toString(),editAge.getText().toString(),editAddress.getText().toString()));

        String text = "";
        if (isInserted == true) {
            Log.d("MyContact", "Success inserting data");
            text = "data inserted";
            Toast toast = Toast.makeText(getApplicationContext(), text,Toast.LENGTH_SHORT);
            toast.show();
            //insert toast message here...
        }
        else {
            text = "data failure";
            Toast toast = Toast.makeText(getApplicationContext(), text,Toast.LENGTH_SHORT);
            toast.show();
                Log.d("MyContact", "SFailer inserting data");
        //insert toast message here...
    }

    }
    public void showMessage (String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
        //AlertDialog.Builder
    }
    public void viewData(View vi) {
        Cursor res = myDb.getAllData();
        if (res.getCount()==0) {
            showMessage("Error","No data found in database");
            Toast toast = Toast.makeText(getApplicationContext(), "No Data found in database",Toast.LENGTH_SHORT);
            toast.show();
            Log.d("MyContact", "No data found in database");
            //output message using Log.d and toast
            return;
        }
        StringBuffer buffer = new StringBuffer();
        //setup a look with cursor (res0 using moveto next
        //append each col to the buffer
        //display message using show message
        res.moveToFirst();
        for (int i=0; i<res.getCount();i++ ) {
            for (int j=0; j<=3; j++) {
                buffer.append(fields[j]);
                buffer.append(res.getString(j));
                buffer.append("\n");

            }
            res.moveToNext();
        }
        showMessage("Data",buffer.toString());
    }
}
