package com.hoinguyen.uitcoffeeapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hoinguyen.uitcoffeeapp.CustomAdapter.ShowInvoiceListAdapter;
import com.hoinguyen.uitcoffeeapp.DAO.InvoiceDAO;
import com.hoinguyen.uitcoffeeapp.DTO.InvoiceDTO;
import com.hoinguyen.uitcoffeeapp.R;

import java.util.List;

public class View_Invoice_Activity extends AppCompatActivity implements View.OnClickListener {
    GridView gridView;
    TextView txtTotalRevenue;
    Button btnExitInvoice;
    InvoiceDAO invoiceDAO;
    List<InvoiceDTO> invoiceDTOList;
    ShowInvoiceListAdapter showInvoiceListAdapter;
    long sumrevenue = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_invoice_list);

        gridView = findViewById(R.id.gvInvoiceList);
        btnExitInvoice = findViewById(R.id.btnExitInvoice);
        txtTotalRevenue = findViewById(R.id.txtTotalRevenue);

        btnExitInvoice.setOnClickListener(this);

        invoiceDAO = new InvoiceDAO(this);

        ShowInvoice();

        for(int i = 0; i < invoiceDTOList.size(); i++){
            long revenue = invoiceDTOList.get(i).getPayment();
            sumrevenue += revenue;
        }
        txtTotalRevenue.setText(getResources().getString(R.string.totorevenue) + " " + sumrevenue);
    }

    private void ShowInvoice(){
        invoiceDTOList = invoiceDAO.ListAllInvoice();
        showInvoiceListAdapter = new ShowInvoiceListAdapter(this, R.layout.custom_layout_invoice_list, invoiceDTOList);
        gridView.setAdapter(showInvoiceListAdapter);
        showInvoiceListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
