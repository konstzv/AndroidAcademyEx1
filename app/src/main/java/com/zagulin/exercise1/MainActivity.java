package com.zagulin.exercise1;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Nullable
    private EditText messageET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageET = findViewById(R.id.message_edit_text);
        Button previewBtn = findViewById(R.id.preview_button);
        if (previewBtn == null) {
            Log.e(TAG, "Preview button is null");
            return;
        }

        previewBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (messageET == null) {
            Log.e(TAG, "Message edit text is null");
            return;
        }
        String message = messageET.getText().toString();
        if (message.isEmpty()) {
            Toast.makeText(this, R.string.no_text_message, Toast.LENGTH_LONG).show();
            return;
        }
        startPreviewActivityForMessage(message);

    }

    private void startPreviewActivityForMessage(@NonNull String message) {
        startActivity(PreviewActivity.intent(this, message));
    }
}
