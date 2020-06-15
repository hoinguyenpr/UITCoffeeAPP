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

import com.hoinguyen.uitcoffeeapp.DAO.CategoryDAO;
import com.hoinguyen.uitcoffeeapp.DTO.CategoryDTO;
import com.hoinguyen.uitcoffeeapp.R;

public class Activity_Add_Category extends AppCompatActivity implements View.OnClickListener{

    Button btnAcceptAddCategory;
    EditText edAddCategory;
    CategoryDAO categoryDAO;
    CategoryDTO categoryDTO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_category);

        categoryDAO = new CategoryDAO(this);


        btnAcceptAddCategory = findViewById(R.id.btnAcceptAddCategory);
        edAddCategory = findViewById(R.id.edAddCategory);

        btnAcceptAddCategory.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        categoryDTO = new CategoryDTO();

        String sCategoryName = edAddCategory.getText().toString();

        categoryDTO.setCategory_name(sCategoryName);

        if(sCategoryName != null && !sCategoryName.equals("")){

            boolean check = categoryDAO.AddCategory(categoryDTO);
            Intent iDataOfCategory = new Intent();
            iDataOfCategory.putExtra("checkcategory",check);
            setResult(Activity.RESULT_OK, iDataOfCategory);
            finish();

        }else{
            Toast.makeText(this, getResources().getString(R.string.notifyplsenterdata), Toast.LENGTH_SHORT).show();
        }
    }
}
