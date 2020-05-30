package com.hoinguyen.uitcoffeeapp.FragmentApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hoinguyen.uitcoffeeapp.Activity.Activity_Add_Table;
import com.hoinguyen.uitcoffeeapp.R;

public class ShowTableFragment extends Fragment {
    public static int REQUEST_CODE_ADD = 111;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_show_table, container,false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itAddTable = menu.add(1,R.id.itAddTable,1,R.string.addtable );
        itAddTable.setIcon(R.drawable.table_free);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_ADD){
            if(resultCode == Activity.RESULT_OK);
            Intent intent = data;
            boolean check = intent.getBooleanExtra("Result add table", false);
            if(check){
                Toast.makeText(getActivity(), getResources().getString(R.string.themthanhcong),Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), getResources().getString(R.string.themthatbai),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
