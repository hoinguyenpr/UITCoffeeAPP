package com.hoinguyen.uitcoffeeapp.FragmentApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hoinguyen.uitcoffeeapp.Activity.Activity_Add_Menu;
import com.hoinguyen.uitcoffeeapp.Activity.HomepageActivity;
import com.hoinguyen.uitcoffeeapp.R;
/*
* Hoi Nguyen 1.6.2020
* */

public class ShowMenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_show_menu, container, false);

        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.addmenu);
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
