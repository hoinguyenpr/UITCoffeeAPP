package com.hoinguyen.uitcoffeeapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hoinguyen.uitcoffeeapp.DAO.EmployeeDAO;
import com.hoinguyen.uitcoffeeapp.DTO.EmployeeDTO;
import com.hoinguyen.uitcoffeeapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin, btnReg;
    EditText edUsername, edPassword;

    EmployeeDAO employeeDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        btnLogin = findViewById(R.id.btnlgLogin);
        btnReg  = findViewById(R.id.btnlgReg);
        edUsername = findViewById(R.id.edlgUsername);
        edPassword = findViewById(R.id.edlgPassword);

        employeeDAO = new EmployeeDAO(this);

        //Đăng kí sự kiện onclick cho btnLogin
        btnLogin.setOnClickListener(this);//click xong login
        btnReg.setOnClickListener(this);//click xong chuyen den layout dang ki

        ShowRegButton();
    }
    private void ShowRegButton(){
        boolean check = employeeDAO.checkEmployee();
        if(check){
            btnReg.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
        }else{
            btnReg.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
        }
    }

    private void clickBtnLogin(){
        String sUsername =  edUsername.getText().toString();
        String sPassword = edPassword.getText().toString();
       int result =  employeeDAO.EmployeeLogin(sUsername, sPassword);
       if(result != 0){
           int stype = employeeDAO.getTypeByID(result);
           //Log.d("typeid", "type" + stype);
           //Chuyển đến actvitiy Homepage sau khi login success
           Intent iHomePage = new Intent(LoginActivity.this, HomepageActivity.class);
           //put giá trị cho HomepageActivity
           iHomePage.putExtra("username", edUsername.getText().toString());
           iHomePage.putExtra("typeem", stype);
           iHomePage.putExtra("emid", result);
           startActivity(iHomePage);
       }else{
           Toast.makeText(LoginActivity.this, "Đăng nhập thất bại  ", Toast.LENGTH_LONG).show();
       }
    }
    private void clickBtnReg(){
        Intent iReg = new Intent(LoginActivity.this, Add_New_Employee_Activity.class);
        startActivity(iReg);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnlgLogin:
                clickBtnLogin();
                ;break;
            case R.id.btnlgReg:
                clickBtnReg();
                ;break;
        }
    }
}
