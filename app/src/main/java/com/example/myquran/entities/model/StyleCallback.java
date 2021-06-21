package com.example.myquran.entities.model;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myquran.HomeActivity;
import com.example.myquran.R;
import com.example.myquran.connectionbd.DataBaseHelper;

public class StyleCallback implements ActionMode.Callback {
    private TextView mTextView;
    private int page;
    public StyleCallback(TextView textView,int page){
        this.mTextView=textView;
        this.page=page;
    }
    PageStatModel mPageStatModel=new PageStatModel();
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        Log.d("tag","onCreateActionMode");
        MenuInflater inflater =mode.getMenuInflater();
        inflater.inflate(R.menu.select_menu, menu);
        menu.removeItem(android.R.id.selectAll);
        return true;


    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

        Log.d("tag",String.format("onActionItemClicked item=%s/%d", item.toString(), item.getItemId()));
        CharacterStyle cs ;
        int start = mTextView.getSelectionStart();
        int end = mTextView.getSelectionEnd();
        SpannableString fullText=(SpannableString) mTextView.getText();
        ForegroundColorSpan fcsCOLOR = new ForegroundColorSpan(Color.RED);


        SpannableStringBuilder ssd = new SpannableStringBuilder(mTextView.getText());
        switch (item.getItemId()){
            case R.id.voir:
                fullText.setSpan(fcsCOLOR,start,end,SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                mPageStatModel=new PageStatModel(start,end,page);
                DataBaseHelper dataBaseHelper=new DataBaseHelper(mTextView.getContext());
                dataBaseHelper.addPagesStatText(mPageStatModel);

                mTextView.setText(fullText);
                return true;


        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }
}
