package com.hoinguyen.uitcoffeeapp.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoinguyen.uitcoffeeapp.DTO.EmployeeDTO;
import com.hoinguyen.uitcoffeeapp.Database.CreateDatabase;

public class EmployeeDAO {
    SQLiteDatabase sqLiteDatabase;

    public EmployeeDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        sqLiteDatabase = createDatabase.open();
    }

    //hàm thêm nhân viên
    public long AddEmployee(EmployeeDTO employeeDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_employee_fullname, employeeDTO.getFullname());
        contentValues.put(CreateDatabase.TB_employee_username, employeeDTO.getUsername());
        contentValues.put(CreateDatabase.TB_employee_password, employeeDTO.getPassword());
        contentValues.put(CreateDatabase.TB_employee_sex, employeeDTO.getSex());
        contentValues.put(CreateDatabase.TB_employee_phone, employeeDTO.getPhone());
        contentValues.put(CreateDatabase.TB_employee_birthday, employeeDTO.getBirthday());
        contentValues.put(CreateDatabase.TB_employee_startdate, employeeDTO.getStart_date());
        contentValues.put(CreateDatabase.TB_employee_type, employeeDTO.getType());
        contentValues.put(CreateDatabase.TB_employee_status, employeeDTO.getStatus());

        long result = sqLiteDatabase.insert(CreateDatabase.TB_employee, null, contentValues);
        return result;
    }

    //hàm kiểm tra có nhân viên nào không
    public boolean checkEmployee(){
        String query = "SELECT * FROM " + CreateDatabase.TB_employee;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.getCount() != 0){
            return true;
        }else{
            return false;
        }
    }
    //hàm kiểm tra đăng nhập
    public boolean EmployeeLogin(String username, String password) {
        String query = "SELECT * FROM " + CreateDatabase.TB_employee + " WHERE " + CreateDatabase.TB_employee_username + " = '" + username +
                "' AND " + CreateDatabase.TB_employee_password + " = '" + password +"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }
}
