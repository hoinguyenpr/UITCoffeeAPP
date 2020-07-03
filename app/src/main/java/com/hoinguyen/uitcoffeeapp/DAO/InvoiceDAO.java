package com.hoinguyen.uitcoffeeapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hoinguyen.uitcoffeeapp.DTO.InvoiceDTO;
import com.hoinguyen.uitcoffeeapp.DTO.OrderDTO;
import com.hoinguyen.uitcoffeeapp.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {
    SQLiteDatabase sqLiteDatabase;

    public InvoiceDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        sqLiteDatabase = createDatabase.open();
    }

    public long AddInvoice(InvoiceDTO invoiceDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_invoice_payment, invoiceDTO.getPayment());
        contentValues.put(CreateDatabase.TB_invoice_time, invoiceDTO.getTime());
        contentValues.put(CreateDatabase.TB_invoice_orderid, invoiceDTO.getOrderid());
        Log.d("payment", "payment " + invoiceDTO.getPayment());
        Log.d("orderid", "orderid " + invoiceDTO.getOrderid());
        Log.d("time", "time " + invoiceDTO.getTime());

        long check = sqLiteDatabase.insert(CreateDatabase.TB_invoice, null, contentValues);
        return check;
    }

    public List<InvoiceDTO> ListAllInvoice(){
        List<InvoiceDTO> invoiceDTOList = new ArrayList<>();
        String sqlquery = "select * from " + CreateDatabase.TB_invoice;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlquery,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            InvoiceDTO invoiceDTO = new InvoiceDTO();
            invoiceDTO.setInvoiceid(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_invoice_invoiceid)));
            invoiceDTO.setPayment(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_invoice_payment)));
            invoiceDTO.setTime(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_invoice_time)));
            invoiceDTO.setOrderid(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_invoice_orderid)));

            invoiceDTOList.add(invoiceDTO);
            cursor.moveToNext();
        }
        return invoiceDTOList;
    }

    public String getIDEmByOrderID(int orderID){
        String ID = "";
        String sqlQueryID = "select a.em_id from employee a, t_order b where a.em_id = b.em_id and b.order_id = " + orderID;
        String sqlQueryFullname = "select a.em_id from employee a, t_order b where a.em_id = b.em_id and b.order_id = " + orderID;
        Cursor cursor1 = sqLiteDatabase.rawQuery(sqlQueryID, null);
        cursor1.moveToFirst();
        while (!cursor1.isAfterLast()){
            ID = cursor1.getString(cursor1.getColumnIndex(CreateDatabase.TB_employee_emid));
            cursor1.moveToNext();
        }
        return ID;
    }
    public String getNameEmByOrderID(int orderID) {
        String Name = "";
        String sqlQueryFullname = "select a.fullname from employee a, t_order b where a.em_id = b.em_id and b.order_id = " + orderID;
        Cursor cursor1 = sqLiteDatabase.rawQuery(sqlQueryFullname, null);
        cursor1.moveToFirst();
        while (!cursor1.isAfterLast()) {
            Name = cursor1.getString(cursor1.getColumnIndex(CreateDatabase.TB_employee_fullname));
            cursor1.moveToNext();
        }
        return Name;
    }

    public String getNameIDEMByOrderID(int orderID){
        return getIDEmByOrderID(orderID) +" - "+ getNameEmByOrderID(orderID);
    }
}
