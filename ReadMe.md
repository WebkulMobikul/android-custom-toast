# Custom Toast

A library to show customized toast instead of the boring ones.

This library helps you create the toasts and change thier visuals as per your need in the android application.


:star:  `build support version used : 26.1.0 `

:star:  `Data Binding is used`

# Importing Istructions

Add this line in your `module level build.gradle` file

```
  compile 'com.webkul.mobikul:customtoast:1.0.1'
```

:exclamation:  `Make Sure Databinding is set to true in your project`

For this check this line in your `module level build.gradle` file

```
android {
    dataBinding {
        enabled = true
    }
    //
    // Other gradle instructions
    //
 }
    
```

# Usage Examples

*       CustomToast.getInstantToast(this, CustomToast.InstantToastType.SUCCESS).show();

![Instant Toast Success Type](https://mobikul.com/wp-content/uploads/2018/01/success-toast-image.png)


*       CustomToast.getInstantToast(this, CustomToast.InstantToastType.WARNING).show();

![Instant Toast Warning Type](https://mobikul.com/wp-content/uploads/2018/01/toast-warning-image.png)

*       CustomToast.getInstantToast(this, CustomToast.InstantToastType.ERROR).show();

![Instant Toast Error Type](https://mobikul.com/wp-content/uploads/2018/01/toast-error-image.png)

*       CustomToast customToast = new CustomToast(this);
        customToast.setToastPosition(CustomToast.ToastPosition.CENTER);
        customToast.setToastIcon(ContextCompat.getDrawable(this, android.R.drawable.btn_star));
        customToast.setIconPosition(CustomToast.IconPosition.END);
        customToast.setToastText(R.string.my_toast_text);
        customToast.setTextSize(12, TypedValue.COMPLEX_UNIT_DIP);
        customToast.setToastBackground(CustomToast.ToastBackgroundType.ROUNDED_CORNERS);
        customToast.setToastBackgroundColor(Color.BLUE);
        customToast.setToastTextColor(Color.WHITE);
        customToast.show();

![Custom Toast Full Customised](https://mobikul.com/wp-content/uploads/2018/01/customized-toast-image.png)

