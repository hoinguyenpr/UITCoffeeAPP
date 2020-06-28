package com.hoinguyen.uitcoffeeapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hoinguyen.uitcoffeeapp.DAO.EmployeeDAO;
import com.hoinguyen.uitcoffeeapp.DTO.EmployeeDTO;
import com.hoinguyen.uitcoffeeapp.FragmentApp.DatePickerFragment;
import com.hoinguyen.uitcoffeeapp.R;

public class Add_New_Employee_Activity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener  {

    EditText edFullName, edUsername, edPassWord, edBirth, edPhone, edStartDay;
    Button btnAccept, btnCancel;
    RadioGroup rgGender;
    RadioButton rdMale, rdFemale;
    TextView txtCreateEmployeeTitle;

    int sGender;
    int employeeid = 0;

    EmployeeDAO employeeDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_employee);

        //Gán biến bên activity cho các giá trị ở layout
        edFullName = findViewById(R.id.edFullName);
        edUsername = findViewById(R.id.edUsername);
        edPassWord = findViewById(R.id.edPassWord);
        edBirth = findViewById(R.id.edBirth);
        edPhone = findViewById(R.id.edPhone);
        edStartDay = findViewById(R.id.edStartDay);
        btnAccept = findViewById(R.id.btnAccept);
        btnCancel = findViewById(R.id.btnCancel);
        rgGender = findViewById(R.id.rgGender);
        rdMale = findViewById(R.id.rdMale);
        rdFemale = findViewById(R.id.rdFemale);
        txtCreateEmployeeTitle = findViewById(R.id.txtCreateEmployeeTitle);


        //
        edBirth.setOnFocusChangeListener(this);
        //edStartDay.setOnFocusChangeListener(this);

        // vi 2 dong nay ma mat 1 tuan fix, discu
        btnAccept.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        employeeDAO = new EmployeeDAO(this);

        employeeid = getIntent().getIntExtra("employeeid", 0);
        if(employeeid != 0){
            txtCreateEmployeeTitle.setText(getResources().getString(R.string.updateemployee));
            edUsername.setText(getResources().getString(R.string.unabletoupdate));
            edUsername.setFocusable(false);
            EmployeeDTO employeeDTO = employeeDAO.getEmployeeByID(employeeid);

            if(employeeDTO.getFullname() != null && employeeDTO.getFullname().equals("")){
                edFullName.setText(employeeDTO.getFullname());
            }
            if(employeeDTO.getPassword() != null && employeeDTO.getPassword().equals("")){
                edPassWord.setText(employeeDTO.getPassword());
            }
            if(employeeDTO.getBirthday() != null && employeeDTO.getBirthday().equals("")){
                edBirth.setText(employeeDTO.getBirthday());
            }
            if(employeeDTO.getStart_date() != null && employeeDTO.getStart_date().equals("")){
                edStartDay.setText(employeeDTO.getStart_date());
            }
            if(employeeDTO.getPhone() != null && employeeDTO.getPhone().equals("")){
                edPhone.setText(employeeDTO.getPhone());
            }
            int Gender = employeeDTO.getSex();
            if(Gender == 0){
                rdMale.setChecked(true);
            }else if (Gender == 1){
                rdFemale.setChecked(true);
            }
        }
    }
    private void AcceptAddEmployee(){
        String sFullname = edFullName.getText().toString();
        String sUsername = edUsername.getText().toString();
        String sPassword = edPassWord.getText().toString();
        switch (rgGender.getCheckedRadioButtonId()){
            case R.id.rdMale:
                sGender = 0;
                break;
            case R.id.rdFemale:
                sGender = 1;
                break;
        }
        String sBirth = edBirth.getText().toString();
        String sPhone = edPhone.getText().toString();
        String sStartDay = edStartDay.getText().toString();
        int type = 1;
        int status = 1;
        if(sUsername == null || sUsername.equals("")){
            Toast.makeText(Add_New_Employee_Activity.this, getResources().getString(R.string.enterusername), Toast.LENGTH_SHORT).show();
        }else if(sPassword == null || sPassword.equals("")){
            Toast.makeText(Add_New_Employee_Activity.this, getResources().getString(R.string.enterpassword), Toast.LENGTH_SHORT).show();
        }else if(sFullname == null || sPassword.equals("")){
            Toast.makeText(Add_New_Employee_Activity.this, getResources().getString(R.string.enterfullname), Toast.LENGTH_SHORT).show();
        }else{
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setFullname(sFullname);
            employeeDTO.setUsername(sUsername);
            employeeDTO.setPassword(sPassword);
            employeeDTO.setBirthday(sBirth);
            employeeDTO.setSex(sGender);
            employeeDTO.setPhone(sPhone);
            employeeDTO.setStart_date(sStartDay);
            employeeDTO.setStatus(status);
            employeeDTO.setType(type);

            long result = employeeDAO.AddEmployee(employeeDTO);
            if(result > 0){
                Toast.makeText(Add_New_Employee_Activity.this,getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(Add_New_Employee_Activity.this,getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void EditEmployee(){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        String sFullname = edFullName.getText().toString();
        String sUsername = edUsername.getText().toString();
        String sPassword = edPassWord.getText().toString();
        String sBirth = edBirth.getText().toString();
        String sPhone = edPhone.getText().toString();
        String sStartDay = edStartDay.getText().toString();
        int type = 1;
        int status = 1;
        switch (rgGender.getCheckedRadioButtonId()){
            case R.id.rdMale:
                sGender = 0;
                break;
            case R.id.rdFemale:
                sGender = 1;
                break;
        }
        employeeDTO.setEm_id(employeeid);
        employeeDTO.setFullname(sFullname);
        employeeDTO.setUsername(sUsername);
        employeeDTO.setPassword(sPassword);
        employeeDTO.setBirthday(sBirth);
        employeeDTO.setSex(sGender);
        employeeDTO.setPhone(sPhone);
        employeeDTO.setStart_date(sStartDay);
        employeeDTO.setStatus(status);
        employeeDTO.setType(type);

        boolean result = employeeDAO.EditEmployee(employeeDTO);
        if(result){
            Toast.makeText(Add_New_Employee_Activity.this,getResources().getString(R.string.editsucess), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(Add_New_Employee_Activity.this,getResources().getString(R.string.editfaild), Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnAccept:
                if(employeeid != 0){
                    //sua nhan vien
                    EditEmployee();
                }else{
                    //them moi nhan vien
                    AcceptAddEmployee();
                }
                ;
                break;
            case R.id.btnCancel:
                finish();
                break;
        }
    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id){
            case R.id.edBirth:
                if(hasFocus){
                    //xuat popup birthday;
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getSupportFragmentManager(),"Ngay sinh");
                };
                break;
//            case R.id.edStartDay:
//                if(hasFocus){
//                    //xuat popup birthday;
//                    DatePickerFragment datePickerFragment = new DatePickerFragment();
//                    datePickerFragment.show(getFragmentManager(),"Ngày Bat dau");
//                };
//                break;

        }
    }
}
