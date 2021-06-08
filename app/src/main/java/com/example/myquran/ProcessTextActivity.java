package com.example.myquran;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;


public class ProcessTextActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_1);

        String selectedText = getIntent().getStringExtra(Intent.EXTRA_PROCESS_TEXT);
        final TextView surahText = findViewById(R.id.surahText);

        final String fullText = ((MyQuranApp) this.getApplication()).getSurahText().getText().toString();

        SpannableString fullSpanneble = (SpannableString) ((MyQuranApp) this.getApplication()).getSurahText().getText();

        ForegroundColorSpan fcsCOLOR = new ForegroundColorSpan(Color.RED);
        int startIndex = fullText.indexOf(selectedText);

        fullSpanneble.setSpan(fcsCOLOR, startIndex, startIndex + selectedText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        surahText.setText(fullSpanneble);

        finish();
    }
}
