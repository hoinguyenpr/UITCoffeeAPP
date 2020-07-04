package com.hoinguyen.uitcoffeeapp.FragmentApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.hoinguyen.uitcoffeeapp.Activity.Activity_Edit_Food;
import com.hoinguyen.uitcoffeeapp.Activity.Activity_Quantity;
import com.hoinguyen.uitcoffeeapp.CustomAdapter.ShowFoodListAdapter;
import com.hoinguyen.uitcoffeeapp.DAO.FoodDAO;
import com.hoinguyen.uitcoffeeapp.DTO.FoodDTO;
import com.hoinguyen.uitcoffeeapp.R;

import java.util.List;

public class ShowFoodListFragment extends Fragment {
    //today
    public static int REQUEST_CODE_EDIT = 16;

    GridView gridView;
    FoodDAO foodDAO;
    List<FoodDTO> foodDTOList;
    ShowFoodListAdapter showFoodListAdapter;
    int tableid;
    int categoryid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_show_menu, container, false);

        gridView = view.findViewById(R.id.gvShowMenu);
        foodDAO = new FoodDAO(getActivity());

        Bundle bundle = getArguments();
        if(bundle != null){
            categoryid = bundle.getInt("categoryid");
            tableid = bundle.getInt("tableid");

            foodDTOList = foodDAO.getListFoodbyCategoryID(categoryid);

            //Log.d("dulieu", foodDTOList.size() +"");

//            showFoodListAdapter = new ShowFoodListAdapter(getActivity(), R.layout.custom_layoyt_showfoolist, foodDTOList);
//            gridView.setAdapter(showFoodListAdapter);
//            showFoodListAdapter.notifyDataSetChanged();

            //today
            showListFood();
            registerForContextMenu(gridView);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(tableid != 0){
                        Intent iQuantity = new Intent(getActivity(), Activity_Quantity.class);
                        iQuantity.putExtra("tableid", tableid);
                        iQuantity.putExtra("foodid", foodDTOList.get(position).getFood_id());
                        startActivity(iQuantity);
                    }
                }
            });

        }
        return view;
    }

    //today
    private void showListFood(){
        foodDTOList = foodDAO.getListFoodbyCategoryID(categoryid);
        showFoodListAdapter = new ShowFoodListAdapter(getActivity(), R.layout.custom_layoyt_showfoolist, foodDTOList);
        gridView.setAdapter(showFoodListAdapter);
        showFoodListAdapter.notifyDataSetChanged();
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
        int placeFood = menuInfo.position;
        int foodid =foodDTOList.get(placeFood).getFood_id();

        switch (id){
            case R.id.itEdit:
                Intent intent = new Intent(getActivity(), Activity_Edit_Food.class);
                intent.putExtra("foodid", foodid);
                startActivityForResult(intent, REQUEST_CODE_EDIT);
               // Toast.makeText(getActivity(), "Click edit",Toast.LENGTH_SHORT).show();
                break;
            case R.id.itDelete:
                //Toast.makeText(getActivity(), "Click delete",Toast.LENGTH_SHORT).show();
                boolean check = foodDAO.deleteFoodByID(foodid);
                if(check){
                    //Cập nhật lại danh sách bàn sau khi xóa
                    showListFood();
                    Toast.makeText(getActivity(), getResources().getString(R.string.delete_successful),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), getResources().getString(R.string.delete_failed),Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_EDIT){
            if(resultCode == Activity.RESULT_OK){
                Intent intent = data;
                boolean check = intent.getBooleanExtra("check", false);
                showListFood();
                if(check){
                    Toast.makeText(getActivity(), getResources().getString(R.string.notify_add_success),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), getResources().getString(R.string.notify_add_faild),Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
