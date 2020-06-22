package com.hoinguyen.uitcoffeeapp.CustomAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hoinguyen.uitcoffeeapp.DTO.PaymentDTO;
import com.hoinguyen.uitcoffeeapp.R;

import java.util.List;

public class ShowPaymentAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<PaymentDTO>paymentDTOList;
    ViewHolderPayment viewHolderPayment;

    public ShowPaymentAdapter(Context context, int layout, List<PaymentDTO>paymentDTOList){
        this.context = context;
        this.layout = layout;
        this.paymentDTOList = paymentDTOList;
    }
    @Override
    public int getCount() {
        return paymentDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return paymentDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolderPayment{
        TextView txtNameOfFood, txtQuantity, txtPriceOfFood;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolderPayment = new ViewHolderPayment();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, parent, false);

            viewHolderPayment.txtNameOfFood = view.findViewById(R.id.txtNameOfFoodForPayment);
            viewHolderPayment.txtPriceOfFood = view.findViewById(R.id.txtPriceOfPayment);
            viewHolderPayment.txtQuantity = view.findViewById(R.id.txtQuantityOfPayment);

            view.setTag(viewHolderPayment);
        }else{
            viewHolderPayment = (ViewHolderPayment) view.getTag();
        }

        PaymentDTO paymentDTO = paymentDTOList.get(position);

        viewHolderPayment.txtNameOfFood.setText(paymentDTO.getName_food());
        viewHolderPayment.txtPriceOfFood.setText(String.valueOf(paymentDTO.getPrice()));
        viewHolderPayment.txtQuantity.setText(String.valueOf(paymentDTO.getQuantity()));

        return view;
    }

}
