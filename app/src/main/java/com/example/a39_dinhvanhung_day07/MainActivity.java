package com.example.a39_dinhvanhung_day07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a39_dinhvanhung_day07.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    PasswordUtil passwordUtil;
    String UserName, Password;
    String User = "admin";
    String password = "Admin123*";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        passwordUtil = new PasswordUtil();
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserName = binding.etUserName.getText().toString();
                Password = binding.etPassword.getText().toString();
                if (UserName.equals(User)){
                    if(passwordUtil.hasLength(Password)){
                        if (passwordUtil.hasSpace(Password)){
                            if (Password.trim().equals(password)){
                                Intent itCreateNewNote = new Intent(getBaseContext(),CreateNewNodeActivity.class);
                                startActivity(itCreateNewNote);
                            }
                            else {
                                Toast.makeText(getBaseContext(),"Mật khẩu của bạn chính xác",Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(getBaseContext(),"Mật khẩu không được chứa khoảng trống",Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    else {
                        Toast.makeText(getBaseContext(),"Mật khẩu của bạn phải có ít nhất 6 kí tự",Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                else{
                    Toast.makeText(getBaseContext(),"Tên đăng nhập không chính xác",Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }
}
