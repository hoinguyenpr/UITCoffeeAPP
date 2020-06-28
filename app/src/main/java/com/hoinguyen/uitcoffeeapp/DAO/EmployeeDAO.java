package com.hoinguyen.uitcoffeeapp.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoinguyen.uitcoffeeapp.DTO.EmployeeDTO;
import com.hoinguyen.uitcoffeeapp.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

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
    public int EmployeeLogin(String username, String password) {
        String query = "SELECT * FROM " + CreateDatabase.TB_employee + " WHERE " + CreateDatabase.TB_employee_username + " = '" + username +
                "' AND " + CreateDatabase.TB_employee_password + " = '" + password +"'";
        int employeeID = 0;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            employeeID = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_employee_emid));
            cursor.moveToNext();
        }
        return employeeID;
    }

    public List<EmployeeDTO> getListOfEmployee(){
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        String sqlQuery = "select * from " + CreateDatabase.TB_employee;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEm_id(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_employee_emid)));
            employeeDTO.setFullname(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_employee_fullname)));
            employeeDTO.setBirthday(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_employee_birthday)));
            employeeDTO.setPassword(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_employee_password)));
            employeeDTO.setUsername(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_employee_username)));
            employeeDTO.setPhone(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_employee_phone)));
            employeeDTO.setSex(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_employee_sex)));
            employeeDTO.setStart_date(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_employee_startdate)));
            employeeDTO.setStatus(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_employee_status)));
            employeeDTO.setType(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_employee_type)));

            employeeDTOList.add(employeeDTO);
            cursor.moveToNext();
        }
        return employeeDTOList;
    }
    public boolean DeleteEmployeeByEmID(int employeeid){
        long check = sqLiteDatabase.delete(CreateDatabase.TB_employee, CreateDatabase.TB_employee_emid + " = "+employeeid, null);
        if(check != 0){
            return true;
        }else{
            return false;
        }
    }

    public EmployeeDTO getEmployeeByID(int employeeid){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        String sqlQuery = "select * from " + CreateDatabase.TB_employee + " where " + CreateDatabase.TB_employee_emid + " = " + employeeid;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            employeeDTO.setEm_id(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_employee_emid)));
            employeeDTO.setFullname(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_employee_fullname)));
            employeeDTO.setBirthday(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_employee_birthday)));
            employeeDTO.setPassword(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_employee_password)));
            employeeDTO.setUsername(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_employee_username)));
            employeeDTO.setPhone(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_employee_phone)));
            employeeDTO.setSex(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_employee_sex)));
            employeeDTO.setStart_date(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_employee_startdate)));
            employeeDTO.setStatus(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_employee_status)));
            employeeDTO.setType(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_employee_type)));

            cursor.moveToNext();
        }
        return employeeDTO;
    }

    public boolean EditEmployee(EmployeeDTO employeeDTO){
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

        long check = sqLiteDatabase.update(CreateDatabase.TB_employee, contentValues, CreateDatabase.TB_employee_emid + " = " + employeeDTO.getEm_id(), null);
        if(check != 0){
            return true;
        }else{
            return false;
        }
    }

}
