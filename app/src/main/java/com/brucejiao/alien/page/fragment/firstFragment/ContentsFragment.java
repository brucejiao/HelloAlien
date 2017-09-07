package com.brucejiao.alien.page.fragment.firstFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brucejiao.alien.R;
import com.brucejiao.alien.utils.CommUtil;

import butterknife.ButterKnife;


/**
 * ViewPaper的内容详情界面
 */
public class ContentsFragment extends Fragment {

    public ContentsFragment() {
    }

    public static ContentsFragment newInstance(String tag)
    {
        ContentsFragment frag = new ContentsFragment();
        Bundle args = new Bundle();
        args.putString("TAG", tag);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initUI(inflater,container);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /**
     *根据不同的 TAG 加载出不同的界面
     * 并在 onCreateView 中加载出来
     * @param inflater
     * @param container
     * @return
     */
    private  View  initUI(LayoutInflater inflater, ViewGroup container){
        String value = getArguments().getString("TAG");
        switch (value)
        {
            case "FIRST_PAGE":
                View FIRST_PAGE_VIEW = inflater.inflate(R.layout.fragment_contents, container, false);
                ButterKnife.bind(this, FIRST_PAGE_VIEW);
                return FIRST_PAGE_VIEW;
            case "SECOND_PAGE":
                View SECOND_PAGE_VIEW = inflater.inflate(R.layout.fragment_contents_test, container, false);
                ButterKnife.bind(this, SECOND_PAGE_VIEW);
                return SECOND_PAGE_VIEW;
            default:break;
        }
        return null;
    }
}
