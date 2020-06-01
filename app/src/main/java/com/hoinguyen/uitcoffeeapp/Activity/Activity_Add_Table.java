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
import com.hoinguyen.uitcoffeeapp.DTO.TableDTO;
import com.hoinguyen.uitcoffeeapp.R;

public class Activity_Add_Table extends AppCompatActivity implements View.OnClickListener{
    EditText edTableName;
    Button btnAcceptAddTable;

    TableDAO tableDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_table);

        //Gán biến bên activity cho các giá trị ở layout
        btnAcceptAddTable = findViewById(R.id.btnAcceptAddTable);
        edTableName = findViewById(R.id.edTableName);

        btnAcceptAddTable.setOnClickListener(this);

        tableDAO = new TableDAO(this);
    }

    @Override
    public void onClick(View view) {
        TableDTO tableDTO = new TableDTO();

        String sNameTable = edTableName.getText().toString();
        tableDTO.setTable_name(sNameTable);

        if(sNameTable != null && !sNameTable.equals("")){
            boolean result = tableDAO.AddTable(tableDTO);
            Intent intent = new Intent();
            intent.putExtra("Result add table", result);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }else{
            Toast.makeText(Activity_Add_Table.this, getResources().getString(R.string.failinfo), Toast.LENGTH_SHORT).show();
        }
    }
}
