package com.udacity.gradle.builditbigger;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by Semmiverian on 2/19/17.
 */

public class DialogHelper {
    private Context context;

    public DialogHelper(Context context) {
        this.context = context;
    }

    public void singlePositiveDialog(String title, String content, String buttonText,MaterialDialog.SingleButtonCallback callback){
        new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .positiveText(buttonText)
                .positiveColorRes(R.color.teal_400)
                .onPositive(callback)
                .show();
    }

    public void positiveAndNegativeDialog(String title, String content, String buttonPositiveText, String buttonNegativeText, MaterialDialog.SingleButtonCallback callbackPositive, MaterialDialog.SingleButtonCallback callbackNegative){
        new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .positiveText(buttonPositiveText)
                .negativeText(buttonNegativeText)
                .positiveColorRes(R.color.teal_400)
                .negativeColorRes(R.color.teal_400)
                .onPositive(callbackPositive)
                .onNegative(callbackNegative)
                .show();
    }

    public MaterialDialog loadingDialog(String title,String content){
        return new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .progress(true, 0)
                .widgetColorRes(R.color.teal_400)
                .show();
    }

}
