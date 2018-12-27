package com.example.ashwani.customotp;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.msg91.sendotp.library.SendOtpVerification;
import com.msg91.sendotp.library.Verification;
import com.msg91.sendotp.library.VerificationListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class SendMessageActivity extends AppCompatActivity implements VerificationListener {
    private static final String TAG = "SendMessageActivity";
    TextView phoneTv, otpTv;
    EditText msgEditText;
    Button send;
    Context context;
    String otp = "0";
    Verification mVerification;
    ContactRoomDatabase contactRoomDatabase;
    ContactDao contactDao;
    String bundleRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        context = this;

        phoneTv = findViewById(R.id.phone_number_send);
        otpTv = findViewById(R.id.otp_tv_send);
        msgEditText = findViewById(R.id.customMsg);
        send = findViewById(R.id.sendMsg);


        Bundle bundle = getIntent().getExtras();
        bundleRec = bundle.getString("contact");
//final String phoneString="9911416637";
        final String phoneString = bundleRec.substring(bundleRec.length() - 10, bundleRec.length());
        Log.d(TAG, "onCreate: " + phoneString);

        phoneTv.setText(phoneString);
        otp = bundle.getString("otp");
        otpTv.setText(otp);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + phoneString + "," + otpTv.getText().toString() + "," + msgEditText.getText().toString());
                mVerification = SendOtpVerification.createSmsVerification
                        (SendOtpVerification
                                .config("+91" + phoneString)
                                .context(context)
                                .message("otp is " + otp + " " + msgEditText.getText().toString())
                                .autoVerification(true)
                                .otp(otp)
                                .otplength("6")
                                .build(), SendMessageActivity.this);
                mVerification.initiate();
                new DatabseAsync().execute();

            }
        });

    }

private class DatabseAsync extends AsyncTask<Void, Void,Void>{
        Contact contact;


    @Override
    protected Void doInBackground(Void... voids) {
        contactRoomDatabase= Room.databaseBuilder(getApplicationContext(),ContactRoomDatabase.class,"contact-database").build();
        contactDao=contactRoomDatabase.contactDao();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        contactDao.insert(new Contact(bundleRec,otp, sdf.format(cal.getTime())));


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Toast.makeText(SendMessageActivity.this,"Msg sent & added to db, Now going BACK",LENGTH_SHORT).show();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },1500);
        super.onPostExecute(aVoid);
    }
}


    @Override
    public void onInitiated(String response) {
        Log.d(TAG, "Initialized!" + response);
        //OTP successfully resent/sent.
    }

    @Override
    public void onInitiationFailed(Exception exception) {
        Log.e(TAG, "Verification initialization failed: " + exception.getMessage());
        //sending otp failed.
    }

    @Override
    public void onVerified(String response) {
        Log.d(TAG, "Verified!\n" + response);
        //OTP verified successfully.
    }

    @Override
    public void onVerificationFailed(Exception exception) {
        Log.e(TAG, "Verification failed: " + exception.getMessage());
        //OTP  verification failed.
    }
}
