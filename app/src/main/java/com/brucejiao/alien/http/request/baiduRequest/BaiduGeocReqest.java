package com.brucejiao.alien.http.request.baiduRequest;

import com.alibaba.fastjson.JSONObject;
import com.brucejiao.alien.dointerface.IAddressModel;
import com.brucejiao.alien.http.Caller;
import com.brucejiao.alien.http.HttpClient;
import com.brucejiao.alien.http.HttpResponseHandler;
import com.brucejiao.alien.http.response.baiduGeocoResp.BaiduGeoResp;
import com.brucejiao.alien.http.response.baiduGeocoResp.GeoeResult;
import com.brucejiao.alien.page.fragment.firstFragment.FirstFragment;

import java.util.HashMap;

import okhttp3.Request;

/**
 * Created by JiaoJianJun on 2017/9/1.
 */

public class BaiduGeocReqest {
    private static IAddressModel mIAddressModel;

    public static void initData(HashMap<String, String> params,final FirstFragment activity){
        mIAddressModel = activity;
        HttpClient.get(Caller.BAIDU_GEOCODER,params, new HttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject response) {
                String resp = response.toString();
                BaiduGeoResp baiduGeoResponse = JSONObject.parseObject(resp,BaiduGeoResp.class);
                String result = baiduGeoResponse.getResult();
                GeoeResult baiduGeoResult = JSONObject.parseObject(result,GeoeResult.class);
                String formatAddress = baiduGeoResult.getFormatted_address();
                mIAddressModel.setProgress(true);
                mIAddressModel.setValue(formatAddress);
//                Alerter.create(activity)
//                        .setText(formatAddress)
//                        .setBackgroundColorRes(R.color.green)
//                        .show();
            }
            @Override
            public void onFailure(Request request, Exception e) {
                mIAddressModel.setProgress(false);
            }
        });
    }
}
