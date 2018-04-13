package com.webkul.mobikul.webkulcustomtoast;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;

import com.webkul.mobikul.customtoast.CustomToast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showCustomToast(View view){
        CustomToast customToast = new CustomToast(this);
        customToast.setToastPosition(CustomToast.ToastPosition.CENTER)
                .setToastIcon(ContextCompat.getDrawable(this, android.R.drawable.btn_star))
                .setIconPosition(CustomToast.IconPosition.END)
                .setToastText(R.string.my_dummy_text)
                .setTextSize(12, TypedValue.COMPLEX_UNIT_DIP)
                .setToastBackground(CustomToast.ToastBackgroundType.ROUNDED_CORNERS)
                .setToastBackgroundColor(Color.BLUE)
                .setToastTextColor(Color.WHITE)
                .show();

    }


    public void showInstantSuccessToast(View view){
        CustomToast.getInstantToast(this, CustomToast.InstantToastType.SUCCESS).show();
    }

    public void showInstantWarningToast(View view){
        CustomToast.getInstantToast(this, CustomToast.InstantToastType.WARNING).show();
    }

    public void showInstantErrorToast(View view){
        CustomToast.getInstantToast(this, CustomToast.InstantToastType.ERROR).show();
    }
}
