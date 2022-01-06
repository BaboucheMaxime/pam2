package com.example.webratingsapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BDSites mydb;
    private String bt_status = "Search";
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    private ActivityResultLauncher<Intent> someActivityResultLauncher;
    RecyclerView recyclerView;
    ArrayList<WebsiteModel> arrayList = new ArrayList<WebsiteModel>();
    ArrayList<Site> arraySite = new ArrayList<Site>();
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        zoviBroadCast();
        mydb = new BDSites(this);
        getContactList();
    }

    private void zoviBroadCast(){
        Receiver mReceiver = new Receiver();
        registerReceiver(mReceiver,
                new IntentFilter("com.example.myapplication"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                String rate = data.getStringExtra("rate");
                String url = data.getStringExtra("url");
                mydb.open();
                Site site = new Site(rate, url);
                mydb.insertSite(site);
                getContactList();
            }
        }
    }

    public void openWebPage(View v) {

        EditText et_url = findViewById(R.id.header);
        String url = et_url.getText().toString();

        if (!url.contains("http://") || !url.contains("https://")) {
            url = "https://" + url;
        }

        Intent j = new Intent(this, DisplayRating.class);
        j.putExtra("URL", url);
        startActivityForResult(j, SECOND_ACTIVITY_REQUEST_CODE);

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }

    public void searchUrl(View v) {

        String message = bt_status + " TEST";

        if (bt_status == "Search") {
            Button tv_title = findViewById(R.id.header2);
            tv_title.setText("Cancel");
            bt_status = "Cancel";
            Toast toast = Toast.makeText(getApplicationContext(),
                    message,
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Button tv_title = findViewById(R.id.header2);
            tv_title.setText("Search");
            bt_status = "Search";
            Toast toast = Toast.makeText(getApplicationContext(),
                    message,
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void getContactList() {

        arraySite = mydb.getAllElements();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(this, arraySite);
        recyclerView.setAdapter(adapter);

    }
}