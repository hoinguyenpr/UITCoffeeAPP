package com.hoinguyen.uitcoffeeapp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.hoinguyen.uitcoffeeapp.CustomAdapter.ShowEmployeeAdapter;
import com.hoinguyen.uitcoffeeapp.DTO.EmployeeDTO;
import com.hoinguyen.uitcoffeeapp.FragmentApp.ShowEmployeeFragment;
import com.hoinguyen.uitcoffeeapp.FragmentApp.ShowMenuFragment;
import com.hoinguyen.uitcoffeeapp.FragmentApp.ShowTableFragment;
import com.hoinguyen.uitcoffeeapp.R;


public class HomepageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView txtFullname_emp_navigation;
    FragmentManager fragmentManager;
    EmployeeDTO employeeDTO;
    int sTypeEm;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_homepage);

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview_homepage);
        toolbar = findViewById(R.id.toolbar);
//
//        employeeDTO = new EmployeeDTO();
//        Log.d("typeem", employeeDTO.getType() +"");
//        Log.d("typeem", employeeDTO.getFullname() +"");
        //findviewbyID ở một layout khác
        View view = navigationView.getHeaderView(0);
        txtFullname_emp_navigation = view.findViewById(R.id.txtNameofEmployeeNavigation);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.open, R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        //Thuộc tính đinh màu sắc icon, nếu null là màu mặc định
        navigationView.setItemIconTintList(null);

        //set sự kiện click cho Navigation menu
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent1 = getIntent();
        String sUserName = intent1.getStringExtra("username");
        Intent intent2 = getIntent();
        sTypeEm = intent2.getIntExtra("typeem", 0);
        //Log.d("typem", "type la: " + sTypeEm  );

        txtFullname_emp_navigation.setText("Hi, " + sUserName);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction tranShowTable = fragmentManager.beginTransaction();
        ShowTableFragment showTableFragment = new ShowTableFragment();
        tranShowTable.replace(R.id.content, showTableFragment);
        tranShowTable.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id){
            case R.id.itHomepage:
                FragmentTransaction tranShowTable = fragmentManager.beginTransaction();
                ShowTableFragment showTableFragment = new ShowTableFragment();
                tranShowTable.replace(R.id.content, showTableFragment);
                tranShowTable.commit();

                //đóng menu sau khi click
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                ;break;
            case R.id.itMenu:
                FragmentTransaction tranShowMenu = fragmentManager.beginTransaction();
                ShowMenuFragment showMenuFragment = new ShowMenuFragment();
                tranShowMenu.replace(R.id.content, showMenuFragment, null).addToBackStack(null);
                tranShowMenu.commit();

                //đóng menu sau khi click
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                ;break;
//            case R.id.itProfile:
//                FragmentTransaction tranShowEmployee = fragmentManager.beginTransaction();
//                ShowEmployeeFragment showEmployeeFragment = new ShowEmployeeFragment();
//                tranShowEmployee.replace(R.id.content, showEmployeeFragment);
//                tranShowEmployee.commit();
//
//                menuItem.setChecked(true);
//                drawerLayout.closeDrawers();
//                ;break;
            case R.id.itProfile:
               if(sTypeEm == 0){
                   FragmentTransaction tranShowEmployee = fragmentManager.beginTransaction();
                   ShowEmployeeFragment showEmployeeFragment = new ShowEmployeeFragment();
                   //back not destroy :))))
                   tranShowEmployee.replace(R.id.content, showEmployeeFragment, null).addToBackStack(null);
                   tranShowEmployee.commit();
                   menuItem.setChecked(true);
                   drawerLayout.closeDrawers();
               }else{
                   Toast.makeText(HomepageActivity.this, getResources().getString(R.string.younotadmin), Toast.LENGTH_SHORT).show();
               }
                ;break;
            case R.id.itExitApp:
                Intent intentExit = new Intent(HomepageActivity.this, LoginActivity.class );
                startActivity(intentExit);
                finish();
                ;break;
            case R.id.itRevenue:
                Intent iRevenue = new Intent(HomepageActivity.this, View_Invoice_Activity.class);
                //iPayment.putExtra("tableid", tableID);
                startActivity(iRevenue);
                ;break;
        }
        return false;
    }

}
