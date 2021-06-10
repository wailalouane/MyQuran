package com.example.myquran;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

import androidx.annotation.DrawableRes;

public class MyDynamicDrawableSpan extends DynamicDrawableSpan {

 private final Context mContext;
 private final int mResourceId;

 public MyDynamicDrawableSpan(Context context, @DrawableRes int resourceId) {
     mContext = context;
     mResourceId = resourceId;
 }

 @Override
 public Drawable getDrawable() {
      Drawable drawable = mContext.getDrawable(mResourceId);
      drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
      return drawable;
 }
 }