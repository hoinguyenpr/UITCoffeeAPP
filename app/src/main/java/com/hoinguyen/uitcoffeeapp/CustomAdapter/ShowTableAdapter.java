package com.hoinguyen.uitcoffeeapp.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hoinguyen.uitcoffeeapp.Activity.HomepageActivity;
import com.hoinguyen.uitcoffeeapp.DAO.OrderDAO;
import com.hoinguyen.uitcoffeeapp.DAO.TableDAO;
import com.hoinguyen.uitcoffeeapp.DTO.OrderDTO;
import com.hoinguyen.uitcoffeeapp.DTO.TableDTO;
import com.hoinguyen.uitcoffeeapp.FragmentApp.ShowMenuFragment;
import com.hoinguyen.uitcoffeeapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

//Kế thừa BaseAdapter
public class ShowTableAdapter extends BaseAdapter implements View.OnClickListener {
    Context context;
    int layout;
    List<TableDTO> tableDTOS;
    ViewHolderTable viewHolderTable;
    TableDAO tableDAO;
    OrderDAO orderDAO;
    FragmentManager fragmentManager;

    //Nhận vào danh sách tableDTOs để hiển thị
    public ShowTableAdapter(Context context, int layout, List<TableDTO> tableDTOS){
        this.context = context;
        this.layout = layout;
        this.tableDTOS = tableDTOS;

        tableDAO = new TableDAO(context);
        orderDAO = new OrderDAO(context);
        fragmentManager = ((HomepageActivity)context).getSupportFragmentManager();
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
        int check_table_status = tableDAO.getTableStatusByID(tableDTO.getTable_id());
        if(check_table_status == 1){
            viewHolderTable.imgTable.setImageResource(R.drawable.table_opened);
        }else{
            viewHolderTable.imgTable.setImageResource(R.drawable.table_free);
        }

        viewHolderTable.txtTableName.setText(tableDTO.getTable_name());


        viewHolderTable.imgTable.setTag(i);

        //Để set sự kiện click
        viewHolderTable.imgTable.setOnClickListener(this);

        //20-6
        //Set su kien click cho ImageButton order
        viewHolderTable.imgOrder.setOnClickListener(this);


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
            //20-6
            //Set su kien click cho ImageButton order
            case R.id.imgOrder:
                int place1 = (int) viewHolderTable.imgTable.getTag();
                int tableID = tableDTOS.get(place1).getTable_id();
                Intent getIHomePage = ((HomepageActivity)context).getIntent();
                int emid = getIHomePage.getIntExtra("emid", 0);
                int status = tableDAO.getTableStatusByID(tableID);
                if(status == 0){
                    //Tạo gọi món, và cập nhật lại status của bàn :)))
//                    Log.d("manv", emid +"");
                    Calendar calendar = Calendar.getInstance();
                    //Format calendar
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String dateOrder  = dateFormat.format(calendar.getTime());

                    OrderDTO orderDTO = new OrderDTO();
                    orderDTO.setTable_id(tableID);
                    orderDTO.setEm_id(emid);
                    orderDTO.setTime_order(dateOrder);
                    orderDTO.setStatus(0);

                    long check = orderDAO.AddOrder(orderDTO);
                    tableDAO.updateTableStatusByID(tableID, 1);
                    if(check == 0){
                        Toast.makeText(context, context.getResources().getString(R.string.notify_add_faild), Toast.LENGTH_SHORT).show();
                    }

                }

                FragmentTransaction tranMenutransaction = fragmentManager.beginTransaction();
                ShowMenuFragment showMenuFragment = new ShowMenuFragment();
                Bundle bDataMenu = new Bundle();
                bDataMenu.putInt("tableid", tableID);

                showMenuFragment.setArguments(bDataMenu);
                tranMenutransaction.replace(R.id.content, showMenuFragment).addToBackStack("showtable");
                tranMenutransaction.commit();
                ;break;
        }
    }
}
