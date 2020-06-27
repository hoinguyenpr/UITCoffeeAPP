package com.hoinguyen.uitcoffeeapp.FragmentApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hoinguyen.uitcoffeeapp.Activity.Activity_Add_Table;
import com.hoinguyen.uitcoffeeapp.Activity.Activity_Edit_Table;
import com.hoinguyen.uitcoffeeapp.CustomAdapter.ShowTableAdapter;
import com.hoinguyen.uitcoffeeapp.DAO.TableDAO;
import com.hoinguyen.uitcoffeeapp.DTO.TableDTO;
import com.hoinguyen.uitcoffeeapp.R;

import java.util.List;

public class ShowTableFragment extends Fragment {
    public static int REQUEST_CODE_ADD = 111;
    public static int REQUEST_CODE_EDIT = 16;
    GridView gridViewShowTable;
    List<TableDTO> tableDTOList;
    TableDAO tableDAO;
    ShowTableAdapter showTableAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_show_table, container,false);
        setHasOptionsMenu(true);

        gridViewShowTable = view.findViewById(R.id.gvShowTable);

        getActivity().setTitle(R.string.addtable);

        tableDAO = new TableDAO(getActivity());
        tableDTOList = tableDAO.ListAllTable();

        showListTable();
        registerForContextMenu(gridViewShowTable);

        return view;
    }


    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.edit_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int placeTable = menuInfo.position;
        int tableid = tableDTOList.get(placeTable).getTable_id();

        switch (id){
            case R.id.itEdit:
                Intent intent = new Intent(getActivity(), Activity_Edit_Table.class);
                intent.putExtra("tableid", tableid);
                startActivityForResult(intent, REQUEST_CODE_EDIT);
                //Toast.makeText(getActivity(), "Vị trí " + menuInfo.position, Toast.LENGTH_SHORT).show();
                ;break;
            case R.id.itDelete:
                boolean check = tableDAO.deleteTableByID(tableid);
                if(check){
                    //Cập nhật lại danh sách bàn sau khi xóa
                    showListTable();
                    Toast.makeText(getActivity(), getResources().getString(R.string.delete_successful),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), getResources().getString(R.string.delete_failed),Toast.LENGTH_SHORT).show();
                }

                ;break;
        }

        return super.onContextItemSelected(item);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itAddTable = menu.add(1,R.id.itAddTable,1,R.string.addtable );
        itAddTable.setIcon(R.drawable.table_add);
        itAddTable.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itAddTable:
                Intent iAddTable = new Intent(getActivity(), Activity_Add_Table.class);
                startActivityForResult(iAddTable, REQUEST_CODE_ADD);
                ;break;
        }
        return super.onOptionsItemSelected(item);

    }
    private void showListTable(){
        tableDTOList = tableDAO.ListAllTable();
        showTableAdapter = new ShowTableAdapter(getActivity(), R.layout.custom_layout_show_table, tableDTOList);
        gridViewShowTable.setAdapter(showTableAdapter);
        showTableAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_ADD){
            if(resultCode == Activity.RESULT_OK) {
                Intent intent = data;
                boolean check = intent.getBooleanExtra("Result add table", false);
                if (check) {
                    showListTable();
                    Toast.makeText(getActivity(), getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT).show();
                }
            }
        }else if(requestCode == REQUEST_CODE_EDIT){
            if(resultCode == Activity.RESULT_OK){
                Intent intent = data;
                boolean check = intent.getBooleanExtra("check", false);
                showListTable();
                if(check){
                    Toast.makeText(getActivity(), getResources().getString(R.string.notify_add_success),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), getResources().getString(R.string.notify_add_faild),Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
