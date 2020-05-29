package com.hoinguyen.uitcoffeeapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hoinguyen.uitcoffeeapp.Database.CreateDatabase;
import com.hoinguyen.uitcoffeeapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
//
//        CreateDatabase createDatabase = new CreateDatabase(this);
//        createDatabase.open();
    }
}
