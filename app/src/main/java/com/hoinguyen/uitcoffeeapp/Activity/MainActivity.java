package com.hoinguyen.uitcoffeeapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Messenger;

import com.hoinguyen.uitcoffeeapp.Database.CreateDatabase;
import com.hoinguyen.uitcoffeeapp.R;

import static java.security.AccessController.getContext;

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
