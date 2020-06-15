package com.hoinguyen.uitcoffeeapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoinguyen.uitcoffeeapp.DTO.FoodDTO;
import com.hoinguyen.uitcoffeeapp.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class FoodDAO {
    SQLiteDatabase sqLiteDatabase;

    public FoodDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        sqLiteDatabase = createDatabase.open();
    }

    public boolean AddFood(FoodDTO foodDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_food_namefood, foodDTO.getName_food());
        contentValues.put(CreateDatabase.TB_food_price, foodDTO.getFood_price());
        contentValues.put(CreateDatabase.TB_food_categoryid, foodDTO.getCategory_id());
        contentValues.put(CreateDatabase.TB_food_image, foodDTO.getImage_food());

        long result = sqLiteDatabase.insert(CreateDatabase.TB_food, null, contentValues);
        if(result != 0){
            return true;
        }else{
            return false;
        }
    }

    public List<FoodDTO> ListAllFood(){
        List<FoodDTO> foodDTOList = new ArrayList<FoodDTO>();
        String sqlquery = "SELECT * FROM " + CreateDatabase.TB_food;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlquery,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setFood_id(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_food_foodid)));
            foodDTO.setName_food(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_food_namefood)));
            foodDTO.setFood_price(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_food_price)));
            foodDTO.setCategory_id(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_food_categoryid)));
            foodDTO.setImage_food(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_food_image)));

            foodDTOList.add(foodDTO);
            cursor.moveToNext();
        }
        return foodDTOList;
    }
}
