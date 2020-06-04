package com.hoinguyen.uitcoffeeapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hoinguyen.uitcoffeeapp.CustomAdapter.ShowCategoryAdapter;
import com.hoinguyen.uitcoffeeapp.DAO.CategoryDAO;
import com.hoinguyen.uitcoffeeapp.DTO.CategoryDTO;
import com.hoinguyen.uitcoffeeapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Activity_Add_Menu extends AppCompatActivity implements View.OnClickListener {

    public static int REQUEST_CODE_ADD_CATEGORY = 113;
    public static int REQUEST_CODE_OPEN_IMAGE = 123;
    ImageButton imgAddCategory;
    Spinner spinCategory;
    CategoryDAO categoryDAO;
    List<CategoryDTO> categoryDTOList;
    ShowCategoryAdapter showCategoryAdapter;
    ImageView imImageOfCategory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_menu);

        categoryDAO = new CategoryDAO(this);
        imgAddCategory = findViewById(R.id.imgAddCategory);
        spinCategory = findViewById(R.id.spinCategory);
        imImageOfCategory = findViewById(R.id.imgImageOfFood);

        showSpinnerCategory();

        imgAddCategory.setOnClickListener(this);
        imImageOfCategory.setOnClickListener(this);
    }

    private void showSpinnerCategory(){
        categoryDTOList = categoryDAO.ListAllCategory();
        showCategoryAdapter = new ShowCategoryAdapter(Activity_Add_Menu.this, R.layout.custom_layout_spin_category, categoryDTOList);
        spinCategory.setAdapter(showCategoryAdapter);
        showCategoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.imgAddCategory:
                Intent iAddCategory = new Intent(Activity_Add_Menu.this, Activity_Add_Category.class);
                startActivityForResult(iAddCategory, REQUEST_CODE_ADD_CATEGORY);
                ;break;
            case R.id.imgImageOfFood:
                Intent iOpenImage = new Intent();
                iOpenImage.setType("image/*");
                iOpenImage.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(iOpenImage,"Chon hinh"), REQUEST_CODE_OPEN_IMAGE);
                ;break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_ADD_CATEGORY){
            if(resultCode == Activity.RESULT_OK){

                Intent iDataCategory = data;
                boolean check = iDataCategory.getBooleanExtra("checkcategory",false);
                if(check){
                    showSpinnerCategory();
                    Toast.makeText(this, getResources().getString(R.string.notify_add_success), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, getResources().getString(R.string.notify_add_faild), Toast.LENGTH_SHORT).show();
                }
            }
        }else if(requestCode == REQUEST_CODE_OPEN_IMAGE){

            if(resultCode == Activity.RESULT_OK && data != null){
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                    imImageOfCategory.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
