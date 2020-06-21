package com.hoinguyen.uitcoffeeapp.FragmentApp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hoinguyen.uitcoffeeapp.Activity.Activity_Add_Menu;
import com.hoinguyen.uitcoffeeapp.Activity.HomepageActivity;
import com.hoinguyen.uitcoffeeapp.CustomAdapter.ShowCategoryFoodAdapter;
import com.hoinguyen.uitcoffeeapp.DAO.CategoryDAO;
import com.hoinguyen.uitcoffeeapp.DTO.CategoryDTO;
import com.hoinguyen.uitcoffeeapp.R;

import java.util.List;
/*
* Hoi Nguyen 1.6.2020
* */

public class ShowMenuFragment extends Fragment {
    GridView gridView;
    List<CategoryDTO> categoryDTOList;
    CategoryDAO categoryDAO;
    FragmentManager fragmentManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_show_menu, container, false);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.addmenu);

        gridView = view.findViewById(R.id.gvShowMenu);

        fragmentManager = getFragmentManager();

        categoryDAO = new CategoryDAO(getActivity());
        categoryDTOList = categoryDAO.ListAllCategory();

        ShowCategoryFoodAdapter adapter = new ShowCategoryFoodAdapter(getActivity(), R.layout.custom_layout_show_category, categoryDTOList);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //20-6 view product when click on grid view ( category)
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int categoryID = categoryDTOList.get(position).getCategory_id();

                ShowFoodListFragment showFoodListFragment = new ShowFoodListFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("categoryid", categoryID);
                showFoodListFragment.setArguments(bundle);

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, showFoodListFragment);

                transaction.commit();
            }
        });

        Bundle bDataMenu = getArguments();
        if(bDataMenu != null){
            int tableid = bDataMenu.getInt("tableid");
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itAddTable = menu.add(1,R.id.itAddMenu,1,R.string.addmenu);
        itAddTable.setIcon(R.drawable.food_120px);
        itAddTable.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itAddMenu:
                Intent iAddCategory = new Intent(getActivity(), Activity_Add_Menu.class);
                startActivity(iAddCategory);
                ;break;
        }
        return super.onOptionsItemSelected(item);
    }
}
