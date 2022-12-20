package com.android.ctmanager;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.android.ctmanager.base.BaseAppToolbarActivity;
import com.android.ctmanager.fragment.MenuFragment0;
import com.android.ctmanager.fragment.MenuFragment1;
import com.android.ctmanager.fragment.MenuFragment2;
import com.android.ctmanager.fragment.MenuFragment3;
import com.android.ctmanager.fragment.MenuFragment4;
import com.android.ctmanager.fragment.MenuFragment5;
import com.android.ctmanager.fragment.MenuFragment6;
import com.android.ctmanager.widget.SimpleCustomPop;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity2 extends BaseAppToolbarActivity {
    String tag= MainActivity2.class.getSimpleName();
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
//    @BindView(R.id.mViewPager)
//    NoViewPager mViewPager;

    private Fragment mCurrentFragment =new  Fragment();
    private MenuFragment0 fragment0;
    private MenuFragment1 fragment1;
    private MenuFragment2 fragment2;
    private MenuFragment3 fragment3;
    private MenuFragment4 fragment4;
//    private MenuFragment6 fragment6;
//    private MenuFragment0 fragment;
//    private MenuFragment0 fragment;
//    private MenuFragment0 fragment;
//    private MenuFragment0 fragment;
//    private MenuFragment0 fragment;
//    private MenuFragment0 fragment;
    private SimpleCustomPop mQuickCustomPopup;

  //  private String[] titles = {"菜单1", "菜单2", "菜单3", "菜单4", "菜单5", "菜单6", "菜单7"};
    private String[] titles = {"菜单1", "菜单2", "菜单3", "菜单4", "菜单5", "菜单6"};
    private List<Fragment> mFragments = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        mQuickCustomPopup = new SimpleCustomPop(this);
        initView();
        initBottomNavBar();
    }

    @Override
    protected void initData() {

    }
    private void initView() {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//          for (int i=0;i<titles.length;i++){
//              if (i>4) break;
//              fragmentTransaction.add(R.id.fl_container,MenuFragment.newInstance("menu+"+i));
//              mFragments.add(MenuFragment.newInstance("menu+"+i));
//          }
//        fragmentTransaction.commit();

//        for (int i=0;i<titles.length;i++){
//              if (i>4) break;
//
//          }
        fragment0=MenuFragment0.newInstance("menu+"+0);
        fragment1=MenuFragment1.newInstance("menu+"+1);
        fragment2=MenuFragment2.newInstance("menu+"+1);
        fragment3=MenuFragment3.newInstance("menu+"+3);
        fragment4=MenuFragment4.newInstance("menu+"+4);
        addFragment(fragment0);
        addFragment(fragment1);
        addFragment(fragment2);
        addFragment(fragment3);
        addFragment(fragment4);
        showFragment(fragment0);
    }


    private void initBottomNavBar() {

        mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        for (int i=0;i<titles.length;i++){
            if (i==5 && titles.length>5)
            mBottomNavigationBar
                    .addItem(new BottomNavigationItem(R.drawable.ic_app_bg, "更多"));
            else{
                mBottomNavigationBar
                        .addItem(new BottomNavigationItem(R.drawable.ic_app_bg, "menu"+i));
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
                if (position == 6 && titles.length>6){
                  //  Toast.makeText(MainActivity.this, "5", Toast.LENGTH_SHORT).show();
//                     chooseSiteDialog[0] = new MoreDialog(MainActivity.this);
//                    chooseSiteDialog[0].show();
//                    chooseSiteDialog[0].setCanceledOnTouchOutside(true);


                }
               else{
                    Log.e("dd","position: "+position);
                    Fragment fragment = mFragments.get(position);
                    addFragment(fragment);
                    showFragment(fragment);
                }
               // rightImage.setVisibility(View.GONE);
                String title = titles[position];
//                if (position == 1 || position == 2) {
//                    hideToolbar();
//                } else {
//                    showToolbar();
//                }
                setTitle(title);


            }

            @Override
            public void onTabUnselected(int position) {
              //  Log.e("dd","1111");

            }

            @Override
            public void onTabReselected(int position) {
                Log.e("dd","2222");
                if (position == 5 && titles.length>5){
                    //  Toast.makeText(MainActivity.this, "5", Toast.LENGTH_SHORT).show();
                 //   MoreDialog chooseSiteDialog = new MoreDialog(MainActivity.this);
//
//                    chooseSiteDialog[0].show();
//                    chooseSiteDialog[0].setCanceledOnTouchOutside(true);
//                    Log.e("dd","dddddd");
                    //  return;
                }
            }
        });

    }


    private void addFragment(Fragment fragment) {

        /*判断该fragment是否已经被添加过  如果没有被添加  则添加*/
        if (!fragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.fl_container, fragment).commit();
            /*添加到 fragmentList*/
            mFragments.add(fragment);
        }
    }

    /*显示fragment*/
    private void showFragment(Fragment fragment) {
        for (Fragment frag : mFragments) {
        Log.e("1","21 f--- "+frag.getClass().getSimpleName());
        Log.e("1","21 fg--- "+fragment.getClass().getSimpleName());
            if (frag != fragment) {
                Log.e("1","2");
                getSupportFragmentManager().beginTransaction().hide(frag).commit();
            }
        }
        getSupportFragmentManager().beginTransaction().show(fragment).commit();

    }
}
