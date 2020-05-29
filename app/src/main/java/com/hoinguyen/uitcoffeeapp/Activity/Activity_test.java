//package com.hoinguyen.uitcoffeeapp.Activity;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.hoinguyen.uitcoffeeapp.DTO.EmployeeDTO;
//import com.hoinguyen.uitcoffeeapp.Database.CreateDatabase;
//import com.hoinguyen.uitcoffeeapp.R;
//
//import java.util.List;
//
//public class Activity_test extends AppCompatActivity {
//
//    private CreateDatabase createDatabase;
//    private TextView tvStudents;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout_test);
//        tvStudents = findViewById(R.id.tvStudents);
//        createDatabase = new CreateDatabase(this);
//    }
//    @Override
//    protected void onDestroy() {//khi nào thoát app mới đóng kết nối đến db
//        super.onDestroy();
//        super.onDestroy();
//    }
//
//    public void insertStudent(View view) {
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        employeeDTO.setBirthday("04/12/1997");
//        employeeDTO.setFullname("Nguyen Van A");
//        employeeDTO.setPassword("123456");
//        employeeDTO.setUsername("hoinguyenpr");
//        employeeDTO.setPhone("0981867344");
//        employeeDTO.setSex(1);
//        employeeDTO.setStart_date("04/12/2020");
//        employeeDTO.setStatus(1);
//        employeeDTO.setType(1);
//        long result = createDatabase.insertStudent(employeeDTO);
//        if(result > 0){
//            Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this,"Co loi xay ra", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void listAllStudent(View view) {
//        List<EmployeeDTO> studentList = createDatabase.getAllStudents();
//        String data = "";
//        for(int i = 0; i < studentList.size(); i++){
//            data = data + " | " + studentList.get(i).getEm_id() + studentList.get(i).getFullname();
//            tvStudents.setText(data);
//            Log.e("ABC", studentList.size() +"");
//        }
//    }
//}
