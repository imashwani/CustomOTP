package com.example.ashwani.customotp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ComposeMsgActivity extends AppCompatActivity {

    private static final String TAG = "compseAct";
    TextView phoneNumber, otpTv;
    Button proceed;
    Context context;
    Integer  otp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_compose_msg);
        otp=(int)Math.floor(Math.random()*1000000);
        System.out.println("otp is, "+otp);

        proceed=findViewById(R.id.proceedComposeMsg);
        phoneNumber = findViewById(R.id.phone_numer);
        otpTv = findViewById(R.id.otp_tv);

        final String bundlePhone = getIntent().getExtras().getString("contact");

        phoneNumber.setText(bundlePhone);
        otpTv.setText("OTP: "+String.valueOf(otp));

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,SendMessageActivity.class);

                Bundle bundle=new Bundle();
                bundle.putString("contact",bundlePhone.toString());
                bundle.putString("otp",otp.toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
}
