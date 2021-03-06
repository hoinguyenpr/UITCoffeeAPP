package com.hoinguyen.uitcoffeeapp.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hoinguyen.uitcoffeeapp.CustomAdapter.ShowPaymentAdapter;
import com.hoinguyen.uitcoffeeapp.DAO.InvoiceDAO;
import com.hoinguyen.uitcoffeeapp.DAO.OrderDAO;
import com.hoinguyen.uitcoffeeapp.DAO.TableDAO;
import com.hoinguyen.uitcoffeeapp.DTO.InvoiceDTO;
import com.hoinguyen.uitcoffeeapp.DTO.PaymentDTO;
import com.hoinguyen.uitcoffeeapp.FragmentApp.ShowTableFragment;
import com.hoinguyen.uitcoffeeapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Activity_Payment extends AppCompatActivity implements View.OnClickListener {

    GridView gridView;
    Button btnExitPayment, btnPayment;
    TextView txtSumPrice;
    OrderDAO orderDAO;
    List<PaymentDTO>paymentDTOList;
    ShowPaymentAdapter showPaymentAdapter;
    long SumofPrice = 0;
    int tableid;
    TableDAO tableDAO;
    FragmentManager fragmentManager;
    int orderid;

    InvoiceDAO invoiceDAO;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_payment);

        gridView = findViewById(R.id.gvPayment);
        btnPayment = findViewById(R.id.btnPayment);
        btnExitPayment = findViewById(R.id.btnExitPayment);
        txtSumPrice = findViewById(R.id.txtSumPrice);

        orderDAO = new OrderDAO(this);
        tableDAO = new TableDAO(this);
        invoiceDAO = new InvoiceDAO(this);

        fragmentManager = getSupportFragmentManager();

        tableid = getIntent().getIntExtra("tableid", 0);
        if(tableid != 0){

            ShowPayment();

            for(int i = 0; i < paymentDTOList.size(); i ++){
                int quantity = paymentDTOList.get(i).getQuantity();
                int price = paymentDTOList.get(i).getPrice();

                SumofPrice += (quantity * price);

            }
            txtSumPrice.setText(getResources().getString(R.string.sum_price) + " " + SumofPrice);
        }

        btnPayment.setOnClickListener(this);
        btnExitPayment.setOnClickListener(this);
    }
    private void ShowPayment(){
        orderid = (int) orderDAO.getOrderIDbyTableID(tableid, 0);
        paymentDTOList = orderDAO.getListOfFoodByOrderID(orderid);

        showPaymentAdapter = new ShowPaymentAdapter(this, R.layout.custom_layout_payment, paymentDTOList);
        gridView.setAdapter(showPaymentAdapter);
        showPaymentAdapter.notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnPayment:
                boolean checkTable = tableDAO.updateTableStatusByID(tableid, 0);
                boolean checkOrder = orderDAO.updateStatusOrderByTableID(tableid, 1);
                if(checkTable && checkOrder){
                    Toast.makeText(Activity_Payment.this, getResources().getString(R.string.payment_success), Toast.LENGTH_SHORT).show();

                    Calendar calendar = Calendar.getInstance();
                    //Format calendar
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");
                    String datePayment  = dateFormat.format(calendar.getTime());
                    InvoiceDTO invoiceDTO = new InvoiceDTO();
                    invoiceDTO.setPayment(SumofPrice);
                    invoiceDTO.setOrderid(orderid);
                    invoiceDTO.setTime(datePayment);
//                    Log.d("payment", "payment " + invoiceDTO.getPayment());
//                    Log.d("orderid", "orderid " + invoiceDTO.getOrderid());
//                    Log.d("time", "time " + invoiceDTO.getTime());
                      long check = invoiceDAO.AddInvoice(invoiceDTO);
//                    Log.d("orderid", "orderid " + orderid );
//                    Log.d("payment", "payment " + SumofPrice);
//                    Log.d("date", "date " + datePayment);
                    if(check > 0){
                        Toast.makeText(Activity_Payment.this,getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Activity_Payment.this,getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT).show();
                    }
                    ShowPayment();
                }else{
                    Toast.makeText(Activity_Payment.this, getResources().getString(R.string.payment_faild), Toast.LENGTH_SHORT).show();
                }
                ;break;
            case R.id.btnExitPayment:
                //Nếu dùng finish thì bàn sẽ không chuyển về trạng thái đúng
                finish();
                //Sau khi nhấn nút thoát, bàn load lại bằng các gọi fragment
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                ShowTableFragment showTableFragment = new ShowTableFragment();
//                transaction.replace(R.id.content, showTableFragment);
//                transaction.commit();
                //code tren bi loi, chua fix duoc 22-6
                ;break;
        }
    }
}
