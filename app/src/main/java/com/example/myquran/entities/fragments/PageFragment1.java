package com.example.myquran.entities.fragments;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.DrawableMarginSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.example.myquran.MyDynamicDrawableSpan;
import com.example.myquran.MyQuranApp;
import com.example.myquran.R;
import com.example.myquran.entities.model.Functions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.myquran.R.drawable.rectangle;
import static com.example.myquran.R.drawable.shape;

public class PageFragment1 extends Fragment {

    String json;
    int cptShow=2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle =getArguments();
         json = bundle.getString("json");//lire le paramtre li jaz men SlidePageAdapter li howa text li kayn f la page smito berk hakka json
         String titrePage=bundle.getString("titre");//titre ta3 la page

        final ViewGroup rootView =(ViewGroup)inflater.inflate(R.layout.page_1,container,false);

        TextView pageTitle=rootView.findViewById(R.id.surahName);
        TextView pageNum=rootView.findViewById(R.id.pageNum);
        pageNum.setText("562");
        final TextView surhText = rootView.findViewById(R.id.surahText);
        ImageButton hideBtn=rootView.findViewById(R.id.hideBtn);//button li nkhebbi bih
        ImageButton showBtn=rootView.findViewById(R.id.showBtn);//button li n affichi bih
        int BissmilahPos = json.indexOf("بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيم");

        SpannableString s1= new SpannableString(json);
        s1.setSpan(new RelativeSizeSpan(1.4f),BissmilahPos,BissmilahPos+"بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيم".length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        s1.setSpan(new ForegroundColorSpan(Color.BLACK),BissmilahPos,BissmilahPos+"بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيم".length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        BissmilahPos = json.indexOf("سورة الملك");
        int x = json.indexOf("\n",BissmilahPos);

        Typeface myTypeface = Typeface.create(ResourcesCompat.getFont(getContext(),R.font.surahs),
                Typeface.BOLD);

        s1.setSpan(new StyleSpan(Typeface.BOLD),BissmilahPos,x,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        s1.setSpan(new RelativeSizeSpan(1.4f),BissmilahPos,x,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        s1.setSpan(new ForegroundColorSpan(Color.BLACK),BissmilahPos,x,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        surhText.setText(s1);
       /* surhText.setText(Html.fromHtml(
                json
        ));*/
        pageTitle.setText(titrePage);
        final List<String> subStringList=new ArrayList<>();
        final ForegroundColorSpan fcsWhite=new ForegroundColorSpan(Color.WHITE);
        final ForegroundColorSpan fcsBlack=new ForegroundColorSpan(Color.BLACK);
        /*ForegroundColorSpan fcsgrreen=new ForegroundColorSpan(Color.GREEN);*/
        //hado les Strig pour les affichier tjr dans la page
        subStringList.add("سورة الملك");
        subStringList.add("بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ");
        for (int i=1;i<13;i++){
        subStringList.add(Functions.ChangetoArabic(i));

        }


        final String fullText=surhText.getText().toString();//all the page text

        ((MyQuranApp) getActivity().getApplication()).setSurahText(surhText);


        hideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//when we click on hide brn
                SpannableString fullSpanneble=new SpannableString(fullText);//SpannableString pour pouvez changez la couleur
                /*String subString="سورة الملك";*/
                /*Toast.makeText(rootView.getContext(),"cliicked",Toast.LENGTH_SHORT).show();*/

                fullSpanneble.setSpan(fcsWhite,0,fullSpanneble.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//hada bach nkhebi kolch w naffichi ghir wach lazem

                for (int i = 0; i <subStringList.size() ; i++) {
                    if (i==0){

                    }
                    ForegroundColorSpan fcsblack=new ForegroundColorSpan(Color.BLACK);
                    int startIndex =fullText.indexOf(subStringList.get(i));
                    fullSpanneble.setSpan(fcsblack,startIndex,startIndex+subStringList.get(i).length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    fullSpanneble.setSpan(fcsblack,startIndex,startIndex+subStringList.get(i).length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    if (i<2)
                    fullSpanneble.setSpan(new RelativeSizeSpan(1.4f),startIndex,startIndex+subStringList.get(i).length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    if (i==0)
                    fullSpanneble.setSpan(new StyleSpan(Typeface.BOLD),startIndex,startIndex+subStringList.get(i).length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }






                cptShow=0;
               surhText.setText(fullSpanneble);

                ((MyQuranApp) getActivity().getApplication()).setSurahText(surhText);

            }
        });


        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//show btn
                String fullText=surhText.getText().toString();
                SpannableString fullSpanneble=new SpannableString(fullText);
             //   cptShow=2;
                fullSpanneble.setSpan(fcsWhite,0,fullSpanneble.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                switch (cptShow){

                    case 0:
                        cptShow++;
                        for (int i = 0; i <subStringList.size()-1 ; i++) {
                            ForegroundColorSpan fcsblack=new ForegroundColorSpan(Color.BLACK);
                            int startIndex =fullText.indexOf(subStringList.get(i));
                            fullSpanneble.setSpan(fcsblack,startIndex,startIndex+subStringList.get(i).length()+10,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                        }
                        ForegroundColorSpan fcsblack1=new ForegroundColorSpan(Color.BLACK);
                        int startIndex1 =fullText.indexOf(subStringList.get(subStringList.size()-1));//nafiichi numero laakher
                        fullSpanneble.setSpan(fcsblack1,startIndex1,startIndex1+subStringList.get(subStringList.size()-1).length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                        break;
                    case 1:
                        cptShow++;
                        for (int i = 1; i <subStringList.size()-1 ; i++) {
                            ForegroundColorSpan fcsblack=new ForegroundColorSpan(Color.BLACK);
                            int startIndex =fullText.indexOf(subStringList.get(i));
                            fullSpanneble.setSpan(fcsblack,startIndex-10,startIndex+subStringList.get(i).length()+15,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                        }
                        ForegroundColorSpan fcsblack=new ForegroundColorSpan(Color.BLACK);
                        ForegroundColorSpan fcsblack2=new ForegroundColorSpan(Color.BLACK);
                        fullSpanneble.setSpan(fcsblack2,0,15,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        int startIndex =fullText.indexOf(subStringList.get(subStringList.size()-1));
                        fullSpanneble.setSpan(fcsblack,startIndex-10,startIndex+subStringList.get(subStringList.size()-1).length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                        break;
                    case 2:
                        fullSpanneble.setSpan(fcsBlack,0,fullSpanneble.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        Toast.makeText(rootView.getContext(),String.valueOf(cptShow),Toast.LENGTH_SHORT).show();

                   //     cptShow=0;

                }




                               surhText.setText(fullSpanneble);

                ((MyQuranApp) getActivity().getApplication()).setSurahText(surhText);
            }
        });
        return rootView;
    }





}
