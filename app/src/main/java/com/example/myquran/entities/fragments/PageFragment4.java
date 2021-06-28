package com.example.myquran.entities.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myquran.MyQuranApp;
import com.example.myquran.R;
import com.example.myquran.connectionbd.DataBaseHelper;
import com.example.myquran.entities.model.Functions;
import com.example.myquran.entities.model.PageStatModel;
import com.example.myquran.entities.model.StyleCallback;

import java.util.ArrayList;
import java.util.List;

public class PageFragment4 extends Fragment {
    String json;
    final int numero=565;
    int cptShow=2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        json = bundle.getString("json");
        String pagetitre = bundle.getString("titre");
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page_4, container, false);
        TextView pageTitle=rootView.findViewById(R.id.surahName);
        TextView pageNum=rootView.findViewById(R.id.pageNum);
        pageNum.setText("565");
        final TextView surhText = rootView.findViewById(R.id.surahText);
        SpannableString s1= new SpannableString(json);
        for (int i : Functions.GetPosAyah(json)){
            s1.setSpan(new StyleSpan(Typeface.BOLD),i-1,i+2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        surhText.setText(s1);
        pageTitle.setText(pagetitre);
        surhText.setCustomSelectionActionModeCallback(new StyleCallback(surhText,numero));
        TextView hideBtn=rootView.findViewById(R.id.hideBtn);//button li nkhebbi bih
        TextView showBtn=rootView.findViewById(R.id.showBtn);//button li n affichi bih


        final List<String> subStringList=new ArrayList<>();
        final ForegroundColorSpan fcsWhite=new ForegroundColorSpan(Color.WHITE);
        final ForegroundColorSpan fcsBlack=new ForegroundColorSpan(Color.BLACK);

        for (int i=16;i<43;i++){
            subStringList.add(Functions.ChangetoArabic(i));

        }
        surhText.setText(s1);
        final String fullText=surhText.getText().toString();//all the page text

        ((MyQuranApp) getActivity().getApplication()).setSurahText(surhText);
        if(Functions.thereIsStat(numero,surhText)){
            surhText.setText(s1);
        }else{
            cptShow=0;
            SpannableString fullSpannable =Functions.loadPages(surhText,numero);
            for (int i :Functions.GetPosAyah(json)){
                fullSpannable.setSpan(new ForegroundColorSpan(Color.BLACK),i-1,i+2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                fullSpannable.setSpan(new StyleSpan(Typeface.BOLD),i-1,i+2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

        }

        hideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//when we click on hide brn
                SpannableString fullSpanneble=new SpannableString(fullText);//SpannableString pour pouvez changez la couleur
                if (!Functions.thereIsStat(numero,surhText))
                    Toast.makeText(rootView.getContext(),"لقد تم محو سجل المراجعة في هذه الصفحة  ",Toast.LENGTH_SHORT).show();

                fullSpanneble.setSpan(fcsWhite,0,fullText.length()-1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                /*String subString="سورة الملك";*/
                /*Toast.makeText(rootView.getContext(),"cliicked",Toast.LENGTH_SHORT).show();*/

                for (int i :Functions.GetPosAyah(json)){
                    fullSpanneble.setSpan(new StyleSpan(Typeface.BOLD),i-1,i+2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                for (int i = 0; i <subStringList.size() ; i++) {
                    ForegroundColorSpan fcsblack=new ForegroundColorSpan(Color.BLACK);
                    int startIndex =fullText.indexOf(subStringList.get(i));
                    fullSpanneble.setSpan(fcsblack,startIndex,startIndex+subStringList.get(i).length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }


                DataBaseHelper dataBaseHelper=new DataBaseHelper(rootView.getContext());
                dataBaseHelper.deleteSubStringsPagesStat(numero);



                cptShow=0;
                surhText.setText(fullSpanneble);

                ((MyQuranApp) getActivity().getApplication()).setSurahText(surhText);

            }
        });


        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//show btn
                String fullText=surhText.getText().toString();
                SpannableString fullSpannable=new SpannableString(fullText);

                //   cptShow=2;

                fullSpannable.setSpan(fcsWhite,0,fullSpannable.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                if(Functions.thereIsStat(numero,surhText)){
                    fullSpannable.setSpan(fcsWhite,0,fullSpannable.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }else{

                    List<PageStatModel> pageStatModels = Functions.loadPagesLists(surhText,numero);

                    for (PageStatModel pageStatModel :pageStatModels ){
                        fullSpannable.setSpan(new ForegroundColorSpan(Color.RED),pageStatModel.getIndexDeb(),pageStatModel.getIndexFin(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }

                }
                for (int i :Functions.GetPosAyah(json)){
                    fullSpannable.setSpan(new StyleSpan(Typeface.BOLD),i-1,i+2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    fullSpannable.setSpan(new ForegroundColorSpan(Color.BLACK),i-1,i+2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                switch (cptShow){

                    case 0://  showing the first word
                        cptShow++;
                        for (int i = 0; i <subStringList.size()-1 ; i++) {
                            ForegroundColorSpan fcsblack=new ForegroundColorSpan(Color.BLACK);
                            int startIndex =fullText.indexOf(subStringList.get(i));
                            if (i==0) // first ayah in page
                                fullSpannable.setSpan(fcsBlack,0,Functions.GetIndexOfFirstWord(json,0),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            fullSpannable.setSpan(fcsblack,startIndex,startIndex+subStringList.get(i).length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            fullSpannable.setSpan(new ForegroundColorSpan(Color.BLACK),startIndex+4,Functions.GetIndexOfFirstWord(json,startIndex+5),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                        }
                        ForegroundColorSpan fcsblack1=new ForegroundColorSpan(Color.BLACK);
                        int startIndex1 =fullText.indexOf(subStringList.get(subStringList.size()-1));//nafiichi numero laakher
                        fullSpannable.setSpan(fcsblack1,startIndex1,startIndex1+subStringList.get(subStringList.size()-1).length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                        break;
                    case 1:// showing  2 first words and last word
                        cptShow++;
                        for (int i = 0; i <subStringList.size()-1 ; i++) {
                            ForegroundColorSpan fcsblack=new ForegroundColorSpan(Color.BLACK);
                            int startIndex =fullText.indexOf(subStringList.get(i));
                            fullSpannable.setSpan(fcsblack,startIndex,startIndex+subStringList.get(i).length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            fullSpannable.setSpan(new ForegroundColorSpan(Color.BLACK),startIndex+4,Functions.GetIndexOfSecondWord(json,startIndex+5),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            fullSpannable.setSpan(new ForegroundColorSpan(Color.BLACK),Functions.GetIndexOfLastWord(json,startIndex),startIndex,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                        }

                        ForegroundColorSpan fcsblack=new ForegroundColorSpan(Color.BLACK);
                        ForegroundColorSpan fcsblack2=new ForegroundColorSpan(Color.BLACK);
                        fullSpannable.setSpan(fcsblack2,0,Functions.GetIndexOfSecondWord(json,0),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        int startIndex =fullText.indexOf(subStringList.get(subStringList.size()-1));
                        fullSpannable.setSpan(fcsblack,Functions.GetIndexOfLastWord(json,startIndex),startIndex,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                        break;
                    case 2:
                        fullSpannable.setSpan(fcsBlack,0,fullSpannable.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        Toast.makeText(rootView.getContext(),String.valueOf(cptShow),Toast.LENGTH_SHORT).show();

                        //     cptShow=0;

                }





                surhText.setText(fullSpannable);


            }
        });






        return rootView;
    }





}
