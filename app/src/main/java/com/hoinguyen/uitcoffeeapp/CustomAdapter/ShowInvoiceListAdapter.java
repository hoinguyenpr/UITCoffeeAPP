package com.hoinguyen.uitcoffeeapp.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hoinguyen.uitcoffeeapp.DAO.InvoiceDAO;
import com.hoinguyen.uitcoffeeapp.DTO.InvoiceDTO;
import com.hoinguyen.uitcoffeeapp.DTO.PaymentDTO;
import com.hoinguyen.uitcoffeeapp.R;

import java.util.List;

public class ShowInvoiceListAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<InvoiceDTO> invoiceDTOList;
    InvoiceDAO invoiceDAO;
    ViewHoldeInvoice viewHoldeInvoice;

    public ShowInvoiceListAdapter(Context context, int layout, List<InvoiceDTO> invoiceDTOList){
        this.context = context;
        this.layout = layout;
        this.invoiceDTOList = invoiceDTOList;
    }

    @Override
    public int getCount() {
        return invoiceDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return invoiceDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return invoiceDTOList.get(position).getInvoiceid();
    }

    public class ViewHoldeInvoice{
        TextView txtInvoiceID, txtEmployeeID, txtTotalPrice, txtTime;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHoldeInvoice  = new ViewHoldeInvoice();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHoldeInvoice.txtEmployeeID = view.findViewById(R.id.txtIV_EmID);
            viewHoldeInvoice.txtInvoiceID = view.findViewById(R.id.txtIV_InvoiceID);
            viewHoldeInvoice.txtTime = view.findViewById(R.id.txtIV_Time);
            viewHoldeInvoice.txtTotalPrice = view.findViewById(R.id.txtIV_Price);

            view.setTag(viewHoldeInvoice);
        }else{
            viewHoldeInvoice = (ViewHoldeInvoice) view.getTag();
        }

        InvoiceDTO invoiceDTO = invoiceDTOList.get(position);
        invoiceDAO = new InvoiceDAO(context);

        viewHoldeInvoice.txtInvoiceID.setText(String.valueOf(invoiceDTO.getInvoiceid()));
        viewHoldeInvoice.txtTotalPrice.setText(String.valueOf(invoiceDTO.getPayment()));
        viewHoldeInvoice.txtEmployeeID.setText(invoiceDAO.getNameIDEMByOrderID(invoiceDTO.getInvoiceid()));
        viewHoldeInvoice.txtTime.setText(invoiceDTO.getTime());

        return view;
    }
}
