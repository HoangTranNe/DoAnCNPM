package com.example.testfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPhoneNumberActivity extends AppCompatActivity {

    EditText inpPhone;
    Button btnContinue;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone_number);

        inpPhone = findViewById(R.id.inputPhone);
        btnContinue = findViewById(R.id.btnContinue);

        String[] countryCodes = getResources().getStringArray(R.array.country_codes);// Lấy danh sách các đầu số điện thoại quốc gia từ tệp countries.xml
        Spinner spinnerCountryCodes = findViewById(R.id.spinnerGroupFourteen);// Tìm Spinner trong giao diện người dùng
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryCodes);// Tạo ArrayAdapter để hiển thị danh sách các đầu số điện thoại trong Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// Định dạng giao diện cho danh sách các đầu số điện thoại (mục được hiển thị khi nhấn vào Spinner)
        spinnerCountryCodes.setAdapter(adapter);// Gán ArrayAdapter vào Spinner

        // Xử lý sự kiện khi người dùng chọn một mục trong Spinner
        spinnerCountryCodes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Lấy mục được chọn từ Spinner
                String selectedCountryCode = (String) parent.getItemAtPosition(position);
                // Xử lý các hành động sau khi người dùng chọn một đầu số điện thoại
                // Ví dụ: Hiển thị thông báo, lưu trữ giá trị, v.v.
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có mục nào được chọn
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(LoginPhoneNumberActivity.this, InputOTPActivity.class );
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = inpPhone.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword()
                Intent intent = new Intent(LoginPhoneNumberActivity.this, InputOTPActivity.class );
                intent.putExtra("inphone", phone);
                startActivity(intent);
            }
        });
    }
}