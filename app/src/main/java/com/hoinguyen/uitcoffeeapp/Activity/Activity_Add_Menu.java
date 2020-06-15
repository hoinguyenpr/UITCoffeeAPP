package com.hoinguyen.uitcoffeeapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hoinguyen.uitcoffeeapp.CustomAdapter.ShowCategoryAdapter;
import com.hoinguyen.uitcoffeeapp.DAO.CategoryDAO;
import com.hoinguyen.uitcoffeeapp.DAO.FoodDAO;
import com.hoinguyen.uitcoffeeapp.DTO.CategoryDTO;
import com.hoinguyen.uitcoffeeapp.DTO.FoodDTO;
import com.hoinguyen.uitcoffeeapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Activity_Add_Menu extends AppCompatActivity implements View.OnClickListener {

    public static int REQUEST_CODE_ADD_CATEGORY = 113;
    public static int REQUEST_CODE_OPEN_IMAGE = 123;
    ImageButton imgAddCategory;
    Spinner spinCategory;

    FoodDAO foodDAO;
    CategoryDAO categoryDAO;

    List<CategoryDTO> categoryDTOList;
    ShowCategoryAdapter showCategoryAdapter;
    ImageView imImageOfFood;
    Button btnAcceptAddFood, btnExitAddFood;
    String linkImage;
    EditText edAddNameOfFood, edAddPriceOfFood;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_menu);

        categoryDAO = new CategoryDAO(this);
        foodDAO = new FoodDAO(this);

        imgAddCategory = findViewById(R.id.imgAddCategory);
        spinCategory = findViewById(R.id.spinCategory);
        imImageOfFood = findViewById(R.id.imgImageOfFood);
        btnAcceptAddFood = findViewById(R.id.btnAcceptAddFood);
        btnExitAddFood = findViewById(R.id.btnExitAddFood);
        edAddNameOfFood = findViewById(R.id.edAddNameOfFood);
        edAddPriceOfFood = findViewById(R.id.edAddPriceOfFood);

        showSpinnerCategory();

        imgAddCategory.setOnClickListener(this);
        imImageOfFood.setOnClickListener(this);
        btnAcceptAddFood.setOnClickListener(this);
        btnExitAddFood.setOnClickListener(this);

        //Xử lý spinner lấy id/ khong dung cach nay
//        spinCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                TextView textView = view.findViewById(R.id.txtNameOfCategory);
//            }
//        });
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
            case R.id.btnAcceptAddFood:
                int place = spinCategory.getSelectedItemPosition();
                int category_id = categoryDTOList.get(place).getCategory_id();
                String name_food = edAddNameOfFood.getText().toString();
                int price_food = Integer.parseInt(edAddPriceOfFood.getText().toString());


                if(name_food != null && price_food > 0 && !name_food.equals("")){
                    FoodDTO foodDTO = new FoodDTO();
                    foodDTO.setName_food(name_food);
                    foodDTO.setFood_price(price_food);
                    foodDTO.setImage_food(linkImage);
                    foodDTO.setCategory_id(category_id);

                    boolean check = foodDAO.AddFood(foodDTO);
                    if(check){
                        Toast.makeText(this,getResources().getString(R.string.notify_add_success), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this,getResources().getString(R.string.notify_add_faild), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this,getResources().getString(R.string.error_add_food), Toast.LENGTH_SHORT).show();
            }


                Log.d("vitri", place +"");
                ;break;
            case R.id.btnExitAddFood:
                finish();
                break;
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
//                try {
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
//                    imImageOfCategory.setImageBitmap(bitmap);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                linkImage = data.getData().toString();
                imImageOfFood.setImageURI(data.getData());

            }
        }
    }
}
