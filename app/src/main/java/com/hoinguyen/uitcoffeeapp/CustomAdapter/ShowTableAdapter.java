package com.hoinguyen.uitcoffeeapp.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hoinguyen.uitcoffeeapp.DTO.TableDTO;
import com.hoinguyen.uitcoffeeapp.R;

import java.util.List;

//Kế thừa BaseAdapter
public class ShowTableAdapter extends BaseAdapter implements View.OnClickListener {
    Context context;
    int layout;
    List<TableDTO> tableDTOS;
    ViewHolderTable viewHolderTable;

    //Nhận vào danh sách tableDTOs để hiển thị
    public ShowTableAdapter(Context context, int layout, List<TableDTO> tableDTOS){
        this.context = context;
        this.layout = layout;
        this.tableDTOS = tableDTOS;
    }

    @Override
    public int getCount() {
        return tableDTOS.size();
    }

    @Override
    public Object getItem(int i) {
        return tableDTOS.get(i);
    }

    @Override
    public long getItemId(int i) {
        return tableDTOS.get(i).getTable_id();
    }



    //Tạo ViewHolderTable để tránh việc findviewbyid nhiều lần
    public class ViewHolderTable{
        ImageView imgTable, imgOrder, imgPayment, imgHint;
        TextView txtTableName;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolderTable = new ViewHolderTable();
            view = inflater.inflate(R.layout.custom_layout_show_table, viewGroup, false);
            viewHolderTable.imgTable = view.findViewById(R.id.imgTable);
            viewHolderTable.imgOrder = view.findViewById(R.id.imgOrder);
            viewHolderTable.imgPayment = view.findViewById(R.id.imgPayment);
            viewHolderTable.imgHint = view.findViewById(R.id.imgHint);
            viewHolderTable.txtTableName = view.findViewById(R.id.txtNameTable);

            view.setTag(viewHolderTable);
        }else{
            viewHolderTable = (ViewHolderTable) view.getTag();
        }

        if(tableDTOS.get(i).isChoose()){
            visibleButton();
        }else{
            hintButton();
        }

        TableDTO tableDTO = tableDTOS.get(i);

        viewHolderTable.txtTableName.setText(tableDTO.getTable_name());


        viewHolderTable.imgTable.setTag(i);

        //Để set sự kiện click
        viewHolderTable.imgTable.setOnClickListener(this);

        return view;
    }

    private void visibleButton(){
        viewHolderTable.imgOrder.setVisibility(View.VISIBLE);
        viewHolderTable.imgPayment.setVisibility(View.VISIBLE);
        viewHolderTable.imgHint.setVisibility(View.VISIBLE);
    }
    private void hintButton(){
        viewHolderTable.imgOrder.setVisibility(View.INVISIBLE);
        viewHolderTable.imgPayment.setVisibility(View.INVISIBLE);
        viewHolderTable.imgHint.setVisibility(View.INVISIBLE);
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        viewHolderTable = (ViewHolderTable) ((View)view.getParent()).getTag();
        switch (id){
            case R.id.imgTable:
                int place = (int) view.getTag();
                tableDTOS.get(place).setChoose(true);
                Toast.makeText(context,"Bàn đang chọn", Toast.LENGTH_SHORT).show();
                visibleButton();
                ;break;
        }
    }
}
