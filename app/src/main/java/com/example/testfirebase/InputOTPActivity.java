package com.example.testfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InputOTPActivity extends AppCompatActivity {

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length() > 0){
                if(selectedETPosition == 0){
                    selectedETPosition = 1;
                    showKeyBoard(otp2);
                }else if(selectedETPosition == 1){
                    selectedETPosition = 2;
                    showKeyBoard(otp3);
                }else if(selectedETPosition == 2){
                    selectedETPosition = 3;
                    showKeyBoard(otp4);
                }
                else if(selectedETPosition == 3){
                    selectedETPosition = 4;
                    showKeyBoard(otp5);
                }
                else if(selectedETPosition == 4){
                    selectedETPosition = 5;
                    showKeyBoard(otp6);
                }
            }
        }
    };
    EditText otp1,otp2,otp3,otp4,otp5,otp6;
    TextView resendOTP;
    Button btnContinue;
    Boolean resendEnable = false;
    Integer resendTime = 60;

    Integer selectedETPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_otpactivity);

        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        otp5 = findViewById(R.id.otp5);
        otp6 = findViewById(R.id.otp6);

        resendOTP = findViewById(R.id.resendBTN);

        final Button verifyBTN = findViewById(R.id.btnContinue);
        final TextView inpPhone = findViewById(R.id.inputPhone);

        final String getMobile = getIntent().getStringExtra("inphone");

        inpPhone.setText(getMobile);

        otp1.addTextChangedListener(textWatcher);
        otp2.addTextChangedListener(textWatcher);
        otp3.addTextChangedListener(textWatcher);
        otp4.addTextChangedListener(textWatcher);
        otp5.addTextChangedListener(textWatcher);
        otp6.addTextChangedListener(textWatcher);

        showKeyBoard(otp1);

        startCountDownTimer();
        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resendEnable){

                    startCountDownTimer();
                }
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String generateOTP =
                        otp1.getText().toString()+
                                otp2.getText().toString()+
                                otp3.getText().toString()+
                                otp4.getText().toString()+
                                otp5.getText().toString()+
                                otp6.getText().toString();
                if(generateOTP.length() ==6){

                }
            }
        });
    }

    private void showKeyBoard(EditText otpET){
        otpET.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(otpET, InputMethodManager.SHOW_IMPLICIT);
    }
    private void startCountDownTimer(){
        resendEnable = false;
        resendOTP.setTextColor(Color.parseColor("#99000000"));

        new CountDownTimer(resendTime * 1000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                resendOTP.setText("Resend Code (" + (millisUntilFinished/1000)+")");
            }

            @Override
            public void onFinish() {
                resendEnable = true;
                resendOTP.setText("Resend Code");
                resendOTP.setTextColor(getResources().getColor(R.color.blue_300));
            }
        }.start();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_DEL){
            if(selectedETPosition == 5){
                selectedETPosition = 4;
                showKeyBoard(otp5);
            }
            else if(selectedETPosition == 4){
                selectedETPosition = 3;
                showKeyBoard(otp4);
            }
            else if(selectedETPosition == 3){
                selectedETPosition = 2;
                showKeyBoard(otp3);
            }else if(selectedETPosition ==2){
                selectedETPosition=1;
                showKeyBoard(otp2);
            }
            else if(selectedETPosition == 1){
                selectedETPosition = 0;
                showKeyBoard(otp1);
            }

            return true;
        }
        else
        {
            return super.onKeyUp(keyCode, event);
        }
    }
}