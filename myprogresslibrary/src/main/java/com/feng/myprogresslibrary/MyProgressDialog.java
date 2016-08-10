package com.feng.myprogresslibrary;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class MyProgressDialog extends Dialog {

    private static final String TAG = "MyPersonDialog";

    Context context;
    MyCircle progress;


    public MyProgressDialog(Context context) {
        this(context, R.style.color_dialog);
        this.context = context;
    }

    public MyProgressDialog(Context context, int themeResId) {
        super(context, R.style.color_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = View.inflate(getContext(), R.layout.dialog_progress, null);
        setContentView(contentView);

        progress= (MyCircle) contentView.findViewById(R.id.progress);
    }

    public void setColor(int color){
        if (progress!=null){
            progress.setColor(color);
        }
    }

    public void setMaxRadius(int maxRadius){
        if (progress!=null){
            progress.setMaxRadius(maxRadius);
        }
    }

    public void setDuration(int time){
        if (progress!=null){
            progress.setDuration(time);
        }
    }

    @Override
    public void show() {
        super.show();
        progress.startAnimator();
    }

    @Override
    public void dismiss() {
        progress.stopAnimator();
        super.dismiss();
    }
}
