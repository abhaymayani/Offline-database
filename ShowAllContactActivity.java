package com.example.contactdayari;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowAllContactActivity extends AppCompatActivity {

    RecyclerView recycl;
    LinearLayoutManager linearLayoutManager;
    SearchView searchView;

    ArrayList<PersonData_store> arrayList = new ArrayList<PersonData_store>();
    public static MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_data);

        recycl = findViewById(R.id.recycl);
        searchView = findViewById(R.id.searchview);

        myDatabase = new MyDatabase(ShowAllContactActivity.this);
        Cursor cursor;
        cursor = myDatabase.showData();  // TODO: 1/12/2021 select ni qry run karva mate..showdata name ni method call karavi...e method MyDatabase ni andar che

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String contact = cursor.getString(2);

            PersonData_store personDataStore = new PersonData_store();      //PersonData_store class no object banavyo...data store karva mate
            personDataStore.setId(id);
            personDataStore.setName(name);
            personDataStore.setContact(contact);

            arrayList.add(personDataStore);
        }

        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recycl.setLayoutManager(linearLayoutManager);

        RecyclAdapter_showContact recyclAdapterShowContact = new RecyclAdapter_showContact(ShowAllContactActivity.this, arrayList);
        recycl.setAdapter(recyclAdapterShowContact);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                ArrayList<PersonData_store> searchlist1 = new ArrayList();

                for (int i = 0; i < arrayList.size(); i++) {
                    PersonData_store personDataStore = arrayList.get(i);

                    String sname = personDataStore.getName();
                    String scontact = personDataStore.getContact();

                    if (sname.equalsIgnoreCase(query) || scontact.equals(query)) {
                        searchlist1.add(personDataStore);
                    }
                }
                linearLayoutManager = new LinearLayoutManager(ShowAllContactActivity.this, RecyclerView.VERTICAL, false);
                recycl.setLayoutManager(linearLayoutManager);

                RecyclAdapter_showContact recyclAdapterShowContact = new RecyclAdapter_showContact(ShowAllContactActivity.this, searchlist1);
                recycl.setAdapter(recyclAdapterShowContact);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<PersonData_store> searchlist2 = new ArrayList();

                for (int i = 0; i < arrayList.size(); i++) {
                    String str = newText.trim();

                    PersonData_store personDataStore = arrayList.get(i);

                    String sname = personDataStore.getName().toLowerCase();
                    String scontact = personDataStore.getContact();

                    if (sname.contains(str.toLowerCase()) || scontact.contains(str)) {
                        searchlist2.add(personDataStore);
                    }
                }
                linearLayoutManager = new LinearLayoutManager(ShowAllContactActivity.this, RecyclerView.VERTICAL, false);
                recycl.setLayoutManager(linearLayoutManager);

                RecyclAdapter_showContact recyclAdapterShowContact = new RecyclAdapter_showContact(ShowAllContactActivity.this, searchlist2);
                recycl.setAdapter(recyclAdapterShowContact);
                return false;
            }
        });
    }

    public void add(View view) {
        Intent intent = new Intent(ShowAllContactActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowAllContactActivity.this);
        builder.setTitle("EXIT.");
        builder.setIcon(R.drawable.ic_warning);
        builder.setMessage("Are You Want To Sure Exit..?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
                finishAffinity();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}