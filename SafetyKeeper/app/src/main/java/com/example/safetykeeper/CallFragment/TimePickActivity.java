package com.example.safetykeeper.CallFragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.example.safetykeeper.R;

public class TimePickActivity extends AppCompatActivity {
    private TimePicker timePicker;
    private ImageButton imageButton;
    private boolean set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_pick);
        timePicker = findViewById(R.id.TimePickActivity_TimePicker);
        imageButton = findViewById(R.id.TimePickActivity_ImageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour, minute;

                if (Build.VERSION.SDK_INT >= 23 ){
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();
                }
                else{
                    hour = timePicker.getCurrentHour();
                    minute = timePicker.getCurrentMinute();
                }

                set = true;
                Intent intent = new Intent();
                intent.putExtra("hour", hour);
                intent.putExtra("minute", minute);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
