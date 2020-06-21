package com.hoinguyen.uitcoffeeapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hoinguyen.uitcoffeeapp.DTO.OrderDTO;
import com.hoinguyen.uitcoffeeapp.Database.CreateDatabase;

public class OrderDAO {

    SQLiteDatabase sqLiteDatabase;

    public OrderDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        sqLiteDatabase = createDatabase.open();
    }

    public long AddOrder(OrderDTO orderDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_order_tableid, orderDTO.getTable_id());
        contentValues.put(CreateDatabase.TB_order_emid, orderDTO.getEm_id());
        contentValues.put(CreateDatabase.TB_order_time, orderDTO.getTime_order());
        contentValues.put(CreateDatabase.TB_order_status, orderDTO.getStatus());

        long orderid = sqLiteDatabase.insert(CreateDatabase.TB_order, null, contentValues);
        return orderid;
    }
}
