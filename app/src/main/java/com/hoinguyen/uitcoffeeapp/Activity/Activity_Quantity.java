package com.hoinguyen.uitcoffeeapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hoinguyen.uitcoffeeapp.DAO.OrderDAO;
import com.hoinguyen.uitcoffeeapp.DTO.OrderDetailDTO;
import com.hoinguyen.uitcoffeeapp.R;

public class Activity_Quantity extends AppCompatActivity implements AdapterView.OnClickListener {
    int tableid, foodid;
    Button btnAcceptAddQuanity;
    EditText edAddQuanity;
    OrderDAO orderDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_addquantity);

        btnAcceptAddQuanity = findViewById(R.id.btnAcceptAddQuanity);
        edAddQuanity = findViewById(R.id.edAddQuanity);

        orderDAO = new OrderDAO(this);

        Intent intent = getIntent();
        tableid = intent.getIntExtra("tableid",0);
        foodid = intent.getIntExtra("foodid", 0);

        btnAcceptAddQuanity.setOnClickListener(this);
//        Log.d("mabansl", tableid +"");
    }

    @Override
    public void onClick(View v) {
        int orderid = (int) orderDAO.getOrderIDbyTableID(tableid, 0);
        boolean check = orderDAO.checkFoodAlreayExist(orderid, foodid);
        if(check){
            //cap nhat mon an da ton tai
            int oldQuantity = orderDAO.getQuantityOfFoodByOrderID(orderid, foodid);
            int newQuantity = Integer.parseInt(edAddQuanity.getText().toString());

             int totalQuantity = oldQuantity + newQuantity;

            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setOrder_id(orderid);
            orderDetailDTO.setFood_id(foodid);
            orderDetailDTO.setQuantity(totalQuantity);
              boolean checkupdate = orderDAO.updateQuantity(orderDetailDTO);
            if(checkupdate){
                Toast.makeText(this,getResources().getString(R.string.notify_add_success), Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,getResources().getString(R.string.notify_add_faild), Toast.LENGTH_SHORT).show();
            }

        }else{
            int quantity_order = Integer.parseInt(edAddQuanity.getText().toString());
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setOrder_id(orderid);
            orderDetailDTO.setFood_id(foodid);
            orderDetailDTO.setQuantity(quantity_order);
            boolean checkupdate = orderDAO.addOrderDetail(orderDetailDTO);
            if(checkupdate){
                Toast.makeText(this,getResources().getString(R.string.notify_add_success), Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,getResources().getString(R.string.notify_add_faild), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
