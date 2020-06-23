package com.hoinguyen.uitcoffeeapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hoinguyen.uitcoffeeapp.DAO.TableDAO;
import com.hoinguyen.uitcoffeeapp.R;

public class Activity_Edit_Table extends AppCompatActivity implements View.OnClickListener {

    Button btnAcceptEditTable;
    EditText edEditTableName;
    TableDAO tableDAO;
    int tableid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_edit_table);

        btnAcceptEditTable = findViewById(R.id.btnAcceptEditTable);
        edEditTableName = findViewById(R.id.edEditTableName);

        tableDAO = new TableDAO(this);

        tableid = getIntent().getIntExtra("tableid", 0);

        btnAcceptEditTable.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        String nameoftable = edEditTableName.getText().toString();
        if(nameoftable.trim().equals("") || nameoftable.trim() != null){
            boolean check = tableDAO.updateTableNameByID(tableid, nameoftable);
            Intent intent = new Intent();
            intent.putExtra("check", check);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }else{
            Toast.makeText(Activity_Edit_Table.this, getResources().getString(R.string.notifyplsenterdata),Toast.LENGTH_SHORT).show();
        }
    }
}