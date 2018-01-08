package com.webkul.mobikul.customtoast;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.webkul.mobikul.customtoast.databinding.CustomToastLayoutBinding;


/**
 * Created by anchit.makkar on 11/11/17.
 * A Custom Toast Class to create dynamic Toasts.
 */

public class CustomToast extends Toast {
    private CustomToastLayoutBinding mBinding;
    private Context mContext;
    private Drawable mToastIcon, mToastBackground;
    private IconPosition mToastIconPosition = IconPosition.START;
    private String mToastText;
    private int mToastTextSize,mToastTextScaleType = TypedValue.COMPLEX_UNIT_SP;

    @ColorInt
    private int mToastTextColor, mToastBackgroundColor;

    /**
     * Construct an empty CustomToast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public CustomToast(Context context) {
        super(context);
        mContext = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.custom_toast_layout,null,false);
    }

    @Override
    public void show() {
        if (mToastIcon !=null) {
            switch (mToastIconPosition) {
                case TOP:
                    mBinding.customToastTextView.setCompoundDrawablesWithIntrinsicBounds(null, mToastIcon, null, null);
                    break;
                case BOTTOM:
                    mBinding.customToastTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, mToastIcon);
                    break;
                case END:
                    mBinding.customToastTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, mToastIcon, null);
                    break;
                case START:
                default:
                    mBinding.customToastTextView.setCompoundDrawablesWithIntrinsicBounds(mToastIcon, null, null, null);
                    break;
            }
        }
        if (mToastText != null && !mToastText.isEmpty()){
            mBinding.customToastTextView.setText(mToastText);
        }


        if (mToastBackground != null){
            mBinding.getRoot().setBackground(mToastBackground);
        }

        if (mToastBackgroundColor != 0) {
            mBinding.getRoot().getBackground().setColorFilter(mToastBackgroundColor, PorterDuff.Mode.SRC_ATOP);
        }

        if (mToastTextColor != 0){
            mBinding.customToastTextView.setTextColor(mToastTextColor);
        }

        if (mToastTextSize != 0){
            mBinding.customToastTextView.setTextSize(mToastTextScaleType,mToastTextSize);
        }

        setView(mBinding.getRoot());
        super.show();
    }

    public CustomToast setToastIcon(Drawable drawable){
        mToastIcon = drawable;
        return this;
    }

    public CustomToast setIconPosition(@NonNull IconPosition iconPosition){
        mToastIconPosition = iconPosition;
        return this;
    }

    public CustomToast setToastText(String text){
        mToastText = text;
        return this;
    }


    public CustomToast setToastText(@StringRes int text){
        mToastText =mContext.getString(text);
        return this;
    }

    @Override
    public void setText(@StringRes int text) throws RuntimeException {
        setText(mContext.getString(text));
    }

    @Override
    public void setText(CharSequence text) throws RuntimeException {
        RuntimeException re = new RuntimeException("try using setToastText() instead of setText()");
        throw re;
    }



    public CustomToast setToastBackground(Drawable drawable){
        mToastBackground =  drawable;
        return this;
    }

    public CustomToast setToastBackground(@DrawableRes int drawable){
        Drawable drawableBg = ContextCompat.getDrawable(mContext, drawable);
        return setToastBackground(drawableBg);
    }

    public CustomToast setToastBackgroundColor(@ColorInt int color) {
        mToastBackgroundColor = color;
        return this;
    }
    public CustomToast setToastBackground(ToastBackgroundType type){
        Drawable drawableBg;
        switch (type){
            // TODO: 5/1/18 Get A Proper Circle shape
//            case CIRCLE:
//                drawableBg = ContextCompat.getDrawable(mContext,R.drawable.custom_toast_circular_bg);
//                break;
            case OVAL:
                drawableBg = ContextCompat.getDrawable(mContext, R.drawable.custom_toast_oval_bg);
                int topPadding = (int)convertDpToPixel(mContext.getResources().getDimension(R.dimen.custom_toast_main_view_padding),mContext);
                int leftPadding = 2*topPadding;
                mBinding.getRoot().setPadding(leftPadding,topPadding,leftPadding,topPadding);
                break;
            case RECTANGLE:
                drawableBg = ContextCompat.getDrawable(mContext,R.drawable.custom_toast_rectangular_bg);
                break;
            case ROUNDED_CORNERS:
            default:
                drawableBg = ContextCompat.getDrawable(mContext, R.drawable.custom_toast_rounded_corners_bg);
                break;
        }

        return setToastBackground(drawableBg);
    }

    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }


    public CustomToast setToastTextColor(@ColorInt int textColor){
        mToastTextColor = textColor;
        return this;
    }


    public CustomToast setToastTextSizeWithUnit(int size,int type){
        switch (type){

            case TypedValue.COMPLEX_UNIT_DIP:
                mToastTextScaleType = TypedValue.COMPLEX_UNIT_DIP;
                break;
            case TypedValue.COMPLEX_UNIT_SP:
                mToastTextScaleType = TypedValue.COMPLEX_UNIT_SP;
                break;
            case TypedValue.COMPLEX_UNIT_PT:
                mToastTextScaleType = TypedValue.COMPLEX_UNIT_PT;
                break;
            case TypedValue.COMPLEX_UNIT_IN:
                mToastTextScaleType = TypedValue.COMPLEX_UNIT_IN;
                break;
            case TypedValue.COMPLEX_UNIT_MM:
                mToastTextScaleType = TypedValue.COMPLEX_UNIT_MM;
                break;
            case TypedValue.COMPLEX_UNIT_PX:
            default:
                mToastTextScaleType = TypedValue.COMPLEX_UNIT_PX;
                break;
        }

        mToastTextSize = size;
        return this;
    }

    public CustomToast setTextSize(int size){
        return this.setToastTextSizeWithUnit(size,TypedValue.COMPLEX_UNIT_PX);
    }

    public CustomToast setTextSize(int size, int unit) {
        return this.setToastTextSizeWithUnit(size, unit);
    }

    public CustomToast setToastPosition(@NonNull ToastPosition toastPosition){
        int gravityFlag;

        switch (toastPosition){
            case CENTER:
                gravityFlag = Gravity.CENTER;
                break;
            case TOP_RIGHT:
                gravityFlag = Gravity.TOP |Gravity.RIGHT;
                break;
            case TOP_LEFT:
                gravityFlag = Gravity.TOP|Gravity.LEFT;
                break;
            case BOTTOM_LEFT:
                gravityFlag = Gravity.BOTTOM|Gravity.LEFT;
                break;
            case BOTTOM_RIGHT:
                gravityFlag = Gravity.BOTTOM| Gravity.RIGHT;
                break;
            case RIGHT:
                gravityFlag = Gravity.RIGHT;
                break;
            case LEFT:
                gravityFlag = Gravity.LEFT;
                break;
            case TOP:
                gravityFlag =Gravity.TOP;
                break;
            case BOTTOM:
            default:
                gravityFlag = Gravity.BOTTOM;
                break;
        }

        super.setGravity(gravityFlag,super.getXOffset(),super.getYOffset());

        return this;
    }



    public static CustomToast getInstantToast(@NonNull Context context,@NonNull InstantToastType type){
        CustomToast customToast = new CustomToast(context);
        customToast.setIconPosition(IconPosition.START);
        customToast.setToastPosition(ToastPosition.BOTTOM);
        customToast.setToastBackground(ToastBackgroundType.ROUNDED_CORNERS);
        customToast.setToastTextColor(Color.WHITE);
        switch (type){
            case SUCCESS:
                customToast.setToastText(R.string.success_message);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    customToast.setToastIcon(context.getResources().getDrawable(R.drawable.ic_vector_custom_toast_success_icon,null));
                }else {
                    customToast.setToastIcon(context.getResources().getDrawable(R.drawable.ic_vector_custom_toast_success_icon));
                }
                customToast.setToastBackgroundColor(Color.parseColor("#21c23c"));
                break;
            case ERROR:
                customToast.setToastText(R.string.error_message);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    customToast.setToastIcon(context.getResources().getDrawable(R.drawable.ic_vector_custom_toast_error_icon,null));
                }else {
                    customToast.setToastIcon(context.getResources().getDrawable(R.drawable.ic_vector_custom_toast_error_icon));
                }
                customToast.setToastBackgroundColor(Color.parseColor("#ff5858"));
                break;
            case WARNING:
                customToast.setToastText(R.string.warning_message);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    customToast.setToastIcon(context.getResources().getDrawable(R.drawable.ic_vector_custom_toast_warning_icon,null));
                }else {
                    customToast.setToastIcon(context.getResources().getDrawable(R.drawable.ic_vector_custom_toast_warning_icon));
                }
                customToast.setToastBackgroundColor(Color.parseColor("#ffa21c"));
                break;
            case NORMAL:
                customToast.setToastText(R.string.toast_message);
                break;
        }
        return customToast;
    }




    public enum InstantToastType{
          SUCCESS
        , ERROR
        , WARNING
        , NORMAL
    }

    public enum ToastBackgroundType{
          OVAL
//        , CIRCLE
        , ROUNDED_CORNERS
        , RECTANGLE
    }

    public enum ToastPosition{
          TOP
        , BOTTOM
        , RIGHT
        , LEFT
        , TOP_RIGHT
        , TOP_LEFT
        , BOTTOM_RIGHT
        , BOTTOM_LEFT
        , CENTER
    }
    public enum IconPosition{
          TOP
        , START
        , END
        , BOTTOM
    }
}
