package com.hoinguyen.uitcoffeeapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hoinguyen.uitcoffeeapp.DAO.FoodDAO;

import com.hoinguyen.uitcoffeeapp.DTO.FoodDTO;
import com.hoinguyen.uitcoffeeapp.R;

public class Activity_Edit_Food extends AppCompatActivity implements View.OnClickListener {

    Button btnAcceptEditFood, btnCancelEditFood;
    EditText edEditFoodName, edEditFoodPrice;
    FoodDAO foodDAO;
    int foodid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.laytout_edit_food);

        btnAcceptEditFood = findViewById(R.id.btnAcceptEditFood);
        btnCancelEditFood = findViewById(R.id.btnCancelEditFood);
        edEditFoodName = findViewById(R.id.edEditFoodName);
        edEditFoodPrice = findViewById(R.id.edEditFoodPrice);

        foodDAO = new FoodDAO(this);

        foodid = getIntent().getIntExtra("foodid", 0);

        FoodDTO foodDTO = new FoodDTO();
        foodDTO = foodDAO.getFoodByID(foodid);
        Log.d("foodname", "foodname " + foodDTO.getName_food());
        Log.d("foodprice", "foodprice " + foodDTO.getFood_price());
        edEditFoodName.setText(foodDTO.getName_food().toString());
        edEditFoodPrice.setText(String.valueOf(foodDTO.getFood_price()));

       btnAcceptEditFood.setOnClickListener(this);
       btnCancelEditFood.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnAcceptEditFood:
                String nameoffood = edEditFoodName.getText().toString();
                int priceoffood = Integer.parseInt(edEditFoodPrice.getText().toString());
                if(!nameoffood.trim().equals("") || nameoffood.trim() != null || priceoffood > 0){
                    boolean check1 = foodDAO.updateNameFoodByID(foodid, nameoffood);
                    boolean check2 = foodDAO.updatePriceFoodByID(foodid, priceoffood);

                    boolean check = false;

                    if(check1 == true || check2 == true){
                        check = true;
                    }
                    Intent intent = new Intent();
                    intent.putExtra("check", check);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }else{
                    Toast.makeText(Activity_Edit_Food.this, getResources().getString(R.string.notifyplsenterdata),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnCancelEditFood:
                finish();
                break;
        }
    }
}
