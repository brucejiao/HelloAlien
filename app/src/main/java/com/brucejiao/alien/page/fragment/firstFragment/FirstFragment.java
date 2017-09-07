package com.brucejiao.alien.page.fragment.firstFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brucejiao.alien.R;
import com.brucejiao.alien.dointerface.IAddressModel;
import com.brucejiao.alien.http.request.baiduRequest.BaiduGeocReqest;
import com.brucejiao.alien.page.activity.BaseActivity;
import com.brucejiao.alien.ui.statusBar.StatusBarCompat;
import com.brucejiao.alien.ui.viewPaper.BaseViewPageUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.brucejiao.alien.R.id.appbar;
import static com.brucejiao.alien.R.id.toolbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements IAddressModel {

    @Bind(R.id.tab_FindFragment_title)
    TabLayout mTabLayoutTitle;

    @Bind(R.id.vp_FindFragment_pager)
    ViewPager mViewPaperFragment;
    @Bind(toolbar)
    Toolbar mToolBar;
    @Bind(appbar)
    AppBarLayout mAppBar;
    @Bind(R.id.frist_fragment)
    DrawerLayout mFristFrag;
    @Bind(R.id.toolbar_title_text)
    TextView mToolBarTitleText;

    public FirstFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar((Toolbar) view.findViewById(toolbar));

//        StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.transparence));
        StatusBarCompat.setStatusBarColor(getActivity(), BaseActivity.DEFAULT_COLOR, 0);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("output","json");
        params.put("location","32.170222,118.718123");
        BaiduGeocReqest.initData(params,this);

    }

    /**
     *
     */
    public void initView() {

        //初始化各fragment
        ContentsFragment  oneFragment = ContentsFragment.newInstance("FIRST_PAGE");
        ContentsFragment  twoFragment = ContentsFragment.newInstance("SECOND_PAGE");

        //将fragment装进列表中
        List<Fragment> list_fragment = new ArrayList<Fragment>();
        list_fragment.add(oneFragment);
        list_fragment.add(twoFragment);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        List<String>  list_title = new ArrayList<String>();
        list_title.add("界面一");
        list_title.add("界面二");
        BaseViewPageUI.initViewPaper(FirstFragment.this, mTabLayoutTitle, mViewPaperFragment,
                                     list_title,list_fragment, null);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
//            StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.transparence));
            StatusBarCompat.setStatusBarColor(getActivity(), BaseActivity.DEFAULT_COLOR, 0);
        }
    }

    @Override
    public void setProgress(boolean progress) {

    }

    @Override
    public void setObject(Object object) {

    }

    @Override
    public void setValue(String value) {
        mToolBarTitleText.setText(value);
        mToolBar.setNavigationIcon(R.drawable.arrow_top_left);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolBar);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
//        inflater.inflate(R.menu.menu_parent_fragment, menu);
    }

    @Override
    public void setList(List list) {

    }
}
