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
    public int getTableStatusByID(int tableID){
        int status = 0;
        String sqlQuery = "select * from t_table where table_id  = " + tableID;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            status = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_table_status ));
            cursor.moveToNext();
        }
        return status;
    }

    public boolean updateTableStatusByID(int tableID, int status){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_table_status, status);

        long check = sqLiteDatabase.update(CreateDatabase.TB_table, contentValues, CreateDatabase.TB_table_tableid + " = '" + tableID + " ' ", null);
        if(check != 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteTableByID(int tableID){
        long check = sqLiteDatabase.delete(CreateDatabase.TB_table, CreateDatabase.TB_table_tableid + " = " + tableID, null);
        if(check != 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean updateTableNameByID(int tableID, String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_table_tablename, name);

        long check = sqLiteDatabase.update(CreateDatabase.TB_table, contentValues, CreateDatabase.TB_table_tableid + " = '" + tableID + " ' ", null);
        if(check != 0){
            return true;
        }else{
            return false;
        }
    }
}
