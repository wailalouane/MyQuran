package com.example.myquran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class DouahActivity extends AppCompatActivity {
    TextView DouahText ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douah);
        DouahText=findViewById(R.id.douah_text);
        DouahText.setMovementMethod(new ScrollingMovementMethod());


    }
}