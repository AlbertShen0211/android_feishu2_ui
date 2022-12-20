package com.android.ctmanager.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.ctmanager.R;
import com.android.ctmanager.widget.popup.base.BasePopup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimpleCustomPop extends BasePopup<SimpleCustomPop> {

    @BindView(R.id.tv_item_1)
    TextView mTvItem1;
    @BindView(R.id.tv_item_2) TextView mTvItem2;
    @BindView(R.id.tv_item_3) TextView mTvItem3;
    @BindView(R.id.tv_item_4) TextView mTvItem4;

    public SimpleCustomPop(Context context) {
        super(context);
//            setCanceledOnTouchOutside(false);
    }

    @Override
    public View onCreatePopupView() {
        View inflate = View.inflate(mContext, R.layout.popup_custom, null);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {

    }
}