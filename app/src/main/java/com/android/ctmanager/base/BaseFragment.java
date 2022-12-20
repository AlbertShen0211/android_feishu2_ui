package com.android.ctmanager.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.maning.mndialoglibrary.MProgressDialog;
import com.maning.mndialoglibrary.MToast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {


    private Unbinder unbinder;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        initData();
        return view;
    }

    protected abstract void initData();

    public abstract int getLayoutId();

    public abstract void initViews();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }

    }


    public void showToast(String message) {
        if (isAdded()) {
            MToast.makeTextShort(getActivity(), message);
        }
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
        if (isAdded()) {
            MToast.makeTextShort(getContext(), text);
        }

    }

    public void showProgressDialog() {
        showProgressDialog("");
    }

    public void showProgressDialog(String message) {
        showProgressDialog(message, true);
    }

    public void showProgressDialog(String message, boolean cancelable) {
        MProgressDialog.showProgress(getContext(), message);
    }

    public void dismissProgressDialog() {
        MProgressDialog.dismissProgress();
    }


    public void cancelToast() {
        MToast.cancelToast();
    }


    public int dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public abstract String getPageName();


}
