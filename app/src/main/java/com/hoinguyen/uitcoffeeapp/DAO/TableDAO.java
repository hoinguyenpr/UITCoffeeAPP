package com.hoinguyen.uitcoffeeapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hoinguyen.uitcoffeeapp.DTO.TableDTO;
import com.hoinguyen.uitcoffeeapp.Database.CreateDatabase;

public class TableDAO {
    SQLiteDatabase sqLiteDatabase;

    public TableDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        sqLiteDatabase = createDatabase.open();
    }

    public boolean AddTable(TableDTO tableDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_table_tablename, tableDTO.getTable_name());

        long result = sqLiteDatabase.insert(CreateDatabase.TB_table, null, contentValues);
        if(result != 0){
            return true;
        }else{
            return false;
        }
    }
}
