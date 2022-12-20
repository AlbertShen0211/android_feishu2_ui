package com.android.ctmanager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.ctmanager.adapter.MenuAdapter;
import com.android.ctmanager.base.BaseAppToolbarActivity;
import com.android.ctmanager.base.BaseFragment;
import com.android.ctmanager.fragment.MenuFragment0;
import com.android.ctmanager.widget.NoViewPager;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseAppToolbarActivity {
    String tag = MainActivity.class.getSimpleName();
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    @BindView(R.id.mViewPager)
    NoViewPager mViewPager;
    @BindView(R.id.ll_menu)
    LinearLayout ll_menu;
    @BindView(R.id.fl_cd)
    FrameLayout fl_cd;

    @BindView(R.id.rv_menu_bottom)
    RecyclerView rv_menu_bottom;

    int history = 0;
    private String[] titles = {"菜单1", "菜单2", "菜单3", "菜单4", "菜单5", "菜单6", "菜单7", "菜单8", "菜单9", "菜单10", "菜单11", "菜单12", "菜单13"};
    private List<BaseFragment> mFragments = new ArrayList<>();

    private MenuAdapter adapter;
    ArrayList<Menu> menus = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        initViewPager();
        // initBottomNavBar();
    }

    private void initRecycleView(ArrayList list) {
        rv_menu_bottom.setLayoutManager(new GridLayoutManager(this, 4));
        adapter = new MenuAdapter(R.layout.menu_item, list);
        rv_menu_bottom.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        for (int i = 0; i < 12; i++) menus.add(new Menu("type" + i, "name" + i, "icon" + i));
        initRecycleView(new ArrayList(menus.subList(4, menus.size())));

        fl_cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_menu.setVisibility(View.GONE);
                mBottomNavigationBar.selectTab(history);
            }
        });

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Log.e(tag, "onItemClick: " + position);
            }
        });
        initBottomNavBar();

    }

    private void initViewPager() {
        mViewPager.setCanSwipe(false);

        for (int i = 0; i < titles.length; i++) {
            if (i > 4) break;
            mFragments.add(MenuFragment0.newInstance("menu+" + i));
        }

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                mBottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.getBoolean("isMainActivityDestroy", false)) {
        }
    }


    private void initBottomNavBar() {

        mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        for (int i = 0; i < titles.length; i++) {
            if (i == 4 && titles.length > 4)
                mBottomNavigationBar
                        .addItem(new BottomNavigationItem(R.drawable.ic_app_bg, "更多"));
            else if (i < 4) {
                Log.e("2", "menus name :" + menus.get(i).getName());
                mBottomNavigationBar
                        .addItem(new BottomNavigationItem(R.drawable.ic_app_bg, menus.get(i).getName()));
            } else {
            }
        }

        mBottomNavigationBar.setFirstSelectedPosition(0)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setActiveColor(R.color.colorAccent)
                .setInActiveColor(R.color.black)
                .initialise();

        // final MoreDialog[] chooseSiteDialog = new MoreDialog[1];
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                if (position == 4 && titles.length > 4) {
                    boolean shown = ll_menu.isShown();
                    if (!ll_menu.isShown()) {
                        ll_menu.setVisibility(View.VISIBLE);
                    } else {
                        ll_menu.setVisibility(View.GONE);
                    }

                } else {
                    ll_menu.setVisibility(View.GONE);
                    history = position;
                    mViewPager.setCurrentItem(position, false);
                }

                String title = titles[position];
                setTitle(title);


            }

            @Override
            public void onTabUnselected(int position) {
                Log.e("dd", "1111");

            }

            @Override
            public void onTabReselected(int position) {
                if (position == 4 && titles.length > 4) {
                    if (position == 4 && titles.length > 4) {
                        boolean shown = ll_menu.isShown();
                        Log.e("s", "shown:" + shown);
                        if (!ll_menu.isShown()) {
                            ll_menu.setVisibility(View.VISIBLE);
                        } else {
                            ll_menu.setVisibility(View.GONE);
                            mBottomNavigationBar.selectTab(history);
                        }


                    } else {

                        ll_menu.setVisibility(View.GONE);
                        history = position;
                        mViewPager.setCurrentItem(position, false);
                    }
                }
            }
        });

    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("isMainActivityDestroy", true);
    }

}
