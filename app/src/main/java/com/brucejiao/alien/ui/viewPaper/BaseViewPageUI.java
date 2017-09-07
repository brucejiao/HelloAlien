package com.brucejiao.alien.ui.viewPaper;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.List;


/**
 * Created by JiaoJianJun on 2017/9/6.
 * 侧滑界面的基类
 * 其他侧滑界面基于此进行调整
 */

public class BaseViewPageUI {

    public static void initViewPaper(Fragment context , TabLayout tabLayoutTitle, ViewPager viewPager,
                                     List<String> list_title, List<Fragment>  list_fragment, ViewPager.OnPageChangeListener onPageChangeListener) {


        //设置TabLayout的模式
        tabLayoutTitle.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
//        tabLayoutTitle.addTab(tabLayoutTitle.newTab().setText(list_title.get(0)));
//        tabLayoutTitle.addTab(tabLayoutTitle.newTab().setText(list_title.get(1)));
        for(String title: list_title){
            tabLayoutTitle.addTab(tabLayoutTitle.newTab().setText(title));
        }


        //   getActivity().getSupportFragmentManager()会导致一个问题：数据丢失  看网上讲这边要用getChildFragmentManager()
        Find_tab_Adapter fAdapter = new Find_tab_Adapter(context.getChildFragmentManager(), list_fragment, list_title);//   getActivity().getSupportFragmentManager()会导致一个问题：数据丢失

        //viewpager加载adapter
        viewPager.setAdapter(fAdapter);
//      tabLayout.setViewPager(vp_FindFragment_pager);

        //TabLayout加载viewpager
        tabLayoutTitle.setupWithViewPager(viewPager);
        tabLayoutTitle.setTabsFromPagerAdapter(fAdapter);
        viewPager.setOnPageChangeListener(onPageChangeListener);
    }

}
