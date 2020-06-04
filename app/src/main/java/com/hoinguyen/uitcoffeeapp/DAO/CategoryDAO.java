package com.hoinguyen.uitcoffeeapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoinguyen.uitcoffeeapp.DTO.CategoryDTO;
import com.hoinguyen.uitcoffeeapp.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    SQLiteDatabase sqLiteDatabase;

    public CategoryDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        sqLiteDatabase = createDatabase.open();
    }

    public boolean AddCategory(CategoryDTO categoryDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_category_categoryname, categoryDTO.getCategory_name());

        long result = sqLiteDatabase.insert(CreateDatabase.TB_category, null, contentValues);
        if(result != 0){
            return true;
        }else{
            return false;
        }
    }

    public List<CategoryDTO> ListAllCategory(){
        List<CategoryDTO> tableDTOS = new ArrayList<CategoryDTO>();

        String sqlquery = "SELECT * FROM " + CreateDatabase.TB_category;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlquery,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            CategoryDTO tableDTO = new CategoryDTO();
            tableDTO.setCategory_id(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_category_categoryid)));
            tableDTO.setCategory_name(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_category_categoryname)));

            tableDTOS.add(tableDTO);
            cursor.moveToNext();
        }
        return tableDTOS;
    }

}
