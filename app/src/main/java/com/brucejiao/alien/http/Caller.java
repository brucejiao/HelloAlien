package com.brucejiao.alien.http;


import com.brucejiao.alien.utils.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by Administrator on 2017/6/11.
 * 接口
 */

public class Caller  {

    /**
     Picasso.with(mContext).load("http://pic.nipic.com/2008-07-11/20087119630716_2.jpg").resize(DeviceUtil.dp2px(mContext,73), DeviceUtil.dp2px(mContext,73)).placeholder(R.drawable.default_image).into(mIssueImgOne);

     */
    // IP & PORT
    //9064  正式环境（苏州服务器）
    //9099  陈颖鸽
    //9088  焦健俊   1193
//	public static String BASE_IP = "http://222.190.120.106:9064/zzskp/zpdk.yd";
    public static String BASE_IP = "http://222.190.120.106:9064/zzskp/zpdk.yd";
    public static String SEND_MESSAGE_URL = "http://222.190.120.106:9064/dxfs/sendMessage.api";//suzhou  http://219.83.163.147:8099/hospital/
    public static String MESSAGE_VERI_URL = "http://222.190.120.106:9064/dxfs/messageVerification.api";//suzhou  http://219.83.163.147:8099/hospital/

    //baidu  Address
    public static String BAIDU_GEOCODER = "http://api.map.baidu.com/geocoder";//suzhou  http://219.83.163.147:8099/hospital/


    /**
     * 注册
     * @param regisPhone
     * @param regisPaw
     * @param idCard
     * @return
     */
    public static String getRegisterParams(String regisPhone,String regisPaw,String idCard,String mail){
        Map<String,String> map = new HashMap<String,String>();
        map.put("type", "register.api");
        map.put("phoneNum", regisPhone);
        map.put("userPass", regisPaw);
        map.put("idCard", idCard);
        map.put("type", "1");
        map.put("mail ", mail);
        JSONObject json = new JSONObject(map);
        LogUtil.i("====注册数据====", json.toString());
        return json.toString();
    }

}
