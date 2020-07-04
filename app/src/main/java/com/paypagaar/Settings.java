package com.paypagaar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.paypagaar.util.LocaleHelper;

public class Settings extends AppCompatActivity {

    private Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setTitle(getResources().getString(R.string.settings));
        change = findViewById(R.id.change);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.select_lang));
        builder.setItems(R.array.lang, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String lang;
                switch (i){
                    case 0:
                        lang = "ar";
                        break;
                    case 1:
                        lang = "en";
                        break;
                    case 2:
                        lang = "hi";
                        break;
                    default:
                        lang = "en";
                        break;
                }
                LocaleHelper.setLocale(Settings.this, lang);
                recreate();
            }
        });
        final AlertDialog dialog = builder.create();

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return true;
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        finish();
    }
}
