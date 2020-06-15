package com.hoinguyen.uitcoffeeapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.hoinguyen.uitcoffeeapp.DTO.TableDTO;
import com.hoinguyen.uitcoffeeapp.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

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

    public List<TableDTO> ListAllTable(){
        List<TableDTO> tableDTOS = new ArrayList<TableDTO>();
        String sqlquery = "SELECT * FROM " + CreateDatabase.TB_table;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlquery,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            TableDTO tableDTO = new TableDTO();
            tableDTO.setTable_id(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_table_tableid)));
            tableDTO.setTable_name(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_table_tablename)));

            tableDTOS.add(tableDTO);
            cursor.moveToNext();
        }
        return tableDTOS;
    }
}
