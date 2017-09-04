package com.brucejiao.alien.page.activity;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.brucejiao.alien.R;
import com.brucejiao.alien.dointerface.IAddressModel;
import com.brucejiao.alien.utils.CommUtil;

import java.util.List;


public class MainActivity extends BaseActivity implements IAddressModel {

    private MainActivity mContext;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mContext = this ;

//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("output","json");
//        params.put("location","32.170222,118.718123");
//        BaiduGeocReqest.initData(params,this);
}


    @Override
    public void setProgress(boolean isProgress) {
        progress = CommUtil.showProgress(mContext, "正在加载数据，请稍候...");
        if (isProgress){
            if (progress != null)
            {
                progress.dismiss();
            }
            return;
        }
    }

    @Override
    public void setObject(Object object) {

    }

    @Override
    public void setValue(String value) {
//        CommUtil.showAlert(value,mContext);
    }

    @Override
    public void setList(List list) {

    }
}
