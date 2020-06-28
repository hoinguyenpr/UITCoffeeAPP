package com.hoinguyen.uitcoffeeapp.FragmentApp;

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
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hoinguyen.uitcoffeeapp.Activity.Add_New_Employee_Activity;
import com.hoinguyen.uitcoffeeapp.CustomAdapter.ShowEmployeeAdapter;
import com.hoinguyen.uitcoffeeapp.DAO.EmployeeDAO;
import com.hoinguyen.uitcoffeeapp.DTO.EmployeeDTO;
import com.hoinguyen.uitcoffeeapp.R;

import java.util.List;

public class ShowEmployeeFragment extends Fragment {

    ListView listEmployee;
    EmployeeDAO employeeDAO;
    List<EmployeeDTO> employeeDTOList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_show_employee, container,false);
        setHasOptionsMenu(true);

        listEmployee = view.findViewById(R.id.listViewEmployee);

        employeeDAO = new EmployeeDAO(getActivity());


//        ShowEmployeeAdapter showEmployeeAdapter = new ShowEmployeeAdapter(getActivity(), R.layout.custom_layout_showemployee, employeeDTOList);
//        listEmployee.setAdapter(showEmployeeAdapter);
//        showEmployeeAdapter.notifyDataSetChanged();
        ShowEmployeeList();

        //Đăng kí context menu
        registerForContextMenu(listEmployee);

        return view;
    }

    private void ShowEmployeeList(){
        employeeDTOList = employeeDAO.getListOfEmployee();
        ShowEmployeeAdapter showEmployeeAdapter = new ShowEmployeeAdapter(getActivity(), R.layout.custom_layout_showemployee, employeeDTOList);
        listEmployee.setAdapter(showEmployeeAdapter);
        showEmployeeAdapter.notifyDataSetChanged();
    }

    //Khởi tạo context menu
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.edit_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int place = menuInfo.position;
        int employeeid = employeeDTOList.get(place).getEm_id();
        switch (id){
            case R.id.itEdit:
                Intent iRegister = new Intent(getActivity(), Add_New_Employee_Activity.class);
                iRegister.putExtra("employeeid", employeeid);
                startActivity(iRegister);
                ;break;
            case R.id.itDelete:
                boolean check = employeeDAO.DeleteEmployeeByEmID(employeeid);
                if(check){
                    Toast.makeText(getActivity(), getResources().getString(R.string.delete_successful),Toast.LENGTH_SHORT).show();
                    ShowEmployeeList();
                }else{
                    Toast.makeText(getActivity(), getResources().getString(R.string.delete_failed),Toast.LENGTH_SHORT).show();
                }
                ;break;
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itAddEmployee = menu.add(1,R.id.itAddEmployee,1,R.string.addemployee );
        itAddEmployee.setIcon(R.drawable.add_employee);
        itAddEmployee.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itAddEmployee:
                Intent iRegister = new Intent(getActivity(), Add_New_Employee_Activity.class);
                startActivity(iRegister);
                ;break;
        }
        return true;
    }
}
