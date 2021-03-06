package com.hoinguyen.uitcoffeeapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoinguyen.uitcoffeeapp.DTO.OrderDTO;
import com.hoinguyen.uitcoffeeapp.DTO.OrderDetailDTO;
import com.hoinguyen.uitcoffeeapp.DTO.PaymentDTO;
import com.hoinguyen.uitcoffeeapp.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

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

    public long getOrderIDbyTableID(int TableID, int status){
        String sqlQuery = "select * from t_order where table_id = " + TableID + " and status = " + status;
        long orderid = 0;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            orderid = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_order_orderid));
            cursor.moveToNext();
        }
        return orderid;
    }


    public boolean checkFoodAlreayExist(int orderid, int foodid){
        String sqlQuery = "select * from " + CreateDatabase.TB_orderdetail + " where " + CreateDatabase.TB_orderdetail_foodid +
                " = " + foodid + " and " + CreateDatabase.TB_orderdetail_orderid + " = " + orderid;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery, null);
        if(cursor.getCount() != 0){
            return true;
        }else{
            return false;
        }
    }

    public int getQuantityOfFoodByOrderID(int orderid, int foodid){
        int quantity = 0;
        String sqlQuery = "select * from " + CreateDatabase.TB_orderdetail + " where " + CreateDatabase.TB_orderdetail_foodid +
                " = " + foodid + " and " + CreateDatabase.TB_orderdetail_orderid + " = " + orderid;

        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            quantity = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_orderdetail_quantity));
            cursor.moveToNext();
        }
        return quantity;
    }

    public boolean updateQuantity(OrderDetailDTO orderDetailDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_orderdetail_quantity, orderDetailDTO.getQuantity());

        long check = sqLiteDatabase.update(CreateDatabase.TB_orderdetail, contentValues, CreateDatabase.TB_orderdetail_orderid + " = " + orderDetailDTO.getOrder_id() + " and " +
                CreateDatabase.TB_orderdetail_foodid + " = " + orderDetailDTO.getFood_id(), null);
        if(check !=0 ){
            return true;
        }else{
            return false;
        }
    }

    public boolean addOrderDetail(OrderDetailDTO orderDetailDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_orderdetail_quantity, orderDetailDTO.getQuantity());
        contentValues.put(CreateDatabase.TB_orderdetail_orderid, orderDetailDTO.getOrder_id());
        contentValues.put(CreateDatabase.TB_orderdetail_foodid, orderDetailDTO.getFood_id());

        long check = sqLiteDatabase.insert(CreateDatabase.TB_orderdetail, null, contentValues);
        if(check !=0 ){
            return true;
        }else{
            return false;
        }
    }

    public List<PaymentDTO>getListOfFoodByOrderID(int orderid){
        String sqlQuery = "select * from " + CreateDatabase.TB_orderdetail + " dt, " +
                CreateDatabase.TB_food + " f where "+
                " dt." + CreateDatabase.TB_orderdetail_foodid + " = f." + CreateDatabase.TB_food_foodid +
                " and " + CreateDatabase.TB_orderdetail_orderid + " = " + orderid;
        List<PaymentDTO> paymentDTOList = new ArrayList<PaymentDTO>();
        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setQuantity(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_orderdetail_quantity)));
            paymentDTO.setPrice(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_food_price)));
            paymentDTO.setName_food(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_food_namefood)));

            paymentDTOList.add(paymentDTO);

            cursor.moveToNext();
        }
        return paymentDTOList;
    }

    public boolean updateStatusOrderByTableID(int tableid, int status){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_order_status, status);

        long check = sqLiteDatabase.update(CreateDatabase.TB_order, contentValues, CreateDatabase.TB_order_tableid + " = " + tableid, null);
        if(check !=0 ){
            return true;
        }else{
            return false;
        }
    }
}
