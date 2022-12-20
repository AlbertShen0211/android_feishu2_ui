package com.android.ctmanager.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.ctmanager.R;
import com.android.ctmanager.base.BaseFragment;

import butterknife.BindView;

public class MenuFragment3 extends BaseFragment {

    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.tv_name)
    TextView tv_name;
    private String Tag=getClass().getSimpleName();


    public MenuFragment3() {
        // Required empty public constructor
    }

    @Override
    protected void initData() {

    }

    public static MenuFragment3 newInstance(String param) {
        MenuFragment3 fragment = new MenuFragment3();
        Bundle args = new Bundle();
        args.putString("param",param);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_menu;
    }

    @Override
    public void initViews() {
        if (getArguments()!=null){
            String param = getArguments().getString("param");
            Log.e(Tag,"param="+param);
            tv_name.setText(param);
        }

    }

    @Override
    public String getPageName() {
        return null;
    }


}