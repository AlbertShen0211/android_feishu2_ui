package com.android.ctmanager.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.android.ctmanager.R;

import butterknife.BindView;


/**
 * @author pengl
 */
public abstract class BaseAppToolbarActivity extends BaseActivity {
    protected TextView rightText;
    protected TextView leftText;
    protected TextView titleText;
    public TextView leftImageText;
    protected ImageView rightImage;
    protected Toolbar toolbar;
    @BindView(R.id.rlHomeLeft)
    RelativeLayout rlHomeLeft;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvHomeTitle)
    TextView tvHomeTitle;

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rightText = (TextView) findViewById(R.id.toolbar_right_text);
        leftText = (TextView) findViewById(R.id.toolbar_left_text);
        titleText = (TextView) findViewById(R.id.toolbar_title);
        leftImageText = (TextView) findViewById(R.id.toolbar_left_img_text);
        rightImage = (ImageView) findViewById(R.id.toolbar_right_image);
        leftImageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //this.initToolbarHelper();
        hideHomeLeft();
    }

    public void hideHomeLeft() {
        rlHomeLeft.setVisibility(View.GONE);
    }


    /**
     * init the toolbar
     */
    protected void initToolbarHelper() {
        if (this.toolbar == null) return;

        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        /*if (Build.VERSION.SDK_INT >= 21) {
            this.mAppBarLayout.setElevation(5.0f);
        }*/
    }

    @Override
    public void setTitle(CharSequence title) {
        leftText.setVisibility(View.VISIBLE);
        leftText.setText(title);
        leftImageText.setVisibility(View.GONE);
    }

    @Override
    public void setTitle(int titleId) {
        leftText.setVisibility(View.VISIBLE);
        leftText.setText(titleId);
        leftImageText.setVisibility(View.GONE);
    }

    public void setToolbarTitle(int titleId) {
        titleText.setText(titleId);
        titleText.setVisibility(View.VISIBLE);
    }
    public void setToolbarTitle(String title) {
        titleText.setText(title);
        titleText.setVisibility(View.VISIBLE);
    }

    protected void setNotFitsSystemWindows() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setFitsSystemWindows(false);
    }

    protected void setBackTitle(int titleId) {
        setBackTitle(getString(titleId));
    }
    protected void setBackTitle(String title) {
        leftImageText.setVisibility(View.VISIBLE);
        leftImageText.setText(title);
        leftText.setVisibility(View.GONE);
    }

    protected void hideBackText() {
        leftText.setVisibility(View.GONE);
        leftImageText.setVisibility(View.GONE);
    }

    protected void hideToolbar() {
        toolbar.setVisibility(View.GONE);
    }
    protected void showToolbar() {
        toolbar.setVisibility(View.VISIBLE);
    }


    public void toSelfSetting() {
        Intent mIntent = new Intent();
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        mIntent.setData(Uri.fromParts("package", getPackageName(), null));
        context.startActivity(mIntent);
    }

}