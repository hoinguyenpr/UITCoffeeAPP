package com.hoinguyen.uitcoffeeapp.FragmentApp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hoinguyen.uitcoffeeapp.Activity.Activity_Quantity;
import com.hoinguyen.uitcoffeeapp.CustomAdapter.ShowFoodListAdapter;
import com.hoinguyen.uitcoffeeapp.DAO.FoodDAO;
import com.hoinguyen.uitcoffeeapp.DTO.FoodDTO;
import com.hoinguyen.uitcoffeeapp.R;

import java.util.List;

public class ShowFoodListFragment extends Fragment {
    GridView gridView;
    FoodDAO foodDAO;
    List<FoodDTO> foodDTOList;
    ShowFoodListAdapter showFoodListAdapter;
    int tableid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_show_menu, container, false);

        gridView = view.findViewById(R.id.gvShowMenu);

        foodDAO = new FoodDAO(getActivity());

        Bundle bundle = getArguments();
        if(bundle != null){
            int categoryid = bundle.getInt("categoryid");
            tableid = bundle.getInt("tableid");

            foodDTOList = foodDAO.getListFoodbyCategoryID(categoryid);

            //Log.d("dulieu", foodDTOList.size() +"");

            showFoodListAdapter = new ShowFoodListAdapter(getActivity(), R.layout.custom_layoyt_showfoolist, foodDTOList);
            gridView.setAdapter(showFoodListAdapter);
            showFoodListAdapter.notifyDataSetChanged();

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
}
