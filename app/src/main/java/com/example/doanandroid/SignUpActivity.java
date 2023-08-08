package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.doanandroid.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);
        binding.btnDangKi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email = binding.txtEmail.getText().toString();
                String password = binding.txtPassWord.getText().toString();
                String tel = binding.txtTel.getText().toString();
                String username = binding.txtUserName.getText().toString();
                String checkpassword = binding.txtCheckPassWord.getText().toString();

                if(email.equals("")|| password.equals("")){
                    Toast.makeText(SignUpActivity.this,"Không được để trống",Toast.LENGTH_SHORT).show();
                }else{
                    if(password.equals(checkpassword)){
                        Boolean checkUserEmail = databaseHelper.checkEmail(email);

                        if(checkUserEmail== false){
                            Boolean insert = databaseHelper.insertData(email, password,tel,username);

                            if(insert == true){
                                Toast.makeText(SignUpActivity.this,"Đăng kí thành công",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(SignUpActivity.this,"Đăng kí thất bại",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(SignUpActivity.this,"Tài khoản đã tồn tại",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(SignUpActivity.this,"Mật khẩu không hợp lệ",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.txtloginchuyenhuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}