package com.doutya.superadmin.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.doutya.superadmin.R;

public class VerifyOtpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        OtpAuth otp = new OtpAuth(this);
        otp.SendOtp(getIntent().getStringExtra("phone"));
        Toast.makeText(this, getIntent().getStringExtra("phone"), Toast.LENGTH_SHORT).show();

        AppCompatButton button = findViewById(R.id.VerifyOtp);

        button.setOnClickListener(View->{
            EditText e = findViewById(R.id.EnterOtp);
            otp.VerifyCode(e.getText().toString());

        });
    }


}