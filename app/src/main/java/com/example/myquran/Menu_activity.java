package com.example.myquran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Menu_activity extends AppCompatActivity {
    ImageView exit_image;
    ImageView Surah_list_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        Surah_list_image = findViewById(R.id.surah_list_image);
        Surah_list_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu_activity.this,HomeActivity.class));

            }
        });
        exit_image=findViewById(R.id.exit_image);
        exit_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}