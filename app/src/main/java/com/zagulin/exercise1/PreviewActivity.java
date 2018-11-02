package com.zagulin.exercise1;

import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PreviewActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PreviewActivity";
    private static final String EXTRA_EMAIL_ADDRESSES_DEFAULT_VALUE = "konstzv@gmail.com";
    private static final String EXTRA_EMAIL_SUBJECT_DEFAULT_VALUE = "Test";
    private static final String EXTRA_EMAIL_MESSAGE_KEY = "EXTRA_EMAIL_MESSAGE";
    private static final String MAIL_TO_URI = "mailto:";


    @Nullable
    private String message;

    public static Intent intent(@NonNull Context context, @NonNull String message) {
        Intent intent = new Intent(context, PreviewActivity.class);
        intent.putExtra(EXTRA_EMAIL_MESSAGE_KEY, message);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        setContentView(R.layout.activity_preview);
        Intent intent = getIntent();
        if (intent == null) {
            Log.e(TAG, "intent is null");
            return;
        }
        message = intent.getStringExtra(EXTRA_EMAIL_MESSAGE_KEY);
        if (message == null) {
            Log.e(TAG, "message from intent extra is null");
            return;
        }


        TextView textView = findViewById(R.id.preview_tv);
        if (textView == null) {
            Log.e(TAG, "textView is null");
            return;
        }
        Button sendBtn = findViewById(R.id.send_button);
        if (sendBtn == null) {
            Log.e(TAG, "sendBtn is null");
            return;
        }

        sendBtn.setOnClickListener(this);

        textView.setText(message);

    }

    @Override
    public void onClick(View v) {
        if (message == null) {
            Toast.makeText(this, R.string.internal_error, Toast.LENGTH_LONG).show();
            Log.e(TAG, "attempt to send a null message");
            return;
        }

        emailMe(message);
    }


    private void emailMe(@NonNull String message) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);

        intent.setData(Uri.parse(MAIL_TO_URI));
        intent.putExtra(Intent.EXTRA_EMAIL, EXTRA_EMAIL_ADDRESSES_DEFAULT_VALUE);
        intent.putExtra(Intent.EXTRA_SUBJECT, EXTRA_EMAIL_SUBJECT_DEFAULT_VALUE);
        intent.putExtra(Intent.EXTRA_TEXT, message);


        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            return;
        }
        Toast.makeText(this, R.string.no_email_client_error, Toast.LENGTH_LONG).show();
    }
}
