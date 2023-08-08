package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.doanandroid.databinding.ActivityLoginBinding;
import com.example.doanandroid.databinding.ActivitySignUpBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        databaseHelper= new DatabaseHelper(this);
        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.txtEmail.getText().toString();
                String passsword = binding.txtPassWord.getText().toString();

                if(email.equals("")||passsword.equals("")){
                    Toast.makeText(LoginActivity.this,"Điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkCredentials = databaseHelper.checkEmailPassword(email,passsword);

                    if(checkCredentials == true){
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this, "Đăng nhập không thành công",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.txtdangkichuyenhuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}