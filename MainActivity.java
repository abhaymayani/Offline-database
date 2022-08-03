package com.example.contactdayari;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button submit;
    EditText name, contact;
    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.submit);
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);

        myDatabase = new MyDatabase(MainActivity.this);

    }

    public void submit(View view) {
        String nm = name.getText().toString();
        String contac = contact.getText().toString();

        myDatabase.insertData(nm, contac);

        Intent intent = new Intent(MainActivity.this, ShowAllContactActivity.class);
        startActivity(intent);
        finish();
    }

   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    // TODO: 1/13/2021  finish


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // TODO: 1/13/2021 not finish


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }*/


}