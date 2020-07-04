package com.paypagaar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.paypagaar.util.LocaleHelper;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private ImageView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.lang, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(itemSelected);

        String lang = LocaleHelper.getLanguage(this);

        if (lang.equals("ar")){
            spinner.setSelection(0);
        }
        else if (lang.equals("en")){
            spinner.setSelection(1);
        }
        else if (lang.equals("hi")){
            spinner.setSelection(2);
        }

        button = findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SecondActivity.class));
                finish();
            }
        });
    }

    AdapterView.OnItemSelectedListener itemSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            int pos = adapterView.getSelectedItemPosition();
            String lang = "";
            if (pos == 0){
                lang = "ar";
            }
            else if (pos == 1){
                lang = "en";
            }
            else if (pos == 2){
                lang = "hi";
            }
            LocaleHelper.setLocale(MainActivity.this, lang);

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

}
