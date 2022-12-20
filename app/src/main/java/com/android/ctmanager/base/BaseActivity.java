package com.android.ctmanager.base;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.ctmanager.R;
import com.gyf.immersionbar.ImmersionBar;
import com.maning.mndialoglibrary.MProgressDialog;
import com.maning.mndialoglibrary.MToast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author pengl
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Context context;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(getLayoutId());
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);

        context = this;
        ImmersionBar.with(this)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .fitsSystemWindows(true)    //解决状态栏和布局重叠问题，任选其一，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色，还有一些重载方法
                .statusBarColor(R.color.white)
                .init();

        BaseActivityManager.getInstance().addActivity(this);

        unbinder = ButterKnife.bind(this);

        this.initToolbar(savedInstanceState);
        this.initViewsAndEvents(savedInstanceState);
        this.initData();
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = res.getConfiguration();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (unbinder != null)
            unbinder.unbind();

        closeKeyboard();

        cancelToast();
        dismissProgressDialog();
        BaseActivityManager.getInstance().removeActivity(this);
        context = null;

    }


    protected abstract int getLayoutId();

    protected abstract void initViewsAndEvents(Bundle savedInstanceState);

    protected abstract void initData();

    protected void initToolbar(Bundle savedInstanceState) { };

    protected <V extends View> V findView(int id) {
        return (V) this.findViewById(id);
    }

    public void showProgressDialog() {
        showProgressDialog("");
    }

    public void showProgressDialog(String message) {
        showProgressDialog(message, true);
    }

    public void showProgressDialog(String message, boolean cancelable) {
        MProgressDialog.showProgress(this, message);
    }

    @Deprecated
    public void stopProgressDialog() {
        MProgressDialog.dismissProgress();
    }

    public void dismissProgressDialog() {
        MProgressDialog.dismissProgress();
    }


    public void showToast(String message) {
        MToast.makeTextShort(this, message);
    }

    public void showToast(int messageId) {
        showToast(getString(messageId));
    }

    public void showCustomToast(int msgId) {
        showCustomToast(getString(msgId));
    }

    public void showCustomToast(String message) {
        showCustomToast(message, Toast.LENGTH_SHORT);
    }

    public void showCustomToast(CharSequence text, int duration) {
        MToast.makeTextShort(this, text);

    }

    public void cancelToast() {
        MToast.cancelToast();
    }

    public void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public int dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}