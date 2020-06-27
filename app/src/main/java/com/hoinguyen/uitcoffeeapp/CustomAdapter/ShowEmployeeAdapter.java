package com.hoinguyen.uitcoffeeapp.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoinguyen.uitcoffeeapp.DTO.EmployeeDTO;
import com.hoinguyen.uitcoffeeapp.R;

import java.util.List;

public class ShowEmployeeAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<EmployeeDTO>employeeDTOList;
    ViewHolderEmployee viewHolderEmployee;

    public ShowEmployeeAdapter(Context context, int layout, List<EmployeeDTO>employeeDTOList){
        this.context = context;
        this.layout = layout;
        this.employeeDTOList = employeeDTOList;
    }
    @Override
    public int getCount() {
        return employeeDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return employeeDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return employeeDTOList.get(position).getEm_id();
    }

    public class ViewHolderEmployee{
        ImageView imImageOfEmployee;
        TextView txtNameOfEmployee, txtPhoneNumber;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolderEmployee = new ViewHolderEmployee();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, parent, false);

            viewHolderEmployee.imImageOfEmployee = view.findViewById(R.id.imImageOfEmployee);
            viewHolderEmployee.txtNameOfEmployee = view.findViewById(R.id.txtNameOfEmployee);
            viewHolderEmployee.txtPhoneNumber = view.findViewById(R.id.txtPhoneNumber);

            view.setTag(viewHolderEmployee);
        }else{
            viewHolderEmployee = (ViewHolderEmployee) view.getTag();
        }

        EmployeeDTO employeeDTO = employeeDTOList.get(position);
        viewHolderEmployee.txtNameOfEmployee.setText(employeeDTO.getFullname());
        viewHolderEmployee.txtPhoneNumber.setText(employeeDTO.getPhone());
        return view;
    }
}
