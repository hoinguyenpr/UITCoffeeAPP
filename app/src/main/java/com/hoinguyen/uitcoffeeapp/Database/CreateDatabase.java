package com.hoinguyen.uitcoffeeapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDatabase extends SQLiteOpenHelper {
    //    Create table
    //Create table order
    public static String TB_order = "t_order";
    public static String TB_order_orderid = "order_id";
    public static String TB_order_emid = "em_id";
    public static String TB_order_time = "time";
    public static String TB_order_status = "status";
    public static String TB_order_tableid = "table_id";

    //Create table order_detail
    public static String TB_orderdetail = "order_detail";
    public static String TB_orderdetail_orderid = "order_id";
    public static String TB_orderdetail_foodid = "food_id";
    public static String TB_orderdetail_quantity = "quantity";

    //Create table food
    public static String TB_food = "food";
    public static String TB_food_foodid = "food_id";
    public static String TB_food_namefood = "name_food";
    public static String TB_food_status = "status";
    public static String TB_food_price = "price";
    public static String TB_food_categoryid = "category_id";

    //Create table Category
    public static String TB_category = "category";
    public static String TB_category_categoryid = "category_id";
    public static String TB_category_categoryname = "category_name";

    //Create table invoice
    public static String TB_invoice = "invoice";
    public static String TB_invoice_invoiceid = "invoice_id";
    public static String TB_invoice_orderid = "order_id";
    public static String TB_invoice_time = "time";
    public static String TB_invoice_payment = "payment";

    //Create table Employee
    public static String TB_employee = "employee";
    public static String TB_employee_emid = "em_id";
    public static String TB_employee_username = "username";
    public static String TB_employee_fullname = "fullname";
    public static String TB_employee_phone = "phone";
    public static String TB_employee_startdate = "start_date";
    public static String TB_employee_sex = "sex";
    public static String TB_employee_birthday = "birthday";
    public static String TB_employee_password = "password";
    public static String TB_employee_type = "type";
    public static String TB_employee_status = "status";

    //Create table Store
    public static String TB_store = "store";
    public static String TB_store_storeid = "store_id";
    public static String TB_store_storename = "store_name";
    public static String TB_store_status = "status";
    public static String TB_store_revenue = "revenue";

    //Create table Table
    public static String TB_table = "t_table";
    public static String TB_table_tableid = "table_id";
    public static String TB_table_status = "status";
    public static String TB_table_storeid = "store_id";

    public CreateDatabase(Context context) {
        super(context, "nhom5UIT.db", null, 1);
    }
    String sqlOrder = "CREATE TABLE " + TB_order + " ( " +
            TB_order_orderid + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TB_order_emid + " INTEGER, " +
            TB_order_tableid + " INTEGER, " +
            TB_order_time + " TEXT, " +
            TB_order_status + " INTEGER )";

    String sqlOrderDetail = "CREATE TABLE " + TB_orderdetail + " ( " +
            TB_orderdetail_foodid + " INTEGER, " +
            TB_orderdetail_orderid + " INTEGER, " +
            TB_orderdetail_quantity + " INTEGER ) ";

    String sqlFood = "CREATE TABLE " + TB_food + " ( " +
            TB_food_foodid + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TB_food_categoryid + " INTEGER, " +
            TB_food_namefood + " TEXT, " +
            TB_food_price + " INTEGER," + TB_food_status + " INTEGER) ";

    String sqlCategory = "CREATE TABLE " + TB_category + " ( " +
            TB_category_categoryid + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TB_category_categoryname + " TEXT )";

        String sqlStore = "CREATE TABLE " + TB_store + " ( " +
                TB_store_storeid + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TB_store_storename + " TEXT, " +
                TB_store_revenue + " INTEGER, " +
                TB_store_status + " INTEGER )";

    String sqlEmployee = "CREATE TABLE " + TB_employee + " ( " +
            TB_employee_emid + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TB_employee_fullname + " TEXT, " +
            TB_employee_birthday + " TEXT, " +
            TB_employee_password + " TEXT, " +
            TB_employee_username + " TEXT, " +
            TB_employee_phone + " TEXT, " +
            TB_employee_sex + " INTEGER, " +
            TB_employee_startdate + " TEXT, " +
            TB_employee_status + " INTEGER, " +
            TB_employee_type + " INTEGER)";

    String sqlInvoice = "CREATE TABLE " + TB_invoice + " ( " +
            TB_invoice_invoiceid + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TB_invoice_payment + " INTEGER, " +
            TB_invoice_time + " TEXT, " +
            TB_invoice_orderid + " INTEGER )";

    String sqlTable = "CREATE TABLE " + TB_table + " ( " +
            TB_table_tableid + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TB_table_storeid + " INTEGER, " +
            TB_table_status + " INTEGER )";
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table sql statement
        db.execSQL(sqlOrder);
        db.execSQL(sqlOrderDetail);
        db.execSQL(sqlFood);
        db.execSQL(sqlCategory);
        db.execSQL(sqlStore);
        db.execSQL(sqlEmployee);
        db.execSQL(sqlInvoice);
        db.execSQL(sqlTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
//    public long insertStudent(EmployeeDTO employeeDTO){
//    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();// Câu lệnh xin quyền ghi
//    ContentValues contentValues = new ContentValues();// giúp định nghĩa cặp giá trị tương ứng với tên cột
//    contentValues.put(TB_employee_fullname, employeeDTO.getFullname()); //giá trị nào put vào cặp nào
//    contentValues.put(TB_employee_username, employeeDTO.getUsername());
//    contentValues.put(TB_employee_password, employeeDTO.getPassword());
//    contentValues.put(TB_employee_phone, employeeDTO.getPhone());
//    contentValues.put(TB_employee_sex, employeeDTO.getSex());
//    contentValues.put(TB_employee_startdate, employeeDTO.getStart_date());
//    contentValues.put(TB_employee_status, employeeDTO.getStatus());
//    contentValues.put(TB_employee_type, employeeDTO.getType());
//    contentValues.put(TB_employee_birthday, employeeDTO.getBirthday());
//
//    long result = sqLiteDatabase.insert(TB_employee, null, contentValues);
//    //nullColumnHack muốn điền null giá trị cột nào đó
//    sqLiteDatabase.close();
//    return result;
//    }
//
//    public List<EmployeeDTO> getAllStudents() {
//        List<EmployeeDTO> students = new ArrayList<>();
//        String sqlSELECT = "SELECT * FROM " + TB_employee;
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery(sqlSELECT, null);
//        //Cursor con trỏ easy to understand lắm
//        Log.e("Size", cursor.getCount() +"");
//        if (cursor.getCount() > 0) {
//            cursor.moveToFirst();//di chuyển con trỏ tới vị trí đầu tiên
//            while (!cursor.isAfterLast()) {//đọc đến vị trí cuối cùng thì dừng
//                int id = cursor.getInt(cursor.getColumnIndex(TB_employee_emid));
//                String name = cursor.getString(cursor.getColumnIndex(TB_employee_fullname));
//
//                EmployeeDTO employeeDTO = new EmployeeDTO();
//                employeeDTO.setEm_id(id);
//                employeeDTO.setFullname(name);
//                students.add(employeeDTO);
//
//                cursor.moveToNext();
//            }
//            cursor.close();
//        }
//        return students;
//    }
}